package com.liuxin.ladder.aop.redis;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

/**
 * @author meteor
 */
public class RedisUtil {

    /**
     * @param joinPoint
     * @param params    参数
     * @param prefix    前缀
     * @return
     * @throws Exception
     */
    public static String returnRedisKey(ProceedingJoinPoint joinPoint, String prefix, String[] params) throws Exception {
        boolean b = checkPrefix(prefix);
        if (!b) {
            throw new RuntimeException("缓存前缀规则有错误或键为空");
        }
        List<String> keyName = new LinkedList<>();
        keyName.add(prefix);
        for (int i = 0; i < params.length; i++) {
            String param = params[i];
            //判定是否有. 例如user.id  有则要处理，无则进一步处理
            if (!param.contains(".")) {
                Object arg = getArg(joinPoint, param);
                //判定请求参数中是否有相关参数。无则直接当键处理，有则取值当键处理
                if (arg != null) {
                    keyName.add(arg.toString());
                }
            } else {
                //拿到对象参数 例如  user.id  拿到的是user这个相关对象去获取请求参数,然后通过反射区获取参数中的属性数据
                String[] values = StringUtils.split(param, ".");
                Object arg = getArg(joinPoint, values[0]);
                String result = JSONObject.toJSONString(ReflectionUtil.getFieldValue(arg, values[1]));
                if (result != null) {
                    keyName.add(result);
                }
            }
        }
        return StringUtils.join(keyName, RedisKeyConstant.SPLIT);
    }

    /**
     * 检查key前缀
     *
     * @param prefix
     * @return
     */
    private static boolean checkPrefix(String prefix) {
        if (StringUtils.isEmpty(prefix)) {
            return false;
        }
        //如果没有以:开头，报错
        return prefix.indexOf(":") > 1;
    }

    /**
     * 获取请求的参数。
     *
     * @param joinPoint 切点
     * @param paramName
     * @return 返回和参数名一样的参数对象或值
     * @throws NoSuchMethodException 异常
     */
    private static Object getArg(ProceedingJoinPoint joinPoint, String paramName) throws NoSuchMethodException {
        Method method = getMethod(joinPoint);
        ParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] p = parameterNameDiscoverer.getParameterNames(method);
        if (p == null) {
            throw new IllegalArgumentException("没有参数[" + paramName + "] 没有方法:" + method);
        }
        //判断是否有相关参数
        int indix = 0;

        for (String string : p) {
            if (string.equalsIgnoreCase(paramName)) {
                return joinPoint.getArgs()[indix];
            }
            indix++;
        }
        return null;
    }


    /**
     * 数据转换,将缓存中的数据转换为调用方法的返回类型
     *
     * @param joinPoint
     * @param value
     * @return
     * @throws ClassNotFoundException
     */
    public static Object getValueActualTypeData(ProceedingJoinPoint joinPoint, String value) throws ClassNotFoundException {
        Method method = getMethod(joinPoint);
        Class returnActualType = getReturnActualType(method);
        if (null != returnActualType) {
            return JSONObject.parseArray(value, returnActualType);
        }
        return null;
    }

    /**
     * 数据转换
     *
     * @param method
     * @return
     * @throws ClassNotFoundException
     */
    private static Class getReturnActualType(Method method) throws ClassNotFoundException {
        Type genericReturnType = method.getGenericReturnType();
        if (genericReturnType instanceof ParameterizedType) {
            Type[] actualTypes = ((ParameterizedType) genericReturnType).getActualTypeArguments();
            for (Type actualType : actualTypes) {
                return Class.forName(actualType.getTypeName());
            }
        }
        return null;
    }

    /**
     * 参数名
     *
     * @param joinPoint
     * @return
     */
    private static Method getMethod(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        return methodSignature.getMethod();
    }
}

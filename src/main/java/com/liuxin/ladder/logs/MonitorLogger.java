package com.liuxin.ladder.logs;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import ch.qos.logback.classic.Level;

/**
 * 打印监控用日志用
 * @author liuxin
 *
 */
public class MonitorLogger {

  
  /*exlce导入导出日志*/
  private static Logger importLog = LoggerFactory.getLogger("import_log");
  
  /**定时任务日志*/
  private static Logger timerLog = LoggerFactory.getLogger("timer_log");

  /**调用第三方系统日志*/
  private static Logger thirdPartLog = LoggerFactory.getLogger("sync_log");

  /**rabbitmq日志*/
  private static Logger rabbitmqLog = LoggerFactory.getLogger("");

  
  /**
	 * 打印controller层请求info日志
	 * @param cls 类名
	 * @param requestPath 请求地址
	 * @param params 请求参数
	 * @param expand 扩展字段
	 */
	public static void printControllerRqeusetLog(Class<?> cls,String requestPath, Object params, Object expand){
	  LoggerFactory.getLogger(cls).info("类名：{}，请求地址：{}，请求参数：{}，拓展描述：{}",cls.getSimpleName() , requestPath, params, expand);
	}
	
	
	/**
       *  打印controller层响应的日志
   * @param cls 类名
   * @param requestPath 请求地址
   * @param totalTime 耗时(毫秒)
   * @param params 接口响应信息
   * @param errorMsg 错误信息
   * @param thra  错误的堆栈信息
   */
	 public static  void printControllerResponseLog(Class<?> cls,String requestPath,long totalTime, Object params,Object expand, String errorMsg,Throwable e){
	   if(StringUtils.isNotBlank(errorMsg)) {
       LoggerFactory.getLogger(cls).info("类名：{}，请求地址：{}，请求耗时:{}ms，响应信息：{}，拓展描述：{}", cls.getClass().getName(), requestPath, totalTime,params,expand);
	   }else {
	     LoggerFactory.getLogger(cls).error("类名：{}，请求地址：{}，请求耗时:{}ms，拓展描述：{}，错误信息：{}", cls.getClass().getName(), requestPath, totalTime,expand,errorMsg,e);
	   }
	  }
	 
	 
	 /**
	  * @Title: printServicestInfoLog
	  * @Description: 打印业务层(service,dao)普通的日志
	  * @param cls     类名
	  * @param methodName    方法名称 
	  * @param msgMap  关键信息描述,key-value形式 比如scode-RMBJ00201
	  * @param expand   拓展信息   
	  * void   
	  * @throws
	  */
	  public static void printBussinesstLog(Class<?> cls,String methodName, Map<String, String> msgMap, Object expand,String type){
	    if(Level.ERROR.levelStr.equals(type)) {
	      LoggerFactory.getLogger(cls).error("类名：{}，方法名称：{}，{}，拓展描述：{}",cls.getSimpleName() , methodName, JSONObject.toJSONString(msgMap), expand);
	    }else if(Level.WARN.levelStr.equals(type)) {
	      LoggerFactory.getLogger(cls).warn("类名：{}，方法名称：{}，{}，拓展描述：{}",cls.getSimpleName() , methodName, JSONObject.toJSONString(msgMap), expand);
	    }else{
	      LoggerFactory.getLogger(cls).info("类名：{}，方法名称：{}，{}，拓展描述：{}",cls.getSimpleName() , methodName, JSONObject.toJSONString(msgMap), expand);

	    }
    }
	  
	  
	  /**
	   * @Title: printImportInfoLog
	   * @Description: excel导入导出info日志
	   * @param fileName 文件名称
	   * @param baseInfo 导入基本信息, appKey businessType schoolId 等基础信息
	   * @param excelHead excel表头信息
	   * @param params
	   * @param expand   
	   * void   
	   * @throws
	   */
	   public static void printImportHeaderInfoLog(String fileName,Object baseInfo, Object excelHead){
	     importLog.info("文件名称：{}，导入基本信息：{}，表头信息：{}",fileName , baseInfo,excelHead);
	    }
	   
	   /**
	     * @Title: printImportInfoLog
	     * @Description: excel导入导出info日志
	     * @param appKey
	     * @param content 导入的内容 ,按行输出
	     * void   
	     * @throws
	     */
	   public static void printImportContentInfoLog(String appKey, Object content){
       importLog.error("appKey：{}，导入内容：{}", appKey, content);
      }
	   
	   
	   /**
      * @Title: printImportInfoLog
      * @Description: excel导入导出info日志
      * @param appKey
      * @param errorMsg 错误内容,按行输出
      * void   
      * @throws
      */
	   public static void printImportErrorLog(String appKey, Object errorMsg){
       importLog.error("appKey：{}，错误内容：{}", appKey, errorMsg);
     }
	   
	   /**
	    * @Title: printTimerBeginLog 定时任务开始日志
	    * @Description: 定时任务日志打印
	    * @param requestPath 请求路径
	    * @param startTime   请求时间
	    * void   
	    * @throws
	    */
	   public static void printTimerBeginLog(String requestPath, long startTime){
	     timerLog.info("接口名称：{}，开始时间：{}",requestPath, startTime);
     }
	   
	   
     /**
      * @Title: printTimerFinishLog 定时任务结束日志
      * @Description: 定时任务日志打印
      * @param requestPath 请求路径
      * @param startTime   请求时间
      * @param endTime     结束时间
      * @param totalTime   总耗时
      * void   
      * @throws
      */
     public static void printTimerFinishLog(String requestPath, long endTime, Long totalTime,String errorMsg, Throwable e){
       if(StringUtils.isNotBlank(errorMsg)) {
         timerLog.info("接口名称：{}，开始时间：{}，结束时间：{}，总耗时：{}ms，任务完成!",requestPath, endTime,totalTime);
       }else {
         timerLog.error("接口名称：{}，开始时间：{}，结束时间：{}，总耗时：{}ms，任务出错：{}，",requestPath, endTime,totalTime,errorMsg,e);
       }
     }
     
     /**
      * @Title: printTimerLog
      * @Description: 定时任务日志打印
      * @param cls         类名
      * @param methodName  方法名
      * @param msgMap      关键信息描述
      * @param expand      拓展参数
      * @param type        日志类型
      * void   
      * @throws
      */
     public static void printTimerLog(Class<?> cls,String methodName, Map<String, String> msgMap, Object expand,String type){
       if(Level.ERROR.levelStr.equals(type)) {
         LoggerFactory.getLogger(cls).error("类名：{}，方法名称：{}，{}，拓展描述：{}",cls.getSimpleName() , methodName, JSONObject.toJSONString(msgMap), expand);
       }else if(Level.WARN.levelStr.equals(type)) {
         LoggerFactory.getLogger(cls).warn("类名：{}，方法名称：{}，{}，拓展描述：{}",cls.getSimpleName() , methodName, JSONObject.toJSONString(msgMap), expand);
       }else{
         LoggerFactory.getLogger(cls).info("类名：{}，方法名称：{}，{}，拓展描述：{}",cls.getSimpleName() , methodName, JSONObject.toJSONString(msgMap), expand);
       }
     }
     
     
     /**
      * 
      * @Title: printThirdPartInfoLog
      * @Description: 请求第三方系统日志输出
      * @param requestId  请求唯一标识
      * @param msgMap     关键信息描述
      * void   
      * @throws
      */
     public static void printThirdPartInfoLog(String requestId,Map<String, String> msgMap){
       thirdPartLog.info("请求标识：{}，请求参数{}",requestId, JSONObject.toJSONString(msgMap));
     }
     
     /**
      * @Title: printThirdPartSuccLog
      * @Description: 请求第三方系统成功后输出
      * @param systemName     请求系统名称
      * @param url            请求路径
      * @param requestId      请求唯一标识
      * @param requestParams  请求参数
      * @param totalTimes     总耗时
      * @param responseParams 响应信息  
      * void   
      * @throws
      */
     public static void printThirdPartSuccLog(String systemName,String url,String requestId,Object requestParams, long totalTimes,Object responseParams){
       thirdPartLog.info("请求成功！请求系统(接口)名称：{}，请求路径：{}，请求标识：{}，请求参数{}，响应参数:{}，请求耗时：{}",systemName, url,requestId, JSONObject.toJSONString(requestParams),responseParams,totalTimes);
     }
     
     
     /**
      * 
      * @Title: printThirdPartErrorLog
      * @Description: 请求第三方系统错误日志输出
      * @param systemName     请求系统名称
      * @param url            请求路径
      * @param requestId      请求唯一标识
      * @param requestParams  请求参数
      * @param totalTimes     总耗时
      * @param e   
      * void   
      * @throws
      */
     public static void printThirdPartErrorLog(String systemName,String url, Object requestParams,String requestId, long totalTimes,Throwable e){
       thirdPartLog.error("请求失败！请求系统(接口)名称：{}，请求路径：{}，请求标识：{}，请求耗时：{}，错误信息:{}",systemName, url, requestId, JSONObject.toJSONString(requestParams),totalTimes,e);
     }
     
}

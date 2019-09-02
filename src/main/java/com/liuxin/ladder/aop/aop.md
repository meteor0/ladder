[参考资料](https://www.ibm.com/developerworks/cn/java/j-spring-boot-aop-web-log-processing-and-distributed-locking/index.html)
[参考资料](https://blog.csdn.net/corbin_zhang/article/details/80576809)
### WebLogAspect
 * @Aspect:作用是把当前类标识为一个切面供容器读取
 * @Pointcut：Pointcut是植入Advice的触发条件。每个Pointcut的定义包括2部分，一是表达式，二是方法签名。方法签名必须是 public及void型。可以将Pointcut中的方法看作是一个被Advice引用的助记符，因为表达式不直观，因此我们可以通过方法签名的方式为 此表达式命名。因此Pointcut中的方法只需要方法签名，而不需要在方法体内编写实际代码。
 * @Around：环绕增强，相当于MethodInterceptor
 * @AfterReturning：后置增强，相当于AfterReturningAdvice，方法正常退出时执行
 * @Before：标识一个前置增强方法，相当于BeforeAdvice的功能，相似功能的还有
 * @AfterThrowing：异常抛出增强，相当于ThrowsAdvice
 * @After: final增强，不管是抛出异常或者正常退出都会执行
 * @Component 注解是说明该类作为一个 Spring 组件。
 
### ControllerWebLog
 * @Target说明了Annotation所修饰的对象范围
 * @Retention可以用来修饰注解，是注解的注解，称为元注解。
 * 1、RetentionPolicy.SOURCE：注解只保留在源文件，当Java文件编译成class文件的时候，注解被遗弃；
 * 2、RetentionPolicy.CLASS：注解被保留到class文件，但jvm加载class文件时候被遗弃，这是默认的生命周期；
 * 3、RetentionPolicy.RUNTIME：注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在；
 
 #### @Pointcut
 > execution()是最常用的切点函数，其语法如下所示：
 execution(<修饰符模式>? <返回类型模式> <方法名模式>(<参数模式>) <异常模式>?)
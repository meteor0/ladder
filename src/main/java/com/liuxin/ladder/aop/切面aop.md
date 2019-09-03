[参考资料](https://www.ibm.com/developerworks/cn/java/j-spring-boot-aop-web-log-processing-and-distributed-locking/index.html)
[@Pointcut参考资料](https://blog.csdn.net/corbin_zhang/article/details/80576809)
## AOP 的核心概念
- 切面（Aspect）：通常是一个类，在里面可以定义切入点和通知。
- 连接点（Joint Point）：被拦截到的点，因为 Spring 只支持方法类型的连接点，所以在 Spring 中连接点指的就是被拦截的到的方法，实际上连接点还可以是字段或者构造器。
- 切入点（Pointcut）：对连接点进行拦截的定义。
- 通知（Advice）：拦截到连接点之后所要执行的代码，通知分为前置、后置、异常、最终、环绕通知五类。
- AOP 代理：AOP 框架创建的对象，代理就是目标对象的加强。Spring 中的 AOP 代理可以使 JDK 动态代理，也可以是 CGLIB 代理，前者基于接口，后者基于子类。

### 主要注解
 * @Aspect:作用是把当前类标识为一个切面供容器读取
 * @Pointcut：Pointcut是植入Advice的触发条件。每个Pointcut的定义包括2部分，一是表达式，二是方法签名。方法签名必须是 public及void型。可以将Pointcut中的方法看作是一个被Advice引用的助记符，因为表达式不直观，因此我们可以通过方法签名的方式为 此表达式命名。因此Pointcut中的方法只需要方法签名，而不需要在方法体内编写实际代码。
 * @Around：环绕增强，相当于MethodInterceptor
 * @AfterReturning：后置增强，相当于AfterReturningAdvice，方法正常退出时执行
 * @Before：标识一个前置增强方法，相当于BeforeAdvice的功能，相似功能的还有
 * @AfterThrowing：异常抛出增强，相当于ThrowsAdvice
 * @After: final增强，不管是抛出异常或者正常退出都会执行
 * @Component 注解是说明该类作为一个 Spring 组件。
 * @Order(i) 标识切面的优先级, i 的值越小，优先级越高
 **其中 @Before、@After、@AfterReturning、@Around、@AfterThrowing 都属于通知。**

 * @Target说明了Annotation所修饰的对象范围
 * @Retention可以用来修饰注解，是注解的注解，称为元注解。
 * 1、RetentionPolicy.SOURCE：注解只保留在源文件，当Java文件编译成class文件的时候，注解被遗弃；
 * 2、RetentionPolicy.CLASS：注解被保留到class文件，但jvm加载class文件时候被遗弃，这是默认的生命周期；
 * 3、RetentionPolicy.RUNTIME：注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在；
 
 #### @Pointcut
 > execution()是最常用的切点函数，其语法如下所示：
 execution(<修饰符模式>? <返回类型模式> <方法名模式>(<参数模式>) <异常模式>?)
 eg:@Pointcut("execution(* cn.itweknow.sbaop.controller..*.*(..))")

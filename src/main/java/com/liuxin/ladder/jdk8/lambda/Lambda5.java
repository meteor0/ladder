package com.liuxin.ladder.jdk8.lambda;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.function.*;

/**
 * @description: 内置的函数式接口
 * @author: liuxin79
 * @date: 2019-08-01 15:57
 */
public class Lambda5 {
    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        /**Predicate 断言
         * negate 相反
         * and or 断言组合
         */
        System.out.println("*****************************************Predicate*****************************************");

        Predicate<String> predicate1 = (s) -> s.length() > 0;
        Predicate<String> predicate2 = (s) -> s.length() > 2;
        Predicate<String> predicate3 = predicate2.and(predicate2);

        boolean foo1 = predicate1.test("foo");
        boolean foo2 = predicate1.negate().test("foo");
        boolean foo3 = predicate3.test("1234");

        System.out.println(foo1);
        System.out.println(foo2);
        System.out.println(foo3);

        Predicate<Boolean> nonNull = Objects::nonNull;
        boolean test1 = nonNull.test(true);
        Predicate<String> nonNull2 = Objects::isNull;
        boolean test2 = nonNull2.test("12");
        Predicate<String> predicate = String::isEmpty;
        Predicate<String> test3 = predicate.negate();
        System.out.println(test1);
        System.out.println(test2);
        System.out.println(test3.test("!23"));

        System.out.println("*****************************************Function*****************************************");

        //Function 函数式接口的作用是，我们可以为其提供一个原料，他给生产一个最终的产品。通过它提供的默认方法，组合,链行处理(compose, andThen)：
        Function<String, Integer> toInteger = Integer::valueOf;
        //apply传参的功能
        Integer apply = toInteger.apply("123");
        System.out.println(apply);

        Function<Integer,Integer> A=i->i+1;
        Function<Integer,Integer> B=i->i*i;

        //A.apply(5) = 6 ,B.apply(6)
        System.out.println("F1:"+B.apply(A.apply(5)));
        //compose串联多个函数，先执行compose函数参数的方法(A)，再执行调用者 等价于B.apply(A.apply(5))
        System.out.println("F1:"+B.compose(A).apply(5));
        //B.apply(5) = 25 ,B.apply(25)
        System.out.println("F2:"+A.apply(B.apply(5)));
        //andThen串联多个函数，先执行调用者(B)，再执行参数 等价于A.apply(B.apply(5))
        System.out.println("F2:"+B.andThen(A).apply(5));
        // A.apply(B.apply(A.apply(A.apply(5))))
        System.out.println(B.compose(A).compose(A).andThen(A).apply(5));


        System.out.println("*****************************************Supplier*****************************************");
        //Supplier直接理解成一个创建对象的工厂
        Supplier<Person> personSupplier = Person::new;
        Person person = personSupplier.get();
        System.out.println(person.firstName);

        System.out.println("*****************************************Consumer*****************************************");
        Consumer<Person> consumer = (p) -> System.out.println("Hello, " + p.firstName);
        Consumer<Person> consumer2 = (p) -> System.out.println("world, " + p.lastName);
        consumer.accept(new Person("Skywalker","12"));
        //andThen在执行完调用者(consumer)方法后再执行传入参数的方法(consumer2).
        consumer.andThen(consumer2).accept(new Person("Skywalker","Tim"));


        System.out.println("*****************************************Comparator*****************************************");

        //Comparator就是一个比较器，比较两个元素的大小
        Comparator<Person> comparator = Comparator.comparing(Person::getFirstName);
                                        // (p1, p2) -> p1.firstName.compareTo(p2.firstName);
                                        // Comparator.comparingInt(String::length)
        Person p1 = new Person("John", "Doe");
        Person p2 = new Person("Alice", "Wonderland");
        // compare 比较用来排序的两个参数。compare大于0就证明p1比p2大，返回0就证明p1和p2相等，返回小于0就证明p1比p2小。
        int compare = comparator.compare(p1, p2);
        System.out.println(compare);
        // reversed 反向排序
        int compare1 = comparator.reversed().compare(p1, p2);
        System.out.println(compare1);
        //equals 比较相等
        boolean equals = comparator.equals(p1);
        System.out.println(equals);

        System.out.println("****************************************Optional*****************************************");
        //https://blog.csdn.net/zknxx/article/details/78586799
        //把指定的值封装为Optional对象，如果指定的值为null，则抛出NullPointerException
        Optional<String> ofOptional = Optional.of("张三");
        System.out.println(ofOptional.get());
        //把指定的值封装为Optional对象，如果指定的值为null，则创建一个空的Optional对象
        Optional<String> nullOptional = Optional.ofNullable(null);
        Optional<String> nullOptional2 = Optional.ofNullable("lisi");
        System.out.println(nullOptional2.get());
        //创建一个空的String类型的Optional对象
        Optional<String> emptyOptional = Optional.empty();
        //如果创建的Optional中有值存在，则返回此值，否则抛出NoSuchElementException
        Optional<String> stringOptional = Optional.of("张三2");
        System.out.println(stringOptional.get());
        //如果创建的Optional中有值存在，则返回此值，否则返回一个默认值
        Optional<String> optional2 = Optional.empty();
        System.out.println(optional2.orElse("zhangsan"));
        //如果创建的Optional中有值存在，则返回此值，否则返回一个由Supplier接口生成的值
        Optional<String> optional3 = Optional.empty();
        System.out.println(optional3.orElseGet(() -> "lishi"));
        //如果创建的Optional中有值存在，则返回此值，否则抛出一个由指定的Supplier接口生成的异常
        Optional<String> optional4 = Optional.empty();
        //System.out.println(optional4.orElseThrow(CustomException::new));
        //如果创建的Optional中的值满足filter中的条件，则返回包含该值的Optional对象，否则返回一个空的Optional对象
        Optional<String> optional5 = Optional.of("zhangsan");
        System.out.println(optional5.filter(e -> e.length() > 5).orElse("王五"));
        //map方法执行传入的lambda表达式参数对Optional实例的值进行修改,修改后的返回值仍然是一个Optional对象
        Optional<String> optional6 = Optional.of("zhangsan");
        System.out.println(optional6.map(e -> e.toUpperCase()).orElse("失败"));
        //map方法中的lambda表达式返回值可以是任意类型，在map函数返回之前会包装为Optional。
        //但flatMap方法中的lambda表达式返回值必须是Optionl实例
        Optional<String> optional7 = Optional.of("zhangsan");
        System.out.println(optional7.flatMap(e -> Optional.of("lisi")).orElse("失败"));
        //ifPresent方法的参数是一个Consumer的实现类，Consumer类包含一个抽象方法，该抽象方法对传入的值进行处理，只处理没有返回值。
        Optional<String> optional8 = Optional.of("zhangsan");
        optional8.ifPresent(e-> System.out.println("我被处理了。。。"+e));
    }
    private static class CustomException extends RuntimeException {
        private static final long serialVersionUID = -4399699891687593264L;

        public CustomException() {
            super("自定义异常");
        }

        public CustomException(String message) {
            super(message);
        }
    }
}

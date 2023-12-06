package com.example.db.router.annotation;

import java.lang.annotation.*;

/**
 * @description: 路由注解
 * @Documented 是元注解（注解的注解），用于告诉 Java 应该将该注解包含进文档中，即在使用 javadoc 命令生成文档时，该注解也会被包含在其中。
 * @Retention 也是元注解，用于指定被标记的注解保留的时间。RetentionPolicy.RUNTIME 表示该注解将在运行时保留，在运行时可以通过反射机制获取该注解。
 * @Target 也是元注解，用于指定该注解可以放置的元素类型。该注解可以放置于类型或方法上。
 * public @interface DBRouter：定义一个名为 DBRouter 的注解，它可以被放置在类型或方法上。
 * String key() default “”：注解中定义了一个名为 key 的字符串类型的属性，它的默认值为空字符串。key 属性表示进行数据库路由的字段名，即需要根据哪个字段对数据进行路由。
 *
 * @author yanghanwei
 * @version 1.0.0
 * 2023/7/14 10:42
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DBRouter {

    /** 分库分表字段 */
    String key() default "";

}

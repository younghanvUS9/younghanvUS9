package com.example.db.router.annotation;

import java.lang.annotation.*;

/**
 * 路由策略，分表标记
 *
 * @author yanghanwei
 * @version 1.0.0
 * 2023/7/14 10:43
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DBRouterStrategy {

    boolean splitTable() default false;
}

//package com.ctg.test.dataconfig;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//
//@Aspect
//@Component
//@Order(value = -1)
//public class DynamicDataSourceAspect {
//
//    @Before("execution(* com.unicom.microserv.trades.dao.*.*(..))")
//    @Order(value = -2)
//    public void beforeSwitchDS(JoinPoint point) {
//
//        String dataSource = DatabaseContextHolder.DEFAULT_DS;
//        try {
//            // 得到访问的方法对象
//            Class declaringType = point.getSignature().getDeclaringType();
////
//            // 判断是否存在@DS注解
//            if (null != declaringType.getAnnotation(DB.class)) {
//                DB annotation = (DB) declaringType.getAnnotation(DB.class);
//                // 取出注解中的数据源名
//                dataSource = annotation.value();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        // 切换数据源
//        DatabaseContextHolder.setDB(dataSource);
//
//    }
//
//
//    @After("execution(* com.unicom.microserv.trades.dao.*.*(..))")
//    public void afterSwitchDS(JoinPoint point) {
//
//        DatabaseContextHolder.clearDB();
//
//    }
//}

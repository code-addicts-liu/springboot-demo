//package com.ctg.test.dataconfig;
//
//import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
//
//
//public class DynamicDataSource extends AbstractRoutingDataSource {
//    @Override
//    protected Object determineCurrentLookupKey() {
//        logger.info("当前数据源为：" + DatabaseContextHolder.getDB());
//        return DatabaseContextHolder.getDB();
//    }
//}
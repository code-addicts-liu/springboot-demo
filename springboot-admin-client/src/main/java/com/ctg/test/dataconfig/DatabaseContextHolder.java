package com.ctg.test.dataconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DatabaseContextHolder {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseContextHolder.class);
    /**
     * 默认数据源
     */
    public static final String DEFAULT_DS = "drds";

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    // 设置数据源名
    public static void setDB(String dbType) {
        logger.info("切换到{}数据源" + dbType);
        contextHolder.set(dbType);
    }

    // 获取数据源名
    public static String getDB() {
        return (contextHolder.get());
    }

    // 清除数据源名
    public static void clearDB() {
        contextHolder.remove();
    }

}

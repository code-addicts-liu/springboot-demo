//package com.ctg.test.dataconfig;
//
//import com.alibaba.druid.pool.DruidDataSourceFactory;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.env.Environment;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Properties;
//
//
//@Configuration // 该注解类似于spring配置文件
////@MapperScan(basePackages = {"com.unicom.microserv.trades.**.dao"})
//@EnableTransactionManagement // 开启注解事物
//public class DataSourceConfig {
//    @Autowired
//    Environment env;
//
//    @Bean(name = "drds")
//    @Primary
//    public DataSource dataSourceDef() throws Exception {
//        return getDataSource("spring.datasource");
//    }
//
//    @Bean(name = "iusers")
//    public DataSource dataUserSource() throws Exception {
//        return getDataSource("spring.datasource1");
//    }
//
//    @Bean(name = "crm1")
//    public DataSource dataSource1() throws Exception {
//        return getDataSource("oracle.datasource.crm1");
//    }
//
//    @Bean(name = "crm2")
//    public DataSource dataSource2() throws Exception {
//        return getDataSource("oracle.datasource.crm2");
//    }
//
//    @Bean(name = "crm3")
//    public DataSource dataSource3() throws Exception {
//        return getDataSource("oracle.datasource.crm3");
//    }
//
//    @Bean(name = "crm4")
//    public DataSource dataSource4() throws Exception {
//        return getDataSource("oracle.datasource.crm4");
//    }
//
//    @Bean(name = "crm5")
//    public DataSource dataSource5() throws Exception {
//        return getDataSource("oracle.datasource.crm5");
//    }
//
//    @Bean(name = "crm6")
//    public DataSource dataSource6() throws Exception {
//        return getDataSource("oracle.datasource.crm6");
//    }
//
//    @Bean(name = "crm7")
//    public DataSource dataSource7() throws Exception {
//        return getDataSource("oracle.datasource.crm7");
//    }
//
//    @Bean(name = "crm8")
//    public DataSource dataSource8() throws Exception {
//        return getDataSource("oracle.datasource.crm8");
//    }
//
//
//    public DataSource getDataSource(String prefix) throws Exception {
//        Properties props = new Properties();
//        if (prefix.startsWith("spring")) {
//            props.put("driverClassName", env.getProperty("spring.datasource.driver-class-name"));
//            props.put("url", env.getProperty(prefix + ".jdbc-url"));
//            props.put("username", env.getProperty(prefix + ".username"));
//            String pwd = env.getProperty(prefix + ".password");
//            props.put("password", pwd);
//        } else {
//            props.put("driverClassName", env.getProperty("oracle.datasource.driverClassName"));
//            props.put("url", env.getProperty(prefix + ".url"));
//            props.put("username", env.getProperty(prefix + ".username"));
//            String pwd = env.getProperty(prefix + ".password");
//            props.put("password", pwd);
//        }
//        return DruidDataSourceFactory.createDataSource(props);
//    }
//
//    /**
//     * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
//     * @Qualifier 根据名称进行注入，通常是在具有相同的多个类型的实例的一个注入（例如有多个DataSource类型的实例）
//     */
//    @Bean
//    public DynamicDataSource dataSource(@Qualifier("drds") DataSource dataSourceDef, @Qualifier("crm1") DataSource dataSource1,
//                                        @Qualifier("crm2") DataSource dataSource2, @Qualifier("crm3") DataSource dataSource3,
//                                        @Qualifier("crm4") DataSource dataSource4, @Qualifier("crm5") DataSource dataSource5,
//                                        @Qualifier("crm6") DataSource dataSource6, @Qualifier("crm7") DataSource dataSource7,
//                                        @Qualifier("crm8") DataSource dataSource8, @Qualifier("iusers") DataSource userSource) {
//        Map<Object, Object> targetDataSources = new HashMap<>();
//        targetDataSources.put("crm1", dataSource1);
//        targetDataSources.put("crm2", dataSource2);
//        targetDataSources.put("crm3", dataSource3);
//        targetDataSources.put("crm4", dataSource4);
//        targetDataSources.put("crm5", dataSource5);
//        targetDataSources.put("crm6", dataSource6);
//        targetDataSources.put("crm7", dataSource7);
//        targetDataSources.put("crm8", dataSource8);
//        targetDataSources.put("iusers", userSource);
//
//        DynamicDataSource dataSource = new DynamicDataSource();
//        dataSource.setTargetDataSources(targetDataSources);
//        dataSource.setDefaultTargetDataSource(dataSourceDef);
//
//        return dataSource;
//    }
//
//    /**
//     * 根据数据源创建SqlSessionFactory
//     */
//    @Bean
//    public SqlSessionFactory sqlSessionFactory(DynamicDataSource dataSource) throws Exception {
//        SqlSessionFactoryBean primarySqlSessionFactory = new SqlSessionFactoryBean();
//        primarySqlSessionFactory.setDataSource(dataSource);
//        try {
//            return primarySqlSessionFactory.getObject();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     * 配置事务管理器
//     */
//    @Bean
//    public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) throws Exception {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//}

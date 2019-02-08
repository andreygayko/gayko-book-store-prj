package com.gayko.bookstore.config;

import com.gayko.bookstore.dao.model.*;
import com.zaxxer.hikari.HikariDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

    private final DatabaseProperties databaseProperties;

    @Autowired
    public DatabaseConfig(@Qualifier("databaseProperties") DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }


    @Bean(name = "springLiquibase")
    public SpringLiquibase springLiquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        //liquibase.setDropFirst(Boolean.TRUE);
        liquibase.setChangeLog("classpath:migration/db.changelog.xml");
        return liquibase;
    }

    @Bean
    public DataSource dataSource() {
        final HikariDataSource ds = new HikariDataSource();
        ds.setPoolName("App connection pool");
        ds.setMaximumPoolSize(databaseProperties.getPoolMaxSize());
        ds.setJdbcUrl(databaseProperties.getDatabaseUrl());
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUsername(databaseProperties.getDatabaseUsername());
        ds.setPassword(databaseProperties.getDatabasePassword());
        ds.addDataSourceProperty("cachePrepStmts", databaseProperties.getPoolCachePreparedStatements());
        ds.addDataSourceProperty("prepStmtCacheSize", databaseProperties.getPoolCachePreparedStatementsSize());
        ds.addDataSourceProperty("prepStmtCacheSqlLimit", databaseProperties.getPoolCachePreparedStatementsSQLLimit());
        ds.addDataSourceProperty("useServerPrepStmts", databaseProperties.getPoolUseServerPreparedStatements());
        return ds;
    }

    @Bean
    @DependsOn("springLiquibase")
    public LocalSessionFactoryBean getSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        Properties properties = new Properties();
        properties.put(Environment.DIALECT, databaseProperties.getDialect());
        properties.put(Environment.SHOW_SQL, databaseProperties.getShowSQL());
        //properties.put(Environment.FORMAT_SQL, databaseProperties.getFormatSQL());
        //properties.put(Environment.USE_SQL_COMMENTS, databaseProperties.getUseSQLComments());
        properties.put(Environment.DEFAULT_SCHEMA, "jd2");
        properties.put(Environment.HBM2DDL_AUTO, databaseProperties.getHbm2ddlAuto());
        properties.put(Environment.USE_SECOND_LEVEL_CACHE, databaseProperties.getCacheUseSecondLevelCache());
        properties.put(Environment.CACHE_REGION_FACTORY, databaseProperties.getCacheRegionFactory());

        //properties.put(Environment.GENERATE_STATISTICS, databaseProperties.getGenerateStatistics());
        //properties.put(Environment.AUTO_CLOSE_SESSION, databaseProperties.getAutoCloseSession());
        //properties.put(Environment.AUTOCOMMIT, databaseProperties.getAutocommit());
        factoryBean.setHibernateProperties(properties);
        factoryBean.setAnnotatedClasses(
                User.class,
                Item.class,
                Order.class,
                Comment.class,
                News.class,
                Audit.class,
                Profile.class,
                Role.class,
                Permission.class,
                Discount.class,
                BusinessCard.class
        );
        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }
}

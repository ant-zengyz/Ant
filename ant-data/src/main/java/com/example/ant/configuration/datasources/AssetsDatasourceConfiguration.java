package com.example.ant.configuration.datasources;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 描述：数据源配置
 * User: 曾远征
 * Date: 2018-08-31
 * Time: 15:49
 */
@Configuration
@MapperScan(basePackages = AssetsDatasourceConfiguration.PACKAGE, sqlSessionFactoryRef = "assetsSqlSessionFactory")
public class AssetsDatasourceConfiguration {

    static final String PACKAGE = "com.example.ant.data.assets.dao";
    static final String ALIASESPACKAGE = "com.example.ant.data.assets.entity";
    static final String MAPPER_LOCATION = "classpath:mapper/assets/*.xml";

    @Value("${spring.datasource.assets.url:}")
    private String url;

    @Value("${spring.datasource.assets.username:}")
    private String username;

    @Value("${spring.datasource.assets.password:}")
    private String password;

    @Value("${spring.datasource.assets.driver-class-name:}")
    private String driverClassName;

    @Bean(name = "assetsDataSource")
    public DataSource assetsDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "assetsTransactionManager")
    public DataSourceTransactionManager assetsTransactionManager() {
        return new DataSourceTransactionManager(assetsDataSource());
    }

    @Bean(name = "assetsSqlSessionFactory")
    public SqlSessionFactory assetsSqlSessionFactory(@Qualifier("assetsDataSource") DataSource assetsDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(assetsDataSource);
        return sessionFactory.getObject();
    }

}

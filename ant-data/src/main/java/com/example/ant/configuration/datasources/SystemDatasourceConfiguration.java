package com.example.ant.configuration.datasources;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 描述：数据源配置
 * User: 曾远征
 * Date: 2018-08-31
 * Time: 15:49
 */
@Configuration
@MapperScan(basePackages = SystemDatasourceConfiguration.PACKAGE, sqlSessionFactoryRef = "systemSqlSessionFactory")
public class SystemDatasourceConfiguration {

    static final String PACKAGE = "com.example.ant.data.system.dao";
    static final String ALIASESPACKAGE = "com.example.ant.data.system.entity";
    static final String MAPPER_LOCATION = "classpath:mapper/system/*.xml";

    @Value("${spring.datasource.system.url:}")
    private String url;

    @Value("${spring.datasource.system.username:}")
    private String username;

    @Value("${spring.datasource.system.password:}")
    private String password;

    @Value("${spring.datasource.system.driver-class-name:}")
    private String driverClassName;

    @Bean(name = "systemDataSource")
    @Primary
    public DataSource systemDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "systemTransactionManager")
    @Primary
    public DataSourceTransactionManager systemTransactionManager() {
        return new DataSourceTransactionManager(systemDataSource());
    }

    @Bean(name = "systemSqlSessionFactory")
    @Primary
    public SqlSessionFactory systemSqlSessionFactory(@Qualifier("systemDataSource") DataSource systemDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(systemDataSource);
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources(SystemDatasourceConfiguration.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

    @Bean
    public SqlSessionTemplate userSqlSessionTemplate(@Qualifier("systemSqlSessionFactory") SqlSessionFactory systemSqlSessionFactory) throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(systemSqlSessionFactory); // 使用上面配置的Factory
        return template;
    }
}

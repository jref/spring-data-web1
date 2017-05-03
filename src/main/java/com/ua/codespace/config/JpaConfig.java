package com.ua.codespace.config;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories("com.ua.codespace.repository")
@Import(TransactionConfig.class)
public class JpaConfig {

    //todo: don't forget to set your database properties in the application.properties file
    @Value("${spring.data.db.url}")
    private String url;
    @Value("${spring.data.db.user}")
    private String user;
    @Value("${spring.data.db.password}")
    private String password;

    @Bean
    DataSource dataSource() {
        JdbcDataSource dataSource = new  JdbcDataSource();
        dataSource.setURL(url);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.H2);
        adapter.setShowSql(true);

        adapter.setGenerateDdl(true);
        adapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect");
        return adapter;
    }

    @Bean("entityManagerFactory")
    LocalContainerEntityManagerFactoryBean localContainerEMF(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean lcmfb = new LocalContainerEntityManagerFactoryBean();
        lcmfb.setDataSource(dataSource);
        lcmfb.setJpaVendorAdapter(jpaVendorAdapter);
        lcmfb.setPersistenceUnitName("bamboo");//todo: you can rename persistent unit
        lcmfb.setPackagesToScan("com.ua.codespace.model");
        return lcmfb;
    }

}

package com.example.demo.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.demo.streaming",
        entityManagerFactoryRef = "streamingEntityManagerFactory",
        transactionManagerRef = "streamingTransactionManager"
)
@EntityScan(
        basePackages = {"com.example.demo.streaming.domain"}
)
public class StreamingDataSourceConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean streamingEntityManagerFactory(
            @Qualifier("streamingDataSource") DataSource streamingDataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(streamingDataSource);
        em.setPackagesToScan("com.example.demo.streaming");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.hbm2ddl.auto", "update");
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public JpaTransactionManager streamingTransactionManager(
            @Qualifier("streamingEntityManagerFactory") EntityManagerFactory streamingEntityManagerFactory) {
        return new JpaTransactionManager(streamingEntityManagerFactory);
    }
}

package com.example.demo.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.demo.streaming",
        entityManagerFactoryRef = "streamingEntityManagerFactory",
        transactionManagerRef = "streamingTransactionManager"
)
public class StreamingDataSourceConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean streamingEntityManagerFactory(
            @Qualifier("streamingDataSource") DataSource streamingDataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(streamingDataSource);
        em.setPackagesToScan("com.example.demo.streaming");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return em;
    }

    @Bean
    public JpaTransactionManager streamingTransactionManager(
            @Qualifier("streamingEntityManagerFactory") EntityManagerFactory streamingEntityManagerFactory) {
        return new JpaTransactionManager(streamingEntityManagerFactory);
    }
}

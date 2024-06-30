package com.example.demo.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.demo.batch",
        entityManagerFactoryRef = "batchEntityManagerFactory",
        transactionManagerRef = "batchTransactionManager"
)
public class BatchDataSourceConfig {

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean batchEntityManagerFactory(
            @Qualifier("batchDataSource") DataSource batchDataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(batchDataSource);
        em.setPackagesToScan("com.example.demo.batch");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return em;
    }

    @Bean
    @Primary
    public JpaTransactionManager batchTransactionManager(
            @Qualifier("batchEntityManagerFactory") EntityManagerFactory batchEntityManagerFactory) {
        return new JpaTransactionManager(batchEntityManagerFactory);
    }
}

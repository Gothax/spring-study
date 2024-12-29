package com.gothaxcity.springbatchdemo.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.gothaxcity.springbatchdemo.repository",
        entityManagerFactoryRef = "dataEntityManagerFactory",
        transactionManagerRef = "dataTransactionManager"
)
public class DataDBConfig {

    @Bean(name = "dataDBSource")
    @ConfigurationProperties(prefix = "spring.datasource.data")
    public DataSource dataDBSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dataEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean dataEntityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("dataDBSource") DataSource dataSource) {
        return builder.dataSource(dataSource)
                .packages("com.gothaxcity.springbatchdemo.domain")
                .persistenceUnit("data")
                .build();
    }

    @Bean(name = "dataTransactionManager")
    public PlatformTransactionManager dataTransactionManager(
            @Qualifier("dataEntityManagerFactory") EntityManagerFactory dataEntityManagerFactory) {
        return new JpaTransactionManager(dataEntityManagerFactory);
    }



}

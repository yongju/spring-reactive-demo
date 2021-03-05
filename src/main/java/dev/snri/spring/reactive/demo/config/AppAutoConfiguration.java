package dev.snri.spring.reactive.demo.config;

import dev.snri.spring.reactive.demo.util.YamlPropertySourceFactory;
import com.zaxxer.hikari.HikariDataSource;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@PropertySource(value = {"classpath:app.yml"}, factory = YamlPropertySourceFactory.class)
@EnableJpaRepositories(basePackages = {PackagesConst.APP_JPA_REPOSITORIES})
@EnableR2dbcRepositories(basePackages = {PackagesConst.APP_REACTIVE_REPOSITORIES})
public class AppAutoConfiguration extends AbstractR2dbcConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSourceProperties appDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource appDataSource() {
        return appDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.jpa")
    public JpaProperties jpaProperties() {
        return new JpaProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.jpa.hibernate")
    public HibernateProperties hibernateProperties() {
        return new HibernateProperties();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
        var properties = hibernateProperties().determineHibernateProperties(
                jpaProperties().getProperties(), new HibernateSettings());
        return builder.dataSource(appDataSource())
                .properties(properties)
                .packages(PackagesConst.APP_DOMAIN)
                .persistenceUnit("app")
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory(builder).getObject()));
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.r2dbc")
    public R2dbcProperties appR2dbcProperties() {
        return new R2dbcProperties();
    }

    @Override
    public ConnectionFactory connectionFactory() {
        return ConnectionFactories.get(appR2dbcProperties().getUrl());
    }

}

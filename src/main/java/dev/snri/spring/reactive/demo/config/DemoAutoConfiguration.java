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
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.dialect.PostgresDialect;
import org.springframework.data.r2dbc.dialect.R2dbcDialect;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.r2dbc.core.DatabaseClient;

import javax.sql.DataSource;

@Configuration
@PropertySource(value = {"classpath:demo.yml"}, factory = YamlPropertySourceFactory.class)
@EnableJpaRepositories(
        basePackages = {PackagesConst.DEMO_JPA_REPOSITORIES},
        entityManagerFactoryRef = "demoEntityManagerFactory",
        transactionManagerRef = "demoTransactionManager"
)
@EnableR2dbcRepositories(
        basePackages = {PackagesConst.DEMO_REACTIVE_REPOSITORIES},
        entityOperationsRef = "demoR2dbcEntityOperation"
)
public class DemoAutoConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.demo.datasource")
    public DataSourceProperties demoDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource demoDataSource() {
        return demoDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean(name = "demoJpaProperties")
    @ConfigurationProperties(prefix = "spring.demo.jpa")
    public JpaProperties jpaProperties() {
        return new JpaProperties();
    }

    @Bean(name = "demoHibernateProperties")
    @ConfigurationProperties(prefix = "spring.demo.jpa.hibernate")
    public HibernateProperties hibernateProperties() {
        return new HibernateProperties();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean demoEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        var properties = hibernateProperties().determineHibernateProperties(
                jpaProperties().getProperties(), new HibernateSettings());
        return builder.dataSource(demoDataSource())
                .properties(properties)
                .packages(PackagesConst.DEMO_DOMAIN)
                .persistenceUnit("app")
                .build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.demo.r2dbc")
    public R2dbcProperties demoR2dbcProperties() {
        return new R2dbcProperties();
    }

    @Bean
    public ConnectionFactory demoR2dbcConnectionFactory() {
        return ConnectionFactories.get(demoR2dbcProperties().getUrl());
    }

    @Bean
    public R2dbcEntityOperations demoR2dbcEntityOperation() {
        DatabaseClient client = DatabaseClient.create(demoR2dbcConnectionFactory());
        return new R2dbcEntityTemplate(client, PostgresDialect.INSTANCE);
    }

}

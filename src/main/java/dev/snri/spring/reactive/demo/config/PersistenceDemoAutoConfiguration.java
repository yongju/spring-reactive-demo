package dev.snri.spring.reactive.demo.config;

import dev.snri.spring.reactive.demo.util.YamlPropertySourceFactory;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@Configuration
@PropertySource(value = {"classpath:persistence-demo.yml"}, factory = YamlPropertySourceFactory.class)
@EnableJpaRepositories(
        basePackages = {PersistencePackages.DEMO_PACKAGE},
        entityManagerFactoryRef = "demoEntityManagerFactory",
        transactionManagerRef = "demoTransactionManager"
)
public class PersistenceDemoAutoConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource-demo")
    public DataSourceProperties demoDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource demoDataSource() {
        return demoDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean(name = "demoJpaProperties")
    @ConfigurationProperties(prefix = "spring.jpa-demo")
    public JpaProperties jpaProperties() {
        return new JpaProperties();
    }

    @Bean(name = "demoHibernateProperties")
    @ConfigurationProperties(prefix = "spring.jpa-demo.hibernate")
    public HibernateProperties hibernateProperties() {
        return new HibernateProperties();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean demoEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        var properties = hibernateProperties().determineHibernateProperties(
                jpaProperties().getProperties(), new HibernateSettings());
        return builder.dataSource(demoDataSource())
                .properties(properties)
                .packages(PersistencePackages.DEMO_PACKAGE)
                .persistenceUnit("app")
                .build();
    }

}

package com.datacenter.dcsclient.extract.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
@Configuration
@EnableJpaRepositories(
  basePackages = "com.datacenter.dcsclient.extract.repository",
        entityManagerFactoryRef = "extractEntityManagerFactory",
        transactionManagerRef = "extractTransactionManager"
)
public class H2DatabaseConfig
{
       @Autowired
       private Environment env;
  
       @Primary
       @Bean
       @ConfigurationProperties(prefix="h2.datasource")
       public DataSourceProperties extractDataSourceProperties() {
           return new DataSourceProperties();
       }
    
       @Primary
       @Bean
       public DataSource extractDataSource() {
            DataSourceProperties extractDataSourceProperties = extractDataSourceProperties();
            return DataSourceBuilder.create()
           .driverClassName(extractDataSourceProperties.getDriverClassName())
           .url(extractDataSourceProperties.getUrl())
           .username(extractDataSourceProperties.getUsername())
           .password(extractDataSourceProperties.getPassword()) 
           .build();
       }
    
       
       @Primary
       @Bean
       public PlatformTransactionManager extractTransactionManager()
       {
           EntityManagerFactory factory = extractEntityManagerFactory().getObject();
           return new JpaTransactionManager(factory);
       }

       @Primary
       @Bean
       public LocalContainerEntityManagerFactoryBean extractEntityManagerFactory()
       {
           LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
           factory.setDataSource(extractDataSource());
           factory.setPackagesToScan(new String[]{"com.datacenter.dcsclient.domain"});
           factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        
           Properties jpaProperties = new Properties();
           jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
           jpaProperties.put("hibernate.show-sql", env.getProperty("spring.jpa.show-sql"));
           factory.setJpaProperties(jpaProperties);
        
           return factory;
       }
    
       
       @Primary
       @Bean
       public DataSourceInitializer extractDataSourceInitializer() 
       {
           DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
           dataSourceInitializer.setDataSource(extractDataSource());
           ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
           //databasePopulator.addScript(new ClassPathResource("extract-data.sql"));
           //dataSourceInitializer.setDatabasePopulator(databasePopulator);
           //dataSourceInitializer.setEnabled(env.getProperty("datasource.extract.initialize", Boolean.class, false));
           return dataSourceInitializer;
       }   
}
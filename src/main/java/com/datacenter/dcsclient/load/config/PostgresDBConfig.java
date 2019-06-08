package com.datacenter.dcsclient.load.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
  basePackages = "com.datacenter.dcsclient.load.repository",
        entityManagerFactoryRef = "loadEntityManagerFactory",
        transactionManagerRef = "loadTransactionManager"
)
public class PostgresDBConfig
{
       @Autowired
       private Environment env;
  
       @Bean
       @ConfigurationProperties(prefix="pg.datasource")
       public DataSourceProperties loadDataSourceProperties() {
           return new DataSourceProperties();
       }
    
       @Bean
       public DataSource loadDataSource() {
            DataSourceProperties loadDataSourceProperties = loadDataSourceProperties();
            return DataSourceBuilder.create()
           .driverClassName(loadDataSourceProperties.getDriverClassName())
           .url(loadDataSourceProperties.getUrl())
           .username(loadDataSourceProperties.getUsername())
           .password(loadDataSourceProperties.getPassword()) 
           .build();
       }
    
       @Bean
       public PlatformTransactionManager loadTransactionManager()
       {
           EntityManagerFactory factory = loadEntityManagerFactory().getObject();
           return new JpaTransactionManager(factory);
       }
       
       @Bean
       public JpaDialect jpaDialect() {
    		return new HibernateJpaDialect();
    	}

       @Bean
       public LocalContainerEntityManagerFactoryBean loadEntityManagerFactory()
       {
           LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
           factory.setDataSource(loadDataSource());
           factory.setPackagesToScan(new String[]{"com.datacenter.dcsclient.domain"});
           factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
           factory.setJpaDialect(jpaDialect());
           
           Properties jpaProperties = new Properties();
           jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
           jpaProperties.put("hibernate.show-sql", env.getProperty("spring.jpa.show-sql"));
           jpaProperties.put("hibernate.dialect",  env.getProperty("spring.jpa.properties.hibernate.dialect"));
           jpaProperties.put("spring.jpa.database","postgres");
           factory.setJpaProperties(jpaProperties);
        
           return factory;
       }
    
       @Bean
       public DataSourceInitializer loadDataSourceInitializer() 
       {
           DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
           dataSourceInitializer.setDataSource(loadDataSource());
           ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
           //databasePopulator.addScript(new ClassPathResource("load-data.sql"));
           //dataSourceInitializer.setDatabasePopulator(databasePopulator);
           //dataSourceInitializer.setEnabled(env.getProperty("datasource.load.initialize", Boolean.class, false));
           return dataSourceInitializer;
       }   
}
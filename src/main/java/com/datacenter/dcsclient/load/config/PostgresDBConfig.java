package com.datacenter.dcsclient.load.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "loadEntityManagerFactory", transactionManagerRef = "loadTransactionManager", basePackages = {
		"com.datacenter.dcsclient.load.repository" })
public class PostgresDBConfig {
 
	@Bean(name = "loadDataSource")
	@ConfigurationProperties(prefix = "pg.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "loadEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean barEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("loadDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.datacenter.dcsclient.domain").persistenceUnit("postgres")
				.build();
	}

	@Bean(name = "loadTransactionManager")
	public PlatformTransactionManager loadTransactionManager(
			@Qualifier("loadEntityManagerFactory") EntityManagerFactory loadEntityManagerFactory) {
		return new JpaTransactionManager(loadEntityManagerFactory);
	}
}
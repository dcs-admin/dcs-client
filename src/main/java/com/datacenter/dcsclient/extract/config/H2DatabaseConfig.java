package com.datacenter.dcsclient.extract.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "extractEntityManagerFactory", transactionManagerRef = "extractTransactionManager", basePackages = {
		"com.datacenter.dcsclient.extract.repository" })
public class H2DatabaseConfig {

	@Primary
	@Bean(name = "extractDataSource")
	@ConfigurationProperties(prefix = "h2.datasource")
	public DataSource extractDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean(name = "extractEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("extractDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.datacenter.dcsclient.domain").persistenceUnit("h2").build();
	}

	@Primary
	@Bean(name = "extractTransactionManager")
	public PlatformTransactionManager extractTransactionManager(
			@Qualifier("extractEntityManagerFactory") EntityManagerFactory extractEntityManagerFactory) {
		return new JpaTransactionManager(extractEntityManagerFactory);
	}
}
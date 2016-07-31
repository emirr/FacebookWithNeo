//package com.starter.config;
//
//import java.util.Properties;
//
//import org.apache.tomcat.jdbc.pool.DataSource;
//import org.hibernate.SessionFactory;
//import org.hibernate.jpa.HibernateEntityManagerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
//import org.springframework.jdbc.datasource.init.DataSourceInitializer;
//import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
////import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@Configuration
//@EnableTransactionManagement
//public class DatabaseConfig {
//
//	// Private fields
//
//	@Autowired
//	private Environment env;
//
//	@Autowired
//	private DataSource dataSource;
//
//	@Autowired
//	private LocalContainerEntityManagerFactoryBean entityManagerFactory;
//
//	/**
//	 * DataSource definition for database connection. Settings are read from the
//	 * application.properties file (using the env object).
//	 */
//	@Bean
//	public DataSource dataSource() {
//		DataSource dataSource = new DataSource();
//		dataSource.setDriverClassName(env.getProperty("db.driver"));
//		dataSource.setUrl(env.getProperty("db.url"));
//		dataSource.setUsername(env.getProperty("db.username"));
//		dataSource.setPassword(env.getProperty("db.password"));
//		return dataSource;
//	}
//
//	@Bean
//	public DataSourceInitializer databasePopulator() {
//		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
//		populator.addScript(
//				new ClassPathResource("org/springframework/social/connect/jdbc/JdbcUsersConnectionRepository.sql"));
//		populator.setContinueOnError(true); // Continue in case the create
//											// script already ran
//		DataSourceInitializer initializer = new DataSourceInitializer();
//		initializer.setDatabasePopulator(populator);
//		initializer.setDataSource(dataSource());
//		return initializer;
//	}
//
//	@Bean
//	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
//
//		entityManagerFactory.setDataSource(dataSource);
//
//		// Classpath scanning of @Component, @Service, etc annotated class
//		//entityManagerFactory.setPackagesToScan(env.getProperty("entitymanager.packagesToScan"));
//
//		// Vendor adapter
//		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//		entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
//
//		// Hibernate properties
//		Properties additionalProperties = new Properties();
//		additionalProperties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
//		additionalProperties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
//		additionalProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
//		// additionalProperties.put("hibernate.currentsessioncontext",env.getProperty("hibernate.currentsessioncontext"));
//		entityManagerFactory.setJpaProperties(additionalProperties);
//
//		return entityManagerFactory;
//	}
//
//	// session objelerinin üretilebilmesi için gerekli
//	// @Bean
//	// public HibernateJpaSessionFactoryBean sessionFactory() {
//	// return new HibernateJpaSessionFactoryBean();
//	// }
//	@Bean
//	public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf) {
//		return hemf.getSessionFactory();
//	}
//
//	@Bean
//	public JpaTransactionManager transactionManager() {
//		JpaTransactionManager transactionManager = new JpaTransactionManager();
//		transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
//		return transactionManager;
//	}
//
//	/**
//	 * PersistenceExceptionTranslationPostProcessor is a bean post processor
//	 * which adds an advisor to any bean annotated with Repository so that any
//	 * platform-specific exceptions are caught and then rethrown as one Spring's
//	 * unchecked data access exceptions (i.e. a subclass of
//	 * DataAccessException).
//	 */
//	@Bean
//	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
//		return new PersistenceExceptionTranslationPostProcessor();
//	}
//
//}

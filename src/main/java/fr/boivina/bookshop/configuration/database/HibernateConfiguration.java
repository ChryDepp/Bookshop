package fr.boivina.bookshop.configuration.database;

	import java.util.Properties;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

	/**
	 * Hibernate Configuration.
	 */
	@Configuration
	@EnableTransactionManagement
	@PropertySource({ "classpath:hibernate.properties" })
	public class HibernateConfiguration {

		@Inject
		private Environment environment;

		/**
		 * @param dataSource
		 *            datasource used by hibernate
		 * @param hibernateProperties
		 *            hibernate properties
		 * @param jpaVendorAdapter
		 *            used to tell which vendor-specific behavior to use to create
		 *            Spring's EntityManagerFactory creators
		 * @return localContainerEntityManagerFactoryBean used by spring to build
		 *         EntityManagerFactory
		 */
		@Bean
		public LocalContainerEntityManagerFactoryBean entityManagerFactoryContainer(
				DataSource dataSource, Properties hibernateProperties,
				JpaVendorAdapter jpaVendorAdapter) {
			LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
			localContainerEntityManagerFactoryBean.setDataSource(dataSource);
			localContainerEntityManagerFactoryBean
			.setPackagesToScan(new String[] { environment
					.getProperty("entities.package") });
			localContainerEntityManagerFactoryBean
			.setJpaProperties(hibernateProperties);
			localContainerEntityManagerFactoryBean
			.setJpaVendorAdapter(jpaVendorAdapter);
			return localContainerEntityManagerFactoryBean;
		}

		/**
		 * @return hibernate vendor adapter
		 */
		@Bean
		public JpaVendorAdapter hibernateJpaVendorAdapter() {
			return new HibernateJpaVendorAdapter();
		}

		@Bean
		public PlatformTransactionManager transactionManager(
				AbstractEntityManagerFactoryBean entityManagerFactory) {
			JpaTransactionManager transactionManager = new JpaTransactionManager();
			transactionManager.setEntityManagerFactory(entityManagerFactory
					.getObject());
			return transactionManager;
		}

	}

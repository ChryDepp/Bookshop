package fr.boivina.bookshop.configuration.database;

import java.sql.Driver;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;


@Configuration
@PropertySource({ "classpath:jpa/h2db.properties" })
public class H2DBConfiguration {
	
	@Inject
	protected Environment environment;
	
	@Bean
	@SuppressWarnings("unchecked")
	public DataSource dataSource() throws ClassNotFoundException {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriverClass((Class<? extends Driver>) Class
				.forName(environment.getProperty("dataSource.driverClass")));
		dataSource.setUrl(environment.getProperty("dataSource.url"));
		dataSource.setUsername(environment.getProperty("dataSource.username"));
		dataSource.setPassword(environment.getProperty("dataSource.password"));
		return dataSource;
	}
}

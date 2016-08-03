package br.com.igoraraujo91.voteNoRestaurante.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import br.com.igoraraujo91.voteNoRestaurante.dao.DAO;
import br.com.igoraraujo91.voteNoRestaurante.dao.RestaurantDAO;
import br.com.igoraraujo91.voteNoRestaurante.dao.VoteDAO;
import br.com.igoraraujo91.voteNoRestaurante.models.Restaurant;
import br.com.igoraraujo91.voteNoRestaurante.models.Vote;

@Configuration
@ComponentScan(basePackages = { "br.com.igoraraujo91.voteNoRestaurante.controllers" })
@EnableWebMvc
@EnableTransactionManagement
public class AppConfiguration extends WebMvcConfigurerAdapter{

	@Bean
	public FreeMarkerConfigurer freemarkerConfiguration(){
		FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
		configurer.setTemplateLoaderPaths("/WEB-INF/views/", "/staticResources/views/");
		return configurer;
	}

	@Bean
	public FreeMarkerViewResolver freeMarkerViewResolver(){
		FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();

		viewResolver.setCache(true);
		viewResolver.setPrefix("");
		viewResolver.setSuffix("");

		return viewResolver;
	}

	@Bean
	public DataSource dataSource(){
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.DERBY)
				.build();
	}

	@Autowired
	@Bean
	public SessionFactory sessionFactory(DataSource dataSource){
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);

		sessionBuilder.scanPackages("br.com.igoraraujo91.voteNoRestaurante.models");

		Properties properties = new Properties();
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "create");
		properties.put("hibernate.hbm2ddl.import_files", "../initialData.sql");

		sessionBuilder.addProperties(properties);

		return sessionBuilder.buildSessionFactory();
	}

	@Autowired
	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
		return new HibernateTransactionManager(sessionFactory);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry){
		registry.addResourceHandler("/staticResources/**").addResourceLocations("/staticResources/");
	}

	@Bean
	public DAO<Restaurant> restaurantDao(){
		return new RestaurantDAO();
	}

	@Bean
	public DAO<Vote> voteDao(){
		return new VoteDAO();
	}

}

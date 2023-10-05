package com.safesmart.safesmart.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Profile("clientremote")
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.safesmart.safesmart.repository",
           entityManagerFactoryRef = "localEntityManagerFactory",
           transactionManagerRef = "localTransactiopnManager")
public class DatasourceConfigLocal {
	
	@Value("${spring.datasource1.url}")
	private String url;
	
	@Value("${spring.datasource1.username}")
	private String username;
	
	@Value("${spring.datasource1.password}")
	private String password;
	
	@Value("${spring.datasource1.driverClassName}")
	private String driverClassName;
	
	@Primary
	@Bean
    public DataSource localDataSource() {
		 return DataSourceBuilder.create().username(username).password(password).url(url).driverClassName(driverClassName).build();
	}

 
    @Primary
    @Bean(name = "localEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory1(EntityManagerFactoryBuilder builder, @Qualifier("localDataSource") DataSource dataSource) {
      
    	LocalContainerEntityManagerFactoryBean bean =new LocalContainerEntityManagerFactoryBean();
    	bean.setDataSource(dataSource);
    	JpaVendorAdapter adapter=new HibernateJpaVendorAdapter();
    	bean.setJpaVendorAdapter(adapter);
    	Map<String, String> props=new HashMap<>();
    	props.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
    	props.put("hibernate.show_sql", "true");
    	props.put("hibernate.hbm2ddl.auto", "update");
    	bean.setJpaPropertyMap(props);
    	bean.setPackagesToScan("com.safesmart.safesmart.model","com.safesmart.safesmart.dto");
    	return bean;
    	
    	
    	
    	
//    	return builder
//                .dataSource(dataSource)
//                .packages("com.safesmart.safesmart.model","com.safesmart.safesmart.dto") // Package containing the entity classes for the first database
//                .persistenceUnit("remotesafedb")
//                .build();
    }

   

    @Primary
    @Bean(name = "localTransactiopnManager")
    public PlatformTransactionManager transactionManager1(@Qualifier("localEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}

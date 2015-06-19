package com.petro.span.server.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.petro.span.server.spring.service.AuthorizationService;
import com.petro.span.server.spring.service.AuthorizationServiceImpl;
import com.petro.span.server.spring.service.LoginService;
import com.petro.span.server.spring.service.LoginServiceImpl;
import com.petro.span.server.spring.service.RegistrationService;
import com.petro.span.server.spring.service.RegistrationServiceImpl;



@Configuration
@ComponentScan(basePackages ={"com.petro.span.server.spring"})
public class AppConfig {
//
//	@Bean
//	AdminExpanseDaoImpl expanseDao(){
//		AdminExpanseDaoImpl expanseDao = new AdminExpanseDaoImpl();
//		expanseDao.setDataSource(getDataSource());
//		return expanseDao;
//
//	}

	@Bean
	LoginService gloginService(){
		return new LoginServiceImpl();
	}
	
	
	
	@Bean
	AuthorizationService authService(){
		return new AuthorizationServiceImpl();
	}
	
	
	@Bean
	RegistrationService registrationService(){
		return new RegistrationServiceImpl();
	}
//	@Bean
//	BigQueryService bigQueryService(){
//		return new BigQueryServiceImpl();
//	}


//	
//	@Bean
//	ExportExcelReport exportExcelReport(){
//		return new ExportExcelReport();
//	}


//	@Bean(name = "dtSource")
//	public DataSource getDataSource() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//		dataSource.setUrl("jdbc:sqlserver://192.168.101.3:1433;database=VHNDEP");
//		dataSource.setUsername("appsdarcl");
//		dataSource.setPassword("appsdarcl");
//
//		return dataSource;
//	}
}

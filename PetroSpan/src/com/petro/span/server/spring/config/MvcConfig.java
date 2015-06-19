package com.petro.span.server.spring.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@Import(AppConfig.class)
@EnableWebMvc
@ComponentScan(basePackages ={ "com.petro.span.server.controller" })
public class MvcConfig extends WebMvcConfigurerAdapter{

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}



	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(jacksonConverter());
	}

	@Bean
	MappingJackson2HttpMessageConverter jacksonConverter() {
		return new MappingJackson2HttpMessageConverter();
	}




}

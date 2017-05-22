package com.ynyes.lyz.config;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ynyes.lyz.controller.filter.VerwalterFilter;

@Configuration
public class VerwalterConfiguration {

	@Bean
	public FilterRegistrationBean verwalterFilter() {
		FilterRegistrationBean bean = new FilterRegistrationBean(new VerwalterFilter());
		bean.addUrlPatterns("/Verwalter/*");
		return bean;
	}
}

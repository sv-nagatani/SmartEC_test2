package com.example.demo.smartec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SmartEcTest2Application {

	public static void main(String[] args) {
		SpringApplication.run(SmartEcTest2Application.class, args);
	}

	@Bean
	public FilterRegistrationBean<?> loggingFilter() {
		FilterRegistrationBean<LoggingFilter> registrationBean = new FilterRegistrationBean<>(new LoggingFilter());
		registrationBean.setOrder(Integer.MIN_VALUE);
		registrationBean.addUrlPatterns("/*");
		return registrationBean;
	}
}

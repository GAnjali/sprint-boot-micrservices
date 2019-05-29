package com.rest.webservices.restfulwebservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestfulWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}

//	@Bean
//	public LocaleResolver localeResolver(){
//		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
//		localeResolver.setDefaultLocale(Locale.US);
//		return localeResolver;
//	}
//
//	@Bean
//	public ResourceBundleMessageSource bundleMessageSource(){
//		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//		messageSource.setBasename("messages");
//		return messageSource;
//	}
}

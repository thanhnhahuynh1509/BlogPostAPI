package com.htn.blog.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	private ApiInfo apiInfo() {
		return new ApiInfo(
				"Spring Boot Blog REST APIs", 
				"Spring Boot Blog REST API Documentation",
				"1", 
				"Term of service", 
				new Contact("", "", ""), 
				"License of API",
				"API license URL", 
				Collections.emptyList()
		);
	}
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.htn.blog.controller"))
				.paths(PathSelectors.any())
				.build();
	}
	
}

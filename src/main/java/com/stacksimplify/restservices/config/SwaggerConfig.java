package com.stacksimplify.restservices.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {
	@Bean
	public Docket api()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getApiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.stacksimplify.restservices"))
				.paths(PathSelectors.ant("/users/**"))
				/*
				 .apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				 */
				.build();
	}
	//Swaggger Metadata http://localhost:8080/v2/api-docs
	//Swagger UI URL: http://localhost:8080/swagger-ui.html
	private ApiInfo getApiInfo()
	{
		return new ApiInfoBuilder().title("USerManagement service")
				.description("List all APIs")
				.version("2.0")
				.contact(new Contact("Reshma", "http://ww.dd.com", "sdsd@gmail.com"))
				.license("licence2.0")
				.licenseUrl("http://licenceURL.com/")
				.build();
	}
}

package com.caldeira.blog.config.swagger;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.caldeira.blog.model.User;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfigurations {

	@Bean
	public Docket bloggingApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.caldeira.blog")).paths(PathSelectors.ant("/**")).build()
				// ignore 'cause of the passwords
				.ignoredParameterTypes(User.class)
				// Generate key for jwt
				.globalOperationParameters(
						Arrays.asList(new ParameterBuilder().name("Authorization").description("Header para Token JWT")
								.modelRef(new ModelRef("string")).parameterType("header").required(false).build()));

	}
}

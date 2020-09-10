package com.tom.common.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	@Bean
	public Docket mobileAPI() {

		return new Docket(DocumentationType.SWAGGER_2).groupName("Mobile APIs").apiInfo(mobileAPIInfo()).select()
				.apis(RequestHandlerSelectors.any()).paths(PathSelectors.regex(".*tom.*")).build();
	}

	private ApiInfo mobileAPIInfo() {
		return new ApiInfoBuilder().title("OmniMD Mobile API Documentaion")
				.description("This documentation contains information about OmniOne Mobile based APIs")
				.termsOfServiceUrl(null).license(null).licenseUrl(null).version("1.0.0002").build();
	}

}
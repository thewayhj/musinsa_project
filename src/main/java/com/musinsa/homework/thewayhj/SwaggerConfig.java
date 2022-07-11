package com.musinsa.homework.thewayhj;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.sql.Timestamp;

/**
 * swagger config
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@SuppressWarnings("deprecation")
	private ApiInfo apiInfo() {
		return new ApiInfo(
				"Musinsa Homework API",
				"API Documentation for Musinsa Homework",
				"1.0",
				null, null, null, null )
				;
	}

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
        		//.useDefaultResponseMessages(false)
        		.apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/**"))
                .build()
                .directModelSubstitute(Timestamp.class, Long.class)
                ;
    }
}
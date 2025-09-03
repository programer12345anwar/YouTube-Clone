package com.youtube.central.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI centralApiService(){
        return new OpenAPI()
                .info(new Info().title("Central Api Service ")
                        .description("This is the REST API for Central Api Service")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("You can refer to the Central Api  Service wiki Documentation")
                        .url("http://centralapi-service-dummy-url.com/docs"));
    }
}

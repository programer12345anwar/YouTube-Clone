package com.youtube.notification_api.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI notificationServiceApi(){
        return new OpenAPI()
                .info(new Info().title("Notification Service Api")
                        .description("This is the REST API for Notification Service")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("You can refer to the Notification Service wiki Documentation")
                        .url("http://notification-service-dummy-url.com/docs"));
    }
}

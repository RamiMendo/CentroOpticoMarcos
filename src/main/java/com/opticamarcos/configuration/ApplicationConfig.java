package com.opticamarcos.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public OpenAPI customOpenAPI(@Value("V1") String appVersion){
        return new OpenAPI().info(new Info().title("API Centro Optico Marcos")
                .version(appVersion)
                .description("Centro Optico Marcos")
                .termsOfService("https://google.com")
                .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0")));
    }

}

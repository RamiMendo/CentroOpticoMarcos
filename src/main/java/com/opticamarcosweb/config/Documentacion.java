package com.opticamarcosweb.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Documentacion {

    @Bean
    public OpenAPI customOpenAPI(@Value("V1") String AppVersion) {
        return new OpenAPI().info(new Info().title("API Centro Optico Marcos")
                .version(AppVersion)
                .description("Centro Optico Marcos")
                .termsOfService("http://google.com")
                .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0")));

    }
}

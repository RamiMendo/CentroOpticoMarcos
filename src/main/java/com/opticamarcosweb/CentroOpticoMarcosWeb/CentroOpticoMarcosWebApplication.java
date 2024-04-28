package com.opticamarcosweb.CentroOpticoMarcosWeb;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;


@SpringBootApplication
@EntityScan(basePackages = "com.opticamarcosweb.model")
@ComponentScan(basePackages = {"com.opticamarcosweb"})
@EnableJpaRepositories(basePackages = { "com.opticamarcosweb.repository" })
public class CentroOpticoMarcosWebApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(CentroOpticoMarcosWebApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI(@Value("V1") String AppVersion) {
		return new OpenAPI().info(new Info().title("API Centro Optico Marcos")
				.version(AppVersion)
				.description("Centro Optico Marcos")
				.termsOfService("http://google.com")
				.license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0")));
		
	}
}

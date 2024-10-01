package com.opticamarcos.CentroOpticoMarcos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.opticamarcos.model")
@ComponentScan(basePackages = {"com.opticamarcos"})
@EnableJpaRepositories(basePackages = {"com.opticamarcos.repository"})
public class CentroOpticoMarcosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentroOpticoMarcosApplication.class, args);
	}

}

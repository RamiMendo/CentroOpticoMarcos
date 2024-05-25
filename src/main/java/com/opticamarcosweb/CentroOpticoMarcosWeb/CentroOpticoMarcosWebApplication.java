package com.opticamarcosweb.CentroOpticoMarcosWeb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.opticamarcosweb.model")
@ComponentScan(basePackages = {"com.opticamarcosweb"})
@EnableJpaRepositories(basePackages = { "com.opticamarcosweb.repository" })
public class CentroOpticoMarcosWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentroOpticoMarcosWebApplication.class, args);
	}


}

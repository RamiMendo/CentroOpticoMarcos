package com.opticamarcosweb.CentroOpticoMarcosWeb;

import com.opticamarcosweb.repository.ClienteRepository;
import com.opticamarcosweb.service.FichaService;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private FichaService fichaService;

	@Autowired
	private ClienteRepository clienteRepository;

	public static void main(String[] args) {
		SpringApplication.run(CentroOpticoMarcosWebApplication.class, args);
	}


}

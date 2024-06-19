package com.prueb.tecnica.paises;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@EntityScan("com.prueb.tecnica.paises.model")
@ComponentScan(
	basePackages = {"com.prueb.tecnica.paises" }, 
	excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, 
	pattern = {"com\\.prueb\\.tecnica\\.paises\\.model\\..*" }))

public class PaisesApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(PaisesApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PaisesApplication.class);
	}

}

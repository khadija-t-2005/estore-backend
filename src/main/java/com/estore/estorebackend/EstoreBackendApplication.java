package com.estore.estorebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.estore")
@EntityScan(basePackages = "com.estore")
public class EstoreBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstoreBackendApplication.class, args);
		System.out.println("✅ E-Store Application démarrée avec succès!");
	}
}
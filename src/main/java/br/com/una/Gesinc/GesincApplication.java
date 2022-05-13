package br.com.una.Gesinc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GesincApplication {

	public static void main(String[] args) {
		SpringApplication.run(GesincApplication.class, args);
	}

}

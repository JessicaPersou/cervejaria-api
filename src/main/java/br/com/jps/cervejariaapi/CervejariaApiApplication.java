package br.com.jps.cervejariaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class CervejariaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CervejariaApiApplication.class, args);
	}

}

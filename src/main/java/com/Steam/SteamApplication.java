package com.Steam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
			title = "Steam API",
			version ="1.0",
			description = "Documentando uma API que gerencia jogos e possui tratamento de erros personalizados", 
			contact = @Contact(name = "Carlos",email = "crrsj1@gmail.com")	
				
		)	
			
		
	)

public class SteamApplication {

	public static void main(String[] args) {
		SpringApplication.run(SteamApplication.class, args);
	}

}

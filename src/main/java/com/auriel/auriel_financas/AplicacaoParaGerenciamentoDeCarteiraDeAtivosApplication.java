package com.auriel.auriel_financas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling 
public class AplicacaoParaGerenciamentoDeCarteiraDeAtivosApplication {

	public static void main(String[] args) {
		SpringApplication.run(AplicacaoParaGerenciamentoDeCarteiraDeAtivosApplication.class, args);
	}

}

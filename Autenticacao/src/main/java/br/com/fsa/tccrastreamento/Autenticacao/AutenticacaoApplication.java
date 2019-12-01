package br.com.fsa.tccrastreamento.Autenticacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan("br.com.fsa.tccrastreamento.Autenticacao.modelo")
public class AutenticacaoApplication {

	public static void main(String[] args) {
		System.out.println("VERSÃO TESTE DOCKER");
		SpringApplication.run(AutenticacaoApplication.class, args);
	}

}

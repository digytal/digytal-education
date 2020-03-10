package digytal.springdicas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import digytal.springdicas.components.service.AcessoService;

@SpringBootApplication
public class SwaggerJwtPerfilAcessoApplication {
	public static void main(String[] args) {
		SpringApplication.run(SwaggerJwtPerfilAcessoApplication.class, args);
	}
	@Bean
	public CommandLineRunner run(AcessoService service) {
		return args -> {
			service.configuracaoInicial();
		};
	}
}

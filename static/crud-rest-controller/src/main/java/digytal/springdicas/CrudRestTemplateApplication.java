package digytal.springdicas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudRestTemplateApplication {
	//https://github.com/springfox/springfox/issues/1503
	//https://www.treinaweb.com.br/blog/documentando-uma-api-spring-boot-com-o-swagger/
	public static void main(String[] args) {
		SpringApplication.run(CrudRestTemplateApplication.class, args);
	}

}

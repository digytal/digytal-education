package digytal.springdicas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import digytal.springdicas.util.StartHSQLDB;

@SpringBootApplication
public class JpaMappingEntityApplication {
	public static void main(String[] args) {
		try {
			StartHSQLDB.main(new String[] {});
			Thread.sleep(1000L);
			SpringApplication.run(JpaMappingEntityApplication.class, args);
		}catch (Exception e) {
			
		}
	}

}

package digytal.springdicas;

import javax.swing.UIManager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import digytal.springdicas.beans.Contexto;
import digytal.springdicas.desktop.FrmLogin;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		try {
			String lf = UIManager.getSystemLookAndFeelClassName();
			UIManager.setLookAndFeel(lf);
			Contexto.exibirSplash();
			SpringApplication.run(Application.class, args);
			
		} catch (Exception e) {
			//logger.error(e);
			System.exit(0);
		}
		
	}
	@Bean
	public CommandLineRunner run( FrmLogin login) {
		return args -> {
			//DesktopApp.exibirSplash();
			login.exibir();
		};
	}
	
}


/**
 
 
 @SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private ApplicationContext appContext;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        String[] beans = appContext.getBeanDefinitionNames();
        Arrays.sort(beans);
        for (String bean : beans) {
            System.out.println(bean);
        }

    }

}
 
 */

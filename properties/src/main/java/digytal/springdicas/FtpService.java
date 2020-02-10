package digytal.springdicas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FtpService {
	
	@Autowired
	private FtpCredencial credencial;
	
	public void conectar() {
		credencial = new FtpCredencial();
		credencial.setHost("localhost");
		credencial.setPort(21);
		credencial.setUser("digytal");
		credencial.setPass("dicas");
		
		System.out.println(String.format("\n\nConectando ao serviço FTP: host: %s, port: %d, user: %s, password: %s", credencial.getHost(), credencial.getPort(), credencial.getUser(),credencial.getPass()));
	}
}

package digytal.springdicas.core.cadastro;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import digytal.springdicas.api.constants.Profiles;
import digytal.springdicas.api.model.cadastro.Cliente;
import digytal.springdicas.api.service.cadastro.ClienteService;
import digytal.springdicas.core.JpaRepository;

@Service
@Profile(Profiles.CORE)
public class ClienteServiceBean extends JpaRepository<Cliente, Integer> implements ClienteService {

}

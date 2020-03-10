package digytal.springdicas.components.repository.acesso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import digytal.springdicas.core.domain.acesso.Permissao;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, String>{
	
}

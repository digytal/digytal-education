package digytal.springdicas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import digytal.springdicas.domain.acesso.Permissao;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, String>{
	
}

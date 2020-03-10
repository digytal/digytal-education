package digytal.springdicas.components.repository.acesso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import digytal.springdicas.core.domain.acesso.Funcionalidade;

@Repository
public interface FuncionalidadeRepository extends JpaRepository<Funcionalidade, String>{
	
}

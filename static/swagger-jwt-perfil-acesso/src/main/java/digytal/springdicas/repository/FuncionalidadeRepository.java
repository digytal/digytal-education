package digytal.springdicas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import digytal.springdicas.domain.acesso.Funcionalidade;

@Repository
public interface FuncionalidadeRepository extends JpaRepository<Funcionalidade, String>{
	
}

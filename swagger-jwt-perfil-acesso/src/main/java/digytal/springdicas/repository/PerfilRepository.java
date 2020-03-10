package digytal.springdicas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import digytal.springdicas.domain.acesso.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Integer>{
	
}

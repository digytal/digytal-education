package digytal.springdicas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import digytal.springdicas.domain.acesso.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	public Usuario findByLogin(String login);
}

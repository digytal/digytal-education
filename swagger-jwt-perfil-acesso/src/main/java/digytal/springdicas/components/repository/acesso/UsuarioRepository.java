package digytal.springdicas.components.repository.acesso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import digytal.springdicas.core.domain.acesso.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	public Usuario findByLogin(String login);
}

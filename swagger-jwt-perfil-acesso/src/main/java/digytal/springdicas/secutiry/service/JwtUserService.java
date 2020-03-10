package digytal.springdicas.secutiry.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import digytal.springdicas.domain.acesso.Usuario;
import digytal.springdicas.repository.UsuarioRepository;


@Service(value = "userService")
public class JwtUserService implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository repository;
	
	@PersistenceContext
	private EntityManager em;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = repository.findByLogin(username);
		if(user == null){
			throw new UsernameNotFoundException("Usuário não existe");
		}
		Set<SimpleGrantedAuthority> roles=getAuthority(user);
		return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getSenha(), roles);
	}
	private Set<SimpleGrantedAuthority> getAuthority(Usuario user) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ps.id_permissao FROM tab_acs_usuario u  ");
		sb.append("JOIN tab_acs_usuario_perfil up ON u.id_usuario = up.cd_usuario ");
		sb.append("JOIN tab_acs_perfil p ON p.id_perfil = up.cd_perfil ");
		sb.append("JOIN tab_acs_perfil_permissao pp ON up.cd_perfil = pp.cd_perfil  ");   
		sb.append("JOIN tab_acs_permissao ps ON ps.id_permissao = pp.cd_permissao ");
		sb.append("JOIN tab_acs_funcionalidade f ON f.id_funcionalidade = ps.cd_funcionalidade ");
		sb.append("WHERE up.cd_usuario = :idUsuario ");
		sb.append("GROUP BY ps.id_permissao ");  
		sb.append("ORDER BY ps.id_permissao ");
		List<String> lista = em.createNativeQuery(sb.toString()).setParameter("idUsuario", user.getId()).getResultList();
		
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		
		lista.forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
		});
		return authorities;
	}

}

package digytal.springdicas.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import digytal.springdicas.domain.acesso.Funcionalidade;
import digytal.springdicas.domain.acesso.Perfil;
import digytal.springdicas.domain.acesso.Permissao;
import digytal.springdicas.domain.acesso.Usuario;
import digytal.springdicas.domain.acesso.enums.TipoOperacao;
import digytal.springdicas.repository.FuncionalidadeRepository;
import digytal.springdicas.repository.PerfilRepository;
import digytal.springdicas.repository.PermissaoRepository;
import digytal.springdicas.repository.UsuarioRepository;

/**
 * 
 * Voce pode criar seus services em classes diferentes
 * Implementação meramente ilustrativa
 *
 */

@Service
public class AcessoService {
	@Autowired
	private FuncionalidadeRepository funcionalidadeRepository;
	
	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public void configuracaoInicial() {
		List<Permissao> permissoesUser = null;
		if(usuarioRepository.findByLogin("user")==null) {
		
			Funcionalidade funcionalidade = new Funcionalidade();
			funcionalidade.setId("FUNCIONALIDADES");
			funcionalidade.setNome("FUNCIONALIDADES");
			funcionalidade.setModulo("ACESSO");
			funcionalidadeRepository.save(funcionalidade);
			
			 permissoesUser = criarPermissoes(funcionalidade);
			
			
			Perfil perfil =new Perfil();
			perfil.setExterno(false);
			perfil.setNome("USER");
			perfil.setPermissoes(permissoesUser);
			perfilRepository.save(perfil);
			
			Usuario user = new Usuario();
			user.setLogin("user");
			String senha = encoder.encode("user");
			user.setSenha(senha);
			user.setNome("USUARIO COMUM");
			user.setEmail("usuario.comum@digytal.com.br");
			
			user.getPerfis().add(perfil);
			
			usuarioRepository.save(user);
		}
		if(usuarioRepository.findByLogin("master")==null) {
			
			Funcionalidade funcionalidade1 = new Funcionalidade();
			funcionalidade1.setId("PERFIS");
			funcionalidade1.setNome("PERFIS");
			funcionalidade1.setModulo("ACESSO");
			funcionalidadeRepository.save(funcionalidade1);
			
			
			Funcionalidade funcionalidade2 = new Funcionalidade();
			funcionalidade2.setId("PERMISSOES");
			funcionalidade2.setNome("PERMISSOES");
			funcionalidade2.setModulo("ACESSO");
			funcionalidadeRepository.save(funcionalidade2);
			
			
			List<Permissao> permissoes = new ArrayList<Permissao>();
			permissoes.addAll(permissoesUser);
			permissoes.addAll(criarPermissoes(funcionalidade1));
			permissoes.addAll(criarPermissoes(funcionalidade2));
			
			Perfil perfil =new Perfil();
			perfil.setExterno(false);
			perfil.setNome("MASTER");
			perfil.setPermissoes(permissoes);
			perfilRepository.save(perfil);
			
			Usuario user = new Usuario();
			user.setLogin("master");
			String senha = encoder.encode("master");
			user.setSenha(senha);
			user.setNome("USUARIO MASTER");
			user.setEmail("usuario.master@digytal.com.br");
			
			user.getPerfis().add(perfil);
			
			usuarioRepository.save(user);
		}
	}
	@Transactional
	public List<Permissao> criarPermissoes(Funcionalidade funcionalidade){
		List<Permissao> permissoes = new ArrayList<Permissao>();
		for(TipoOperacao tp:TipoOperacao.values()) {
			Permissao permissao = new Permissao();
			permissao.setAcao(tp.getNome());
			permissao.setFuncionalidade(funcionalidade.getId());
			permissao.setId(funcionalidade.getId() + "_"+tp.name());
			permissoes.add(permissao);
			permissaoRepository.save(permissao);
		}
		return permissoes;
	}
}

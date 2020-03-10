package digytal.springdicas.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import digytal.springdicas.domain.acesso.Funcionalidade;
import digytal.springdicas.domain.acesso.Perfil;
import digytal.springdicas.domain.acesso.Permissao;
import digytal.springdicas.domain.roles.RolesAcesso;
import digytal.springdicas.repository.FuncionalidadeRepository;
import digytal.springdicas.repository.PerfilRepository;
import digytal.springdicas.repository.PermissaoRepository;

/**
 * 
 * Voce pode criar seus recursos em classes diferentes
 * Implementação meramente ilustrativa
 *
 */
@RestController
@RequestMapping("/acesso")
public class AcessoResource {
	@Autowired
	private FuncionalidadeRepository funcionalidadeRepository;
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@PostMapping(path="/funcionalidades")
	@PreAuthorize(RolesAcesso.FUNCIONALIDADES_INSERT)
	public void incluirFuncionalidade(@RequestBody Funcionalidade entidade) {
		funcionalidadeRepository.save(entidade);
	}
	
	@GetMapping(path="/funcionalidades")
	@PreAuthorize(RolesAcesso.FUNCIONALIDADES_SEARCH)
	public List<Funcionalidade> listasFuncionalidades() {
		return funcionalidadeRepository.findAll();
	}
	
	@PostMapping(path="/perfis")
	@PreAuthorize(RolesAcesso.PERFIL_INSERT)
	public void incluirPerfil(@RequestBody Perfil entidade) {
		perfilRepository.save(entidade);
	}
	
	@GetMapping(path="/perfis")
	@PreAuthorize(RolesAcesso.PERFIL_SEARCH)
	public List<Perfil> listarPerfis() {
		return perfilRepository.findAll();
	}
	
	@PostMapping(path="/permissoes")
	@PreAuthorize(RolesAcesso.PERMISSAO_INSERT)
	public void incluirPermissao(@RequestBody Permissao entidade) {
		permissaoRepository.save(entidade);
	}
	
	@GetMapping(path="/permissoes")
	@PreAuthorize(RolesAcesso.PERMISSAO_SEARCH)
	public List<Permissao> listarPermissao() {
		return permissaoRepository.findAll();
	}
}

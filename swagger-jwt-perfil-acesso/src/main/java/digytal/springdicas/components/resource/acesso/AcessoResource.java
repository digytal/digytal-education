package digytal.springdicas.components.resource.acesso;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import digytal.springdicas.components.repository.acesso.FuncionalidadeRepository;
import digytal.springdicas.components.repository.acesso.PerfilRepository;
import digytal.springdicas.components.repository.acesso.PermissaoRepository;
import digytal.springdicas.core.domain.acesso.Funcionalidade;
import digytal.springdicas.core.domain.acesso.Perfil;
import digytal.springdicas.core.domain.acesso.Permissao;
import digytal.springdicas.core.roles.RolesAcesso;

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
	
	@PostMapping
	@PreAuthorize(RolesAcesso.FUNCIONALIDADES_INSERT)
	public void incluirFuncionalidade(@RequestBody Funcionalidade entidade) {
		funcionalidadeRepository.save(entidade);
	}
	
	@GetMapping
	@PreAuthorize(RolesAcesso.FUNCIONALIDADES_SEARCH)
	public List<Funcionalidade> listasFuncionalidades() {
		return funcionalidadeRepository.findAll();
	}
	
	@PostMapping
	@PreAuthorize(RolesAcesso.PERFIL_INSERT)
	public void incluirPerfil(@RequestBody Perfil entidade) {
		perfilRepository.save(entidade);
	}
	
	@GetMapping
	@PreAuthorize(RolesAcesso.PERFIL_SEARCH)
	public List<Perfil> listarPerfis() {
		return perfilRepository.findAll();
	}
	
	@PostMapping
	@PreAuthorize(RolesAcesso.PERMISSAO_INSERT)
	public void incluirPermissao(@RequestBody Permissao entidade) {
		permissaoRepository.save(entidade);
	}
	
	@GetMapping
	@PreAuthorize(RolesAcesso.PERMISSAO_SEARCH)
	public List<Permissao> listarPermissao() {
		return permissaoRepository.findAll();
	}
}

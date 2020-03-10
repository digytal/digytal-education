package digytal.springdicas.domain.roles;

public interface RolesAcesso {
	static final String USUARIOS_SEARCH= "hasRole('USUARIOS_SEARCH')";
	
    static final String FUNCIONALIDADES_INSERT= "hasRole('FUNCIONALIDADES_INSERT')";
    static final String FUNCIONALIDADES_SEARCH= "hasRole('FUNCIONALIDADES_SEARCH')";
    

    static final String PERFIL_INSERT= "hasRole('PERFIS_INSERT')";
    static final String PERFIL_SEARCH= "hasRole('PERFIS_SEARCH')";

    static final String PERMISSAO_INSERT= "hasRole('PERMISSAO_INSERT')";
    static final String PERMISSAO_SEARCH= "hasRole('PERMISSAO_SEARCH')";

}

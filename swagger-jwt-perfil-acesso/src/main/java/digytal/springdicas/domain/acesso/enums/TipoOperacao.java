package digytal.springdicas.domain.acesso.enums;

public enum TipoOperacao {
	INSERT("Incluir"),
	UPDATE("Alterar"),
	DELETE("Excluir"),
	VIEW("Visualizar"),
	SEARCH ("Pesquisar"),
	MENU ("Menu");
	private TipoOperacao(String nome) {
		this.nome=nome;
	}
	private String nome;
	public String getNome() {
		return nome;
	}
	public static void perfilPermissao(Integer perfil, String funcionalidade) {
		for(TipoOperacao tp: TipoOperacao.values()) {
			System.out.println(String.format("INSERT INTO TAB_ACS_PERFIL_PERMISSAO(ID_PERFIL, ID_PERMISSAO)VALUES(%d, '%s_%s');", perfil, funcionalidade,tp.name() ));
		}
	}
	public static void main(String[] args) {
		perfilPermissao(1, "GRUPOS");
	}
}
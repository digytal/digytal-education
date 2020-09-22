package digytal.springdicas.domain.acesso;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tab_acs_permissao")
public class Permissao  {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id_permissao",length=50)
	private String id;
	@Column(name = "cd_funcionalidade",length=50)
	private String funcionalidade;

	private String acao;

	private boolean excluido;

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	public boolean isExcluido() {
		return excluido;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFuncionalidade() {
		return funcionalidade;
	}

	public void setFuncionalidade(String funcionalidade) {
		this.funcionalidade = funcionalidade;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Permissao)) {
			return false;
		}
		Permissao other = (Permissao) obj;
		if (id == null) {
			return false;
		}
		if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return String.format("Permissao{id:%s}", id);
	}

}

package digytal.springdicas.core.domain.acesso;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name = "tab_acs_funcionalidade")
public class Funcionalidade {
	
	@Id 
	@Column(name = "id_funcionalidade",length=50)
	private String id;
	@Column(name = "nome_funcionalidade",length=50)
	private String nome;
	@Column(name = "cd_modulo",length=50)
	private String modulo;
	
	private boolean externa;
	
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
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getModulo() {
		return modulo;
	}
	public void setModulo(String modulo) {
		this.modulo = modulo;
	}
	public boolean getExterna() {
		return externa;
	}
	
	public void setExterna(boolean externa) {
		this.externa = externa;
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
		if (!(obj instanceof Funcionalidade)) {
			return false;
		}
		Funcionalidade other = (Funcionalidade) obj;
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
		return String.format("Funcionalidade{id:%s}", id);
	}
	
	
}

package digytal.springdicas.domain.acesso;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tab_acs_perfil")
public class Perfil {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_perfil",length=9)
	private Integer id;
	
	@Column(name = "nome_perfil",length=50)
	private String nome;

	private boolean externo;

	@ManyToMany
	@JoinTable(name = "tab_acs_perfil_permissao", joinColumns = {
			@JoinColumn(name = "cd_perfil") }, inverseJoinColumns = { @JoinColumn(name = "cd_permissao") })
	private List<Permissao> permissoes = new ArrayList<Permissao>();

	private boolean excluido;

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	public boolean isExcluido() {
		return excluido;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean getExterno() {
		return externo;
	}

	public void setExterno(boolean externo) {
		this.externo = externo;
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
		if (!(obj instanceof Perfil)) {
			return false;
		}
		Perfil other = (Perfil) obj;
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
		return String.format("Perfil{id:%s, nome:%s}", id, nome);
	}

}

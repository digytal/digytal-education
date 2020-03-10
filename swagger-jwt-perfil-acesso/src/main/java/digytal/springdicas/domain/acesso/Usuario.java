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
@Table(name = "tab_acs_usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_usuario", length=9)
	private Integer id;
	
	@Column(name="nome_usuario",length = 50)
	private String nome;
	
	@Column(updatable = false)
	private String login;

	@Column(length = 100)
	private String senha;
	@Column(length = 70)
	private String email;

	@ManyToMany
	@JoinTable(name = "tab_acs_usuario_perfil", joinColumns = {
			@JoinColumn(name = "cd_usuario") }, inverseJoinColumns = { @JoinColumn(name = "cd_perfil") })
	private List<Perfil> perfis = new ArrayList<Perfil>();

	private boolean excluido;

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	public boolean isExcluido() {
		return excluido;
	}

	public Integer getId() {
		return id;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}

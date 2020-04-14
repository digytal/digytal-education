package digytal.springdicas.api.model.cadastro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import digytal.springdicas.api.model.Entidade;

@Entity
@Table(name = "tab_cad_empresa")
public class Empresa extends Entidade {
	//@ApiModelProperty(value = "Codigo da empresa", example = "1", readOnly = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	//@ApiModelProperty(value = "Nome da empresa", example = "PEREIRA E GARCIA TEC LTDA")
	@Column(name = "nome_empresa", length = 70, nullable = false)
	private String nome;

	@Column(name = "nm_inscricao", length = 11, nullable = false)
	//@ApiModelProperty(value = "Inscricao da empresa, 8 digitos do CNPJ ou 11 digitos do CPF", example = "12345678")
	private String inscricao;

	public Integer getId() {
		return id;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}

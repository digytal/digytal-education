package digytal.springdicas.api.model.cadastro;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import digytal.springdicas.api.model.Entidade;


@Entity
@Table(name = "tab_cad_cadastro")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ct")
public abstract class Cadastro extends Entidade {
	//@ApiModelProperty(value = "Código do cadastro", readOnly=true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	//@ApiModelProperty(value = "Código da empresa", example = "1")
	@Column(name = "cd_empresa")
	private Integer codigoEmpresa;

	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="cd_endereco", nullable=false)
	private CadastroEndereco endereco = new CadastroEndereco();
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER) // os dados da pessoa não deveriam mudar, logo CascadeType = PERSIST
	@JoinColumn(name="cd_pessoa", nullable=false)
	private Pessoa pessoa = new Pessoa();
	
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public void setCodigoEmpresa(Integer codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	public Integer getCodigoEmpresa() {
		return codigoEmpresa;
	}

	public Integer getId() {
		return id;
	}

	public CadastroEndereco getEndereco() {
		return endereco;
	}

	public void setEndereco(CadastroEndereco endereco) {
		this.endereco = endereco;
	}
	

}

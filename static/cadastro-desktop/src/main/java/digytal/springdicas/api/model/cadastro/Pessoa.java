package digytal.springdicas.api.model.cadastro;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import digytal.springdicas.api.enums.TipoPessoa;
import digytal.springdicas.api.model.Entidade;

@Entity
@Table(name = "tab_cad_pessoa")
public class Pessoa extends Entidade {
	//@ApiModelProperty( value= "Código da pessoa", example="1", readOnly=true)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	
	//@ApiModelProperty( value= "Nome completo \\ Razão Social", example="JOAO PEREIRA ARAUJO LTDA", required=true)
	@Column(name = "nome_razao", length = 70,nullable=false)
	private String nomeRazao;
	
	//@ApiModelProperty( value= "Nome \\ Nome Fantasia", example="ARAUJO")
	@Column(name = "nome_popular", length = 70)
	private String nomeFantasia;
	
	//@ApiModelProperty( value= "Nome \\ Nome Fantasia", example="ARAUJO")
	@Column(name = "rg_im", length = 30)
	private String rgIm;
		
	@Column(name = "cnh_ie", length = 30)
	private String cnhIe;
	
	//@ApiModelProperty( value= "E-mail", example="email@dominio.com")
	@Column(name = "email", length = 100)
	private String email;
	
	//@ApiModelProperty( value= "Numero do Celular", example="11987654321")
	@Column(name = "celular", length = 12)
	private String celular;
	
	//@ApiModelProperty( value= "Numero do Celular 2", example="11987654321")
	@Column(name = "celular2", length = 12)
	private String celular2;
	
	//@ApiModelProperty( value= "CPF \\ CNPJ", example="00645899625", required=true)
	@Column(name = "cpf_cnpj", length = 20,nullable=false)
	private String cpfCnpj;
	
	//@ApiModelProperty( value= "Data de aniversário", example="1990-01-13", required=true)
	@Column(name = "dt_aniversario")
	private Date aniversario;

	//@ApiModelProperty( value= "Tipo pessoa", example="FISICA \\ JURIDICA", readOnly=true)
	@Enumerated(EnumType.STRING)
	@Column(name = "en_tipo", length = 15)
	private TipoPessoa tipo=TipoPessoa.FISICA;
	
	public Integer getId() {
		return id;
	}
	
	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		if (cpfCnpj != null) {
			this.cpfCnpj = cpfCnpj.replaceAll("\\D", "");
			this.tipo = this.cpfCnpj.length() == 14 ? TipoPessoa.JURIDICA : TipoPessoa.FISICA;
		}
	}

	public TipoPessoa getTipo() {
		return tipo;
	}

	public Date getAniversario() {
		return aniversario;
	}

	public void setAniversario(Date aniversario) {
		this.aniversario = aniversario;
	}

	public String getNomeRazao() {
		return nomeRazao;
	}

	public void setNomeRazao(String nomeRazao) {
		this.nomeRazao = nomeRazao;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCelular2() {
		return celular2;
	}

	public void setCelular2(String celular2) {
		this.celular2 = celular2;
	}

	public String getRgIm() {
		return rgIm;
	}
	public void setRgIm(String rgIm) {
		this.rgIm = rgIm;
	}
	
	public String getCnhIe() {
		return cnhIe;
	}

	public void setCnhIe(String cnhIe) {
		this.cnhIe = cnhIe;
	}


	

}

package digytal.springdicas.api.model.cadastro;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import digytal.springdicas.api.enums.LocalEndereco;
import digytal.springdicas.api.enums.municipio.Pais;

@Embeddable
public class Endereco {
	//@ApiModelProperty( value= "CEP - Código postal", example="01001000")
	@Column(length = 8, nullable = false)
	private String cep;
	
	//@ApiModelProperty( value= "Logradouro", example="PRACA DA SE")
	@Column(length = 70, nullable = false)
	private String logradouro;
	
	//@ApiModelProperty( value= "Complemento", example="AP 123")
	@Column(length = 60)
	private String complemento;
	
	//@ApiModelProperty( value= "Bairro", example="LADO IMPAR")
	@Column(length = 70, nullable = false)
	private String bairro;

	//@ApiModelProperty( value= "Localidade (Cidade)", example="SAO PAULO")
	@Column(length = 80, nullable = false)
	private String localidade;

	//@ApiModelProperty( value= "Uf", example="SP")
	@Column(length = 3, nullable = false)
	private String uf;
	
	//@ApiModelProperty( value= "IBGE", example="3550308")
	@Column(length = 7, nullable = false)
	private Integer ibge;

	//@ApiModelProperty( value= "IBGE do estado", example="35", readOnly=true)
	@Column(name = "ibge_uf", length = 2, nullable = false)
	private Integer ibgeUf;

	//@ApiModelProperty( value= "Numero", example="123")
	@Column(length = 6, nullable = false)
	private String numero;

	//@ApiModelProperty( value= "Telefone", example="11957946512")
	@Column(length = 15)
	private String telefone;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 10, name="ct_local")
	private LocalEndereco local = LocalEndereco.CASA;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 10, name="ct_pais")
	private Pais pais = Pais.BRASIL;
	
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	public LocalEndereco getLocal() {
		return local;
	}
	public void setLocal(LocalEndereco local) {
		this.local = local;
	}

	public Integer getIbge() {
		return ibge;
	}

	public void setIbge(Integer ibge) {
		this.ibge = ibge;
		if(ibge!=null) {
			this.ibgeUf = Integer.valueOf(ibge.toString().substring(0,2));
		}
	}
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Integer getIbgeUf() {
		return ibgeUf;
	}
}

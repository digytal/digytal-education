package digytal.springdicas.api.model.cadastro;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import digytal.springdicas.api.model.Entidade;

@Entity
@Table(name = "tab_cad_endereco")
public class CadastroEndereco extends Entidade {
	//@ApiModelProperty(value = "Código do endereco", example = "1", readOnly = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_endereco")
	private Integer id;
	
	@Embedded
	private Endereco contato = new Endereco();
	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "cep", column = @Column(name = "cob_cep")),
			@AttributeOverride(name = "logradouro", column = @Column(name = "cob_logradouro")),
			@AttributeOverride(name = "complemento", column = @Column(name = "cob_complemento")),
			@AttributeOverride(name = "bairro", column = @Column(name = "cob_bairro")),
			@AttributeOverride(name = "localidade", column = @Column(name = "cob_localidade")),
			@AttributeOverride(name = "uf", column = @Column(name = "cob_uf")),
			@AttributeOverride(name = "ibge", column = @Column(name = "cob_ibge")),
			@AttributeOverride(name = "ibgeUf", column = @Column(name = "cob_ibge_uf")),
			@AttributeOverride(name = "numero", column = @Column(name = "cob_numero")),
			@AttributeOverride(name = "telefone", column = @Column(name = "cob_telefone")),
			@AttributeOverride(name = "local", column = @Column(name = "ct_cob_local")),
			@AttributeOverride(name = "pais", column = @Column(name = "ct_cob_pais"))
	})
	
	private Endereco cobranca = new Endereco();
	
	public Integer getId() {
		return id;
	}

	public Endereco getContato() {
		return contato;
	}

	public void setContato(Endereco contato) {
		this.contato = contato;
	}

	public Endereco getCobranca() {
		return cobranca;
	}

	public void setCobranca(Endereco cobranca) {
		this.cobranca = cobranca;
	}
	
	
	public void setId(Integer id) {
		this.id = id;
	}
	
}

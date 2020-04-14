package digytal.springdicas.api.model.cadastro;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "tab_cad_cliente")
@DiscriminatorValue(CadastroPapel.CLIENTE)
public class Cliente extends Cadastro {
	//@ApiModelProperty(value = "Determina se o cliente está bloqueado", example = "false")
	@Column(name = "cli_fl_bloqueado", length=1, nullable=false)
	private boolean bloqueado;
	
	//@ApiModelProperty(value = "Valor de limite de credito do cliente", example = "6123.45")
	@Column(name = "cli_limite_credito", precision=9, scale=2, nullable=false)
	private Double limiteCredito=0.0d;
	
	public boolean isBloqueado() {
		return bloqueado;
	}
	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}
	public Double getLimiteCredito() {
		return limiteCredito;
	}
	public void setLimiteCredito(Double limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

}

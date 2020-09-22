package digytal.springdicas.api.model;

import java.io.Serializable;

import javax.persistence.Column;

public abstract class Entidade implements Serializable {
	//@ApiModelProperty(value = "Identifica se o registro esta excluido", example = "false", readOnly = true)
	@Column(name = "fl_excluido", length = 1, nullable = false)
	private boolean excluido;

	public abstract Object getId();

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	public boolean isExcluido() {
		return excluido;
	}
}

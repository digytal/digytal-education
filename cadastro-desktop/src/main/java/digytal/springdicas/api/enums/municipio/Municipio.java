package digytal.springdicas.api.enums.municipio;

import digytal.springdicas.api.enums.Enumeracao;

public interface Municipio extends Enumeracao {
	String getEstado();
	String getSigla();
	Integer getUf();
	Integer getIbge();
}

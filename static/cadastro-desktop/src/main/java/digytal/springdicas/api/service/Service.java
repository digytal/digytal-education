package digytal.springdicas.api.service;

import java.util.List;

import digytal.springdicas.api.enums.TipoOperacao;
import digytal.springdicas.utils.jpa.SearchParams;

public interface Service<E,K> {
	void salvar(TipoOperacao tipoOperacao, E e);
	void incluir(E entidade);
	
	void alterar(E entidade);
	
	void excluir(E entidade);
	
	E buscar(Object id);
	
	List<E> listar(SearchParams params);
	
	List<E> listar(String campo, Object valor);
	
	List<E> listar(String campo, Object operador, Object valor);
	
}

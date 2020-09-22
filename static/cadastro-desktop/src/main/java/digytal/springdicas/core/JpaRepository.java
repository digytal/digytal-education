package digytal.springdicas.core;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import digytal.springdicas.api.enums.TipoOperacao;
import digytal.springdicas.api.model.Entidade;
import digytal.springdicas.api.service.Service;
import digytal.springdicas.utils.jpa.QueryBuilder;
import digytal.springdicas.utils.jpa.SearchParams;

public class JpaRepository <E,K> implements Service<E, K> {
	
	@PersistenceContext
	protected EntityManager em;
	
	static final int MAX_SIZE = 200;
	protected Class<E> entityClass;
	
	public JpaRepository() {
		this.entityClass = (Class<E>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	protected QueryBuilder<E> createQueryBuilder() {
		return QueryBuilder.of(entityClass).withEntityManager(em);
	}
	
	@Override
	@Transactional
	public void incluir(E entidade) {
		em.persist(entidade);
		em.flush();
		Object id = ((Entidade)entidade).getId();
		//return (K) id;
	}
	
	@Override
	@Transactional
	public void alterar(E entidade) {
		//return (E) em.merge(entidade);
		em.merge(entidade);
	}

	
	@Override
	public void excluir(E entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public E buscar(Object id) {
		return em.find(entityClass, id);
	}

	@Override
	public List<E> listar(SearchParams params) {	
		int limit =200;// searchReq.getLimit() == null ? MAX_SIZE : searchReq.getLimit();
		QueryBuilder<E> queryBuilder = createQueryBuilder()
			.select()
			//.sort(searchReq.getSort())
			//.offset(searchReq.getOffset())
			.limit(limit)
		;
		
		listar(queryBuilder,params);
		
		List<E> results = queryBuilder.build().getResultList();
		return results;
	}

	@Override
	public List<E> listar(String campo, Object valor) {
		//implementar o método corretamente
		Query q = em.createQuery("SELECT e FROM " + entityClass.getName() + " e ");
		return q.getResultList();
	}

	@Override
	public List<E> listar(String campo, Object operador, Object valor) {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected void listar(QueryBuilder<E> queryBuilder,SearchParams params) {
		
	}

	@Override
	@Transactional
	public void salvar(TipoOperacao tipoOperacao, E entidade) {
		if(TipoOperacao.INCLUSAO == tipoOperacao)
			incluir(entidade);
		else if(TipoOperacao.ALTERACAO == tipoOperacao)
			alterar(entidade);
		else
			throw new RuntimeException("Tipo Operação Inválida");
	}
}

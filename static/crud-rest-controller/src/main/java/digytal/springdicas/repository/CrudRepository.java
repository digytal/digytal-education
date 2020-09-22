package digytal.springdicas.repository;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import digytal.springdicas.domain.EntityId;

public class CrudRepository <E extends EntityId, K> {
	@PersistenceContext
	private EntityManager em;
	
	protected Class<E> entityClass;
	
	public CrudRepository() {
		this.entityClass = (Class<E>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public List select() {
		String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e ";
		List<E> results = em.createQuery(jpql, entityClass).getResultList();
		return results;
	}
	@Transactional
	public <K> K  insert(E entidade) {
		em.persist(entidade);
		em.flush();
		Object id = ((EntityId)entidade).getId();
		return (K) id;
	}
	@Transactional
	public <E> E  update(E entidade) {
		return (E) em.merge(entidade);
	}
	
	@Transactional
	public void delete(K id) {
		 Object entity = em.find(entityClass, 1);
		 //em.getTransaction().begin();
		 em.remove(entity);
		 //em.getTransaction().commit();
	}
	
}

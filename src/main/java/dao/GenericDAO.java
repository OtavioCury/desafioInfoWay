package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class GenericDAO<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "BancoInfoWay")
	private EntityManager em;

	private Class<T> entityClass;

	public GenericDAO(Class<T> entityClass) {
		this.entityClass = entityClass;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BancoInfoWay");
		em = entityManagerFactory.createEntityManager();
	}
	public EntityManager getEm() {
		return em;
	}

	public T update(T entity) {
		return em.merge(entity);
	}

	public T find(long entityID) {
		return em.find(entityClass, entityID);
	}

	public List<T> findList(List<Long> ids){
		List<T> list = new ArrayList<T>();
		for (Long id : ids) {
			list.add(find(id));
		}
		return list;
	}

	protected void delete(Object id, Class<T> classe) {
		T entityToBeRemoved = em.find(classe, id);
		em.remove(entityToBeRemoved);
	}

	public void save(T entity) {
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
	}    

	public List<T> findAll() {
		CriteriaBuilder cb = getEm().getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(entityClass);
		@SuppressWarnings("unused")
		Root<T> rootU = cq.from(entityClass);
		TypedQuery<T> query = getEm().createQuery(cq);

		return query.getResultList();
	}    	

}

package dao;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import modelo.entidades.Agencia;

@Repository
public class AgenciaDAO extends GenericDAO<Agencia>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AgenciaDAO() {
		super(Agencia.class);
	}

	/**
	 * Busca agência por número
	 * @param numero
	 * @return
	 */
	public Agencia agenciaNumero(String numero) {
		CriteriaBuilder cb = getEm().getCriteriaBuilder();
		CriteriaQuery<Agencia> cq = cb.createQuery(Agencia.class);
		Root<Agencia> root = cq.from(Agencia.class);

		CriteriaQuery<Agencia> query = cq.select(root);

		Predicate predicado = cb.equal(root.get("numero"), numero);

		query.where(predicado);

		TypedQuery<Agencia> tq = getEm().createQuery(query);

		try {
			return tq.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}

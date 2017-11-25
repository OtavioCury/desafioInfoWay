package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import modelo.entidades.Cliente;
import modelo.entidades.Conta;

@Repository
public class ClienteDAO extends GenericDAO<Cliente>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClienteDAO() {
		super(Cliente.class);
	}

	/**
	 * Busca todas as contas de um cliente
	 * @param id
	 * @return
	 */
	public List<Conta> todasContas(Long id){
		TypedQuery<Conta> query = getEm().createQuery("SELECT c FROM conta c WHERE c.cliente_id = :id", Conta.class);
		query.setParameter("id", id);
		List<Conta> contas = null;
		contas = query.getResultList();
		return contas;
	}

	/**
	 * Busca um banco pelo nome
	 * @param nome
	 * @return
	 */
	public Cliente buscarNome(String nome) {
		CriteriaBuilder cb = getEm().getCriteriaBuilder();
		CriteriaQuery<Cliente> cq = cb.createQuery(Cliente.class);
		Root<Cliente> root = cq.from(Cliente.class);

		CriteriaQuery<Cliente> query = cq.select(root);

		List<Predicate> predicados = new ArrayList<Predicate>();		

		predicados.add(cb.equal(root.get("nome"), nome));

		query.where(predicados.toArray(new Predicate[]{}));

		TypedQuery<Cliente> tq = getEm().createQuery(query);

		try {
			return tq.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}

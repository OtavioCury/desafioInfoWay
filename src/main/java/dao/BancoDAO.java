package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import modelo.entidades.Agencia;
import modelo.entidades.Banco;

@Repository
public class BancoDAO extends GenericDAO<Banco>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BancoDAO() {
		super(Banco.class);
	}

	/**
	 * Retorna todos os bancos cadastrados
	 * @return
	 */
	public List<Banco> listaTodos(){
		return super.findAll();
	}

	/**
	 * Busca um banco pelo nome
	 * @param nome
	 * @return
	 */
	public Banco buscarNome(String nome) {
		Query query = getEm().createQuery("SELECT b FROM Banco b WHERE b.nome = :nome");
		query.setParameter("nome", nome);
		Banco banco = null;
		try {
			banco = (Banco) query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
		return banco;
	}

	/**
	 * Busca todas as agências de um banco
	 * @param id
	 * @return
	 */
	public List<Agencia> todasAgencias(Long id){
		CriteriaBuilder cb = getEm().getCriteriaBuilder();
		CriteriaQuery<Agencia> cq = cb.createQuery(Agencia.class);
		Root<Agencia> root = cq.from(Agencia.class);

		CriteriaQuery<Agencia> query = cq.select(root);

		List<Predicate> predicados = new ArrayList<Predicate>();		

		predicados.add(cb.equal(root.get("banco"), id));

		query.where(predicados.toArray(new Predicate[]{}));

		TypedQuery<Agencia> tq = getEm().createQuery(query);

		return tq.getResultList();
	}
}

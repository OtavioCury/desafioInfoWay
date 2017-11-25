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

import modelo.entidades.Conta;

@Repository
public class ContaDAO extends GenericDAO<Conta>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ContaDAO() {
		super(Conta.class);
	}

	public void deletarConta(Long id){
		super.delete(id, Conta.class);
	}

	public void adicionaConta(Conta conta){
		super.save(conta);
	}

	/**
	 * Relaiza o login em uma conta
	 * @param numero
	 * @param senha
	 * @return
	 */
	public Conta login(String numero, String senha) {
		CriteriaBuilder cb = getEm().getCriteriaBuilder();
		CriteriaQuery<Conta> cq = cb.createQuery(Conta.class);
		Root<Conta> root = cq.from(Conta.class);

		CriteriaQuery<Conta> query = cq.select(root);

		Predicate predicado = cb.and(cb.equal(root.get("numero"), numero), cb.equal(root.get("senha"), senha));

		query.where(predicado);

		TypedQuery<Conta> tq = getEm().createQuery(query);

		try {
			return tq.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	/**
	 * Busca todas as contas de uma agência
	 * @param id
	 * @return
	 */
	public List<Conta> contasPorAgencia(Long id){
		CriteriaBuilder cb = getEm().getCriteriaBuilder();
		CriteriaQuery<Conta> cq = cb.createQuery(Conta.class);
		Root<Conta> root = cq.from(Conta.class);

		CriteriaQuery<Conta> query = cq.select(root);

		List<Predicate> predicados = new ArrayList<Predicate>();		

		predicados.add(cb.equal(root.get("agencia"), id));

		query.where(predicados.toArray(new Predicate[]{}));

		TypedQuery<Conta> tq = getEm().createQuery(query);

		return tq.getResultList();
	}
}

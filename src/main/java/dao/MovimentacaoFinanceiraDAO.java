package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import modelo.entidades.MovimentacaoFinanceira;

@Repository
public class MovimentacaoFinanceiraDAO extends GenericDAO<MovimentacaoFinanceira>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MovimentacaoFinanceiraDAO() {
		super(MovimentacaoFinanceira.class);
	}

	/**
	 * Busca todas as movimentações financeiras de uma conta
	 * @param id
	 * @return
	 */
	public List<MovimentacaoFinanceira> movimentacoesPorConta(Long id){
		CriteriaBuilder cb = getEm().getCriteriaBuilder();
		CriteriaQuery<MovimentacaoFinanceira> cq = cb.createQuery(MovimentacaoFinanceira.class);
		Root<MovimentacaoFinanceira> root = cq.from(MovimentacaoFinanceira.class);

		CriteriaQuery<MovimentacaoFinanceira> query = cq.select(root);

		List<Predicate> predicados = new ArrayList<Predicate>();		

		predicados.add(cb.equal(root.get("conta"), id));

		query.where(predicados.toArray(new Predicate[]{}));

		TypedQuery<MovimentacaoFinanceira> tq = getEm().createQuery(query);

		return tq.getResultList();
	}
}
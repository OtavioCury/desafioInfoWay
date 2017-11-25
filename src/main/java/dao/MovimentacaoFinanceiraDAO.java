package dao;

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

	public void deletarMovimentacaoFinanceira(Long id){
		super.delete(id, MovimentacaoFinanceira.class);
	}

	public void adicionaMovimentacaoFinanceira(MovimentacaoFinanceira movimentacaoFinanceira){
		super.save(movimentacaoFinanceira);
	}
}
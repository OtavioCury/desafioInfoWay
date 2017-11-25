package dao;

import modelo.entidades.MovimentacaoFinanceira;

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
package controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.MovimentacaoFinanceiraDAO;
import modelo.entidades.MovimentacaoFinanceira;

@Controller
@RequestMapping("/movimentacaoFinanceira")
public class MovimentacaoFinanceiraController {

	@Autowired
	private MovimentacaoFinanceiraDAO movimentacaoFinanceiraDAO;

	/**
	 * Busca todas as movimentações financeiras de uma conta
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/movimentacoes-conta/{id}", method = RequestMethod.GET)
	public @ResponseBody List<MovimentacaoFinanceira> movimentacoesPorConta(@PathVariable String id){
		List<MovimentacaoFinanceira> movimentacoes = movimentacaoFinanceiraDAO.movimentacoesPorConta(Long.parseLong(id));
		for (MovimentacaoFinanceira movimentacaoFinanceira : movimentacoes) {
			movimentacaoFinanceira.setDescricao(movimentacaoFinanceira.toString());
		}
		return movimentacoes;
	}

	/**
	 * Método que adiciona uma movimentação financeira
	 * @param banco
	 * @return
	 */
	@RequestMapping(value = "/adiciona-movimentacao", method = RequestMethod.POST)
	public ResponseEntity<Void> adicionaMovimentacao(@RequestBody MovimentacaoFinanceira movimentacaoFinanceira){
		movimentacaoFinanceira.setData(new Date());
		movimentacaoFinanceiraDAO.save(movimentacaoFinanceira);
		return new ResponseEntity<Void>(HttpStatus.CREATED);

	}
}

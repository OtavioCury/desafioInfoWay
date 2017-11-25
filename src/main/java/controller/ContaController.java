package controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.ContaDAO;
import modelo.entidades.Conta;

@Controller
@RequestMapping("/conta")
public class ContaController {

	private ContaDAO contaDAO;

	@PostConstruct
	private void init() {
		// TODO Auto-generated method stub
		contaDAO = new ContaDAO();
	}

	@RequestMapping(value = "/adicionaConta", method = RequestMethod.POST)
	public ResponseEntity<Void> adicionaConta(@RequestBody Conta conta){
		List<Conta> contas = new ArrayList<Conta>();
		contas = contaDAO.contasPorAgencia(conta.getAgencia().getId());
		for (Conta contaAux : contas) {
			if (contaAux.getNumero() == conta.getNumero()) {
				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			}
		}
		contaDAO.adicionaConta(conta);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/atualizaConta", method = RequestMethod.POST)
	public ResponseEntity<Void> atualizaConta(@RequestBody Conta conta){
		contaDAO.update(conta);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}

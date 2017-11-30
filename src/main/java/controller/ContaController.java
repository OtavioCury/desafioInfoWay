package controller;

import java.util.ArrayList;
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

import dao.AgenciaDAO;
import dao.ClienteDAO;
import dao.ContaDAO;
import modelo.entidades.Agencia;
import modelo.entidades.Cliente;
import modelo.entidades.Conta;

@Controller
@RequestMapping("/conta")
public class ContaController {

	@Autowired
	private ContaDAO contaDAO;
	@Autowired
	private ClienteDAO clienteDAO;
	@Autowired
	private AgenciaDAO agenciaDAO;

	/**
	 * Adiciona uma conta
	 * @param conta
	 * @return
	 */
	@RequestMapping(value = "/adiciona-conta", method = RequestMethod.POST)
	public ResponseEntity<Void> adicionaConta(@RequestBody Conta conta){
		List<Conta> contas = new ArrayList<Conta>();
		contas = contaDAO.contasPorAgencia(conta.getAgencia().getId());
		for (Conta contaAux : contas) {
			if (contaAux.getNumero().equals(conta.getNumero())) {
				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			}
		}
		Cliente cliente = clienteDAO.buscarNome(conta.getCliente().getNome());
		if (cliente == null) {
			clienteDAO.save(conta.getCliente());
		}else{
			conta.setCliente(cliente);
		}
		conta.setSaldo(0);
		contaDAO.adicionaConta(conta);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	/**
	 * Realiza o Login na conta
	 * @return
	 */
	@RequestMapping(value = "/login/{numero}/{senha}", method = RequestMethod.GET)
	public @ResponseBody Conta login(@PathVariable String numero, @PathVariable String senha){
		return contaDAO.login(numero, senha);
	}

	/**
	 * Busca conta por número
	 * @param numero
	 * @return
	 */
	@RequestMapping(value = "/conta/{numero}/{agencia}", method = RequestMethod.GET)
	public @ResponseBody Conta contaNumero(@PathVariable String numero, @PathVariable String agencia){
		Agencia agenciaAux = agenciaDAO.agenciaNumero(agencia);
		if (agenciaAux != null) {
			List<Conta> contas = new ArrayList<Conta>();
			contas = contaDAO.contasPorAgencia(agenciaAux.getId());
			for (Conta conta : contas) {
				if (conta.getNumero().equals(numero)) {
					return conta;
				}
			}
		}
		return null;
	}

	/**
	 * Atualiza uma conta
	 * @param conta
	 * @return
	 */
	@RequestMapping(value = "/atualiza-conta", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Void> atualizaConta(@RequestBody Conta conta){
		contaDAO.update(conta);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}

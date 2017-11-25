package controller;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.ClienteDAO;
import modelo.entidades.Cliente;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	private ClienteDAO clienteDAO;

	@PostConstruct
	private void init() {
		// TODO Auto-generated method stub
		clienteDAO = new ClienteDAO();
	}

	@RequestMapping(value = "/adicionaCliente", method = RequestMethod.POST)
	public ResponseEntity<Void> adicionaCliente(@RequestBody Cliente cliente){
		if (clienteDAO.buscarNome(cliente.getNome()) == null) {
			clienteDAO.adicionaCliente(cliente);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}else{
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}

}

package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	//	@Autowired
	//	private ClienteDAO clienteDAO;
	//
	//	/**
	//	 * Adiciona um cliente
	//	 * @param cliente
	//	 * @return
	//	 */
	//	@RequestMapping(value = "/adiciona-cliente", method = RequestMethod.POST)
	//	public ResponseEntity<Void> adicionaCliente(@RequestBody Cliente cliente){
	//		if (clienteDAO.buscarNome(cliente.getNome()) == null) {
	//			clienteDAO.save(cliente);
	//			return new ResponseEntity<Void>(HttpStatus.CREATED);
	//		}else{
	//			clienteDAO.update(cliente);
	//			return new ResponseEntity<Void>(HttpStatus.OK);
	//		}
	//	}

}

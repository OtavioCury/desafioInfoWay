package controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.BancoDAO;
import modelo.entidades.Banco;

@Controller
@RequestMapping("/banco")
public class BancoController {

	private BancoDAO bancoDAO;

	@PostConstruct
	private void init() {
		// TODO Auto-generated method stub
		bancoDAO = new BancoDAO();
	}

	@RequestMapping(value = "/bancos", method = RequestMethod.GET, produces = "{application/json}")
	public ResponseEntity<List<Banco>> bancos(){
		return new ResponseEntity<List<Banco>>(bancoDAO.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/adicionaBanco", method = RequestMethod.POST, consumes = "{application/json}")
	public ResponseEntity<Void> adicionaBanco(@RequestBody Banco banco){
		if (bancoDAO.buscarNome(banco.getNome()) == null) {
			bancoDAO.adicionaBanco(banco);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}else{
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}
}

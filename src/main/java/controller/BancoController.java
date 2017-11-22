package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.BancoDAO;
import modelo.entidades.Banco;

@Controller
@RequestMapping("/banco")
public class BancoController {

	private BancoDAO bancoDAO;

	private void init() {
		// TODO Auto-generated method stub
		bancoDAO = new BancoDAO();
	}

	@RequestMapping(value = "/retornaBanco/{id}", method = RequestMethod.GET)
	public ResponseEntity<Banco> retornaBanco(@PathVariable Long id){
		init();
		Banco banco = bancoDAO.find(id);
		if (banco == null) {
			return new ResponseEntity<Banco>(HttpStatus.NOT_FOUND);
		}else{
			return new ResponseEntity<Banco>(banco, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/adicionaBanco", method = RequestMethod.POST)
	public ResponseEntity<Void> adicionaBanco(@RequestBody Banco banco){
		init();
		if (bancoDAO.buscarNome(banco.getNome()) == null) {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}else{
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "/teste", method = RequestMethod.GET)
	public void retornaBanco(){
		System.out.println("teste");
	}
}

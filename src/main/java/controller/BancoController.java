package controller;

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

import dao.BancoDAO;
import modelo.entidades.Agencia;
import modelo.entidades.Banco;

@Controller
@RequestMapping("/banco")
public class BancoController {

	@Autowired
	private BancoDAO bancoDAO;

	/**
	 * Retorna todos os bancos cadastrados
	 * @return
	 */
	@RequestMapping(value = "/bancos", method = RequestMethod.GET)
	public @ResponseBody List<Banco> bancos(){
		System.out.println("Teste");
		return bancoDAO.findAll();
	}

	/**
	 * Método que adiciona um banco 
	 * @param banco
	 * @return
	 */
	@RequestMapping(value = "/adiciona-banco", method = RequestMethod.POST)
	public ResponseEntity<Void> adicionaBanco(@RequestBody Banco banco){
		if (bancoDAO.buscarNome(banco.getNome()) == null) {
			bancoDAO.save(banco);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}else{
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}

	/**
	 * Busca todas as agências de um determinado banco
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/todas-agencias/{id}", method = RequestMethod.GET)
	public @ResponseBody List<Agencia> todasAgencias(@PathVariable String id){
		return bancoDAO.todasAgencias(Long.parseLong(id));
	}
}

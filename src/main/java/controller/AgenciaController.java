package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.AgenciaDAO;
import dao.BancoDAO;
import modelo.entidades.Agencia;

@Controller
@RequestMapping("/agencia")
public class AgenciaController {

	@Autowired
	private AgenciaDAO agenciaDAO;
	@Autowired
	private BancoDAO bancoDAO;

	/**
	 * Lista todas as agências
	 * @return
	 */
	@RequestMapping(value = "/agencias", method = RequestMethod.GET)
	public @ResponseBody List<Agencia> agencias(){
		return agenciaDAO.findAll();
	}

	/**
	 * Adiciona uma agencia
	 * @param agencia
	 * @return
	 */
	@RequestMapping(value = "/adiciona-agencia", method = RequestMethod.POST)
	public ResponseEntity<Void> adicionaAgencia(@RequestBody Agencia agencia){
		List<Agencia> agencias = new ArrayList<Agencia>();
		agencias = bancoDAO.todasAgencias(agencia.getBanco().getId());
		for (Agencia agenciaAux : agencias) {
			if (agenciaAux.getNumero().equals(agencia.getNumero())) {
				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			}
		}
		agenciaDAO.save(agencia);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
}

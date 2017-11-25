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

import dao.AgenciaDAO;
import modelo.entidades.Agencia;

@Controller
@RequestMapping("/agencia")
public class AgenciaController {

	private AgenciaDAO agenciaDAO;

	@PostConstruct
	private void init() {
		// TODO Auto-generated method stub
		agenciaDAO = new AgenciaDAO();
	}

	@RequestMapping(value = "/agencias", method = RequestMethod.GET)
	public ResponseEntity<List<Agencia>> agencias(){
		return new ResponseEntity<List<Agencia>>(agenciaDAO.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/adicionaAgencia", method = RequestMethod.POST)
	public ResponseEntity<Void> adicionaAgencia(@RequestBody Agencia agencia){
		List<Agencia> agencias = new ArrayList<Agencia>();
		agencias = agenciaDAO.agenciasPorBanco(agencia.getBanco().getId());
		for (Agencia agenciaAux : agencias) {
			if (agenciaAux.getNumero() == agencia.getNumero()) {
				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			}
		}
		agenciaDAO.adicionaAgencia(agencia);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
}

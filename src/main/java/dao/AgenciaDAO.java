package dao;

import org.springframework.stereotype.Repository;

import modelo.entidades.Agencia;

@Repository
public class AgenciaDAO extends GenericDAO<Agencia>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AgenciaDAO() {
		super(Agencia.class);
	}

	public void deletarAgencia(Long id){
		super.delete(id, Agencia.class);
	}

	public void adicionaAgencia(Agencia agencia){
		super.save(agencia);
	}
}

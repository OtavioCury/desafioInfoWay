package dao;

import java.util.List;

import javax.persistence.TypedQuery;

import modelo.entidades.Agencia;

public class AgenciaDAO extends GenericDAO<Agencia, Agencia>{
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

	/**
	 * Busca todas as agências de um banco
	 * @param id
	 * @return
	 */
	public List<Agencia> agenciasPorBanco(Long id){
		TypedQuery<Agencia> query = getEm().createQuery("SELECT a FROM agencia a WHERE a.banco_id = :id", Agencia.class);
		query.setParameter("id", id);
		List<Agencia> agencias = null;
		agencias = query.getResultList();
		return agencias;
	}
}

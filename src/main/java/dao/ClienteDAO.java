package dao;

import java.util.List;

import javax.persistence.TypedQuery;

import modelo.entidades.Cliente;
import modelo.entidades.Conta;

public class ClienteDAO extends GenericDAO<Cliente, Cliente>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClienteDAO() {
		super(Cliente.class);
	}

	public void deletarCliente(Long id){
		super.delete(id, Cliente.class);
	}

	public void adicionaCliente(Cliente cliente){
		super.save(cliente);
	}

	/**
	 * Busca todas as contas de um cliente
	 * @param id
	 * @return
	 */
	public List<Conta> todasContas(Long id){
		TypedQuery<Conta> query = getEm().createQuery("SELECT c FROM conta c WHERE c.cliente_id = :id", Conta.class);
		query.setParameter("id", id);
		List<Conta> contas = null;
		contas = query.getResultList();
		return contas;
	}

}
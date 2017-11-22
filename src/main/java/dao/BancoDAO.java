package dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import modelo.entidades.Agencia;
import modelo.entidades.Banco;

public class BancoDAO extends GenericDAO<Banco, Banco>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BancoDAO() {
		super(Banco.class);
	}

	public void deletarBanco(Long id){
		super.delete(id, Banco.class);
	}

	public void adicionaBanco(Banco banco){
		super.save(banco);
	}

	/**
	 * Retorna todos os bancos cadastrados
	 * @return
	 */
	public List<Banco> listaTodos(){
		return super.findAll();
	}

	/**
	 * Busca um banco pelo nome
	 * @param nome
	 * @return
	 */
	public Banco buscarNome(String nome) {
		Query query = getEm().createQuery("SELECT b FROM banco b WHERE b.nome = :nome");
		query.setParameter("nome", nome);
		Banco banco = null;
		try {
			banco = (Banco) query.getSingleResult();
		} catch (NoResultException ex) {
			ex.printStackTrace();
		}
		return banco;
	}

	/**
	 * Busca todas as agências de um banco
	 * @param id
	 * @return
	 */
	public List<Agencia> todasAgencias(Long id){
		TypedQuery<Agencia> query = getEm().createQuery("SELECT a FROM agencia a WHERE a.banco_id = :id", Agencia.class);
		query.setParameter("id", id);
		List<Agencia> agencias = null;
		agencias = query.getResultList();
		return agencias;
	}
}

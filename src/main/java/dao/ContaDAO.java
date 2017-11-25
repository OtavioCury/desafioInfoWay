package dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import modelo.entidades.Conta;

public class ContaDAO extends GenericDAO<Conta>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ContaDAO() {
		super(Conta.class);
	}

	public void deletarConta(Long id){
		super.delete(id, Conta.class);
	}

	public void adicionaConta(Conta conta){
		super.save(conta);
	}

	/**
	 * Relaiza o login em uma conta
	 * @param numero
	 * @param senha
	 * @return
	 */
	public Conta login(String numero, String senha) {
		Query query = getEm().createQuery("SELECT c FROM conta c WHERE c.numero = :numero and c.senha = :senha");
		query.setParameter("numero", numero);
		query.setParameter("senha", senha);
		Conta conta = null;
		try {
			conta = (Conta) query.getSingleResult();
		} catch (NoResultException ex) {
			ex.printStackTrace();
		}
		return conta;
	}

	/**
	 * Busca todas as contas de uma agência
	 * @param id
	 * @return
	 */
	public List<Conta> contasPorAgencia(Long id){
		TypedQuery<Conta> query = getEm().createQuery("SELECT c FROM conta c WHERE c.agencia_id = :id", Conta.class);
		query.setParameter("id", id);
		List<Conta> contas = null;
		contas = query.getResultList();
		return contas;
	}
}

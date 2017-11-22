package dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.entidades.Conta;

public class ContaDAO extends GenericDAO<Conta, Conta>{
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
}

package modelo.entidades;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import modelo.enuns.Operacao;

/**
 * Entity implementation class for Entity: MovimentacaoFinanceira
 *
 */
@Entity
@JsonAutoDetect
public class MovimentacaoFinanceira implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private Operacao operacao;

	@ManyToOne
	private Conta conta;

	@Temporal(value = TemporalType.DATE)
	private Date data;

	private double valor;

	@Transient
	private String descricao;

	private static final long serialVersionUID = 1L;

	public MovimentacaoFinanceira() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Operacao getOperacao() {
		return operacao;
	}
	public void setOperacao(Operacao operacao) {
		this.operacao = operacao;
	}
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovimentacaoFinanceira other = (MovimentacaoFinanceira) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	@Override
	public String toString() {
		String formatoAntigo = "yyyy-MM-dd";
		String novoFormato = "dd/MM/yyyy";
		String novaData;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(formatoAntigo);
			Date d = sdf.parse(data.toString());
			sdf.applyPattern(novoFormato);
			novaData = sdf.format(d);
			return operacao.getDescricao() + " na data = " + novaData + "\n"+" Valor = "
			+ valor;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

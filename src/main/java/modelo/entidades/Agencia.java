package modelo.entidades;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: Agencia
 *
 */
@Entity

public class Agencia implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String numero;

	@ManyToOne
	private Banco banco;

	private static final long serialVersionUID = 1L;

	public Agencia() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Banco getBanco() {
		return banco;
	}
	public void setBanco(Banco banco) {
		this.banco = banco;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		Agencia other = (Agencia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

}

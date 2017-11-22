package modelo.enuns;

public enum TipoConta {
	CORRENTE("Corrente"),
	POUPANCA("Poupança");

	private String descricao;
	private TipoConta(String descricao) {
		this.descricao = descricao;
	}
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static Operacao getTipoByNome(String nome) {
		if (nome != null) {
			for(Operacao e : Operacao.values() ) {
				if (e.name().equalsIgnoreCase(nome)) {
					return e;
				}
			}
		}
		return null;
	}
}

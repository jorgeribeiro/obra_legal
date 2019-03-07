package br.gov.ma.tce.obralegal.server.tipoprofissional;

public enum TipoProfissional {
	
	FISCALIZACAO("FISCALIZAÇÃO"), EXECUCAO("EXECUÇÃO"), CONSULTORIA("CONSULTORIA"), 
	PROJETISTA("PROJETISTA");

	private String descricao;

	private TipoProfissional(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}

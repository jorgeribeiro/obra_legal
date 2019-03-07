package br.gov.ma.tce.obralegal.server.tiposervico;

public enum TipoServico {

	PROJETO("PROJETO"), OUTROS("OUTROS");

	private String descricao;

	private TipoServico(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}

package br.gov.ma.tce.obralegal.server.tipoobra;

public enum TipoObra {

	CONSTRUCAO("CONSTRUÇÃO"), REFORMA("REFORMA"), AMPLIACAO("AMPLIAÇÃO"), OUTROS("OUTROS");

	private String descricao;

	private TipoObra(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
package br.gov.ma.tce.obralegal.server.objetopendencia;

public enum ObjetoPendencia {

	CONTROLES("CONTROLES"), LOCALIZACAO("LOCALIZAÇÃO"), PROFISSIONAIS("PROFISSIONAIS"), SITUACAO("SITUAÇÃO"),
	DOCUMENTOS("DOCUMENTOS");

	private String descricao;

	private ObjetoPendencia(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}

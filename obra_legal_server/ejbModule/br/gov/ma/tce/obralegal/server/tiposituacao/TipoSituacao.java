package br.gov.ma.tce.obralegal.server.tiposituacao;

public enum TipoSituacao {

	NAO_INICIADA("NÃO INICIADA"), PARALISADA("PARALISADA"), REINICIADA("REINICIADA"),
	PARALISADA_RECISAO("PARALISADA POR RECISÃO CONTRATUAL"), CONCLUIDA_NAO_RECEBIDA("CONCLUÍDA E NÃO RECEBIDA"),
	CONCLUIDA_RECEBIDA_PROVISORIAMENTE("CONCLUÍDA E RECEBIDA PROVISORIAMENTE"),
	CONCLUIDA_RECEBIDA("CONCLUÍDA E RECEBIDA DEFINITIVAMENTE"), INICIADA("INICIADA");

	private String descricao;

	private TipoSituacao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}

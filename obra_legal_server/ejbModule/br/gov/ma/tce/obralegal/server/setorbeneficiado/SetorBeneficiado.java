package br.gov.ma.tce.obralegal.server.setorbeneficiado;

public enum SetorBeneficiado {

	ADMINISTRACAO("ADMINISTRAÇÃO"), AGRICULTURA("AGRICULTURA"), ASSISTENCIA_SOCIAL("ASSISTÊNCIA SOCIAL"),
	CIENCIA_TECNOLOGIA("CIÊNCIA E TECNOLOGIA"), COMUNICACOES("COMUNICAÇÕES"), COMERCIO_SERVICOS("COMÉRCIO E SERVIÇOS"),
	CULTURA("CULTURA"), DEFESA_NACIONAL("DEFESA NACIONAL"), DESPORTO_LAZER("DESPORTO E LAZER"),
	DIREITOS_CIDADANIA("DIREITOS DA CIDADANIA"), EDUCACAO("EDUCAÇÃO"), ENCARGOS_ESPECIAIS("ENCARGOS ESPECIAIS"),
	ENERGIA("ENERGIA"), ESSENCIAL_JUSTICA("ESSENCIAL À JUSTIÇA"), GESTAO_AMBIENTAL("GESTÃO AMBIENTAL"),
	HABITACAO("HABITAÇÃO"), INDUSTRIA("INDÚSTRIA"), JUDICIARIA("JUDICIÁRIA"), LEGISLATIVA("LEGISLATIVA"),
	ORGANIZACAO_AGRARIA("ORGANIZAÇÃO AGRÁRIA"), OUTRO("OUTRO"), PREVIDENCIA_SOCIAL("PREVIDÊNCIA SOCIAL"),
	RELACOES_EXTERIORES("RELAÇÕES EXTERIORES"), RESERVA_CONTINGENCIA("RESERVA DE CONTINGÊNCIA"),
	SANEAMENTO("SANEAMENTO"), SAUDE("SAÚDE"), SEGURANCA_PUBLICA("SEGURANÇA PÚBLICA"), TRABALHO("TRABALHO"),
	TRANSPORTE("TRANSPORTE"), URBANISMO("URBANISMO");

	private String descricao;

	private SetorBeneficiado(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
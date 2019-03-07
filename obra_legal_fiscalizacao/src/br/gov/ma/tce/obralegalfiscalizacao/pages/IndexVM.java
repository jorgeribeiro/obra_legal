package br.gov.ma.tce.obralegalfiscalizacao.pages;

import java.util.Collection;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.Scope;
import org.zkoss.bind.annotation.ScopeParam;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;

import br.gov.ma.tce.obralegal.server.beans.obraservico.ObraServico;
import br.gov.ma.tce.obralegal.server.beans.obraservico.ObraServicoFacadeBean;
import br.gov.ma.tce.obralegal.server.beans.solicitacao.Solicitacao;
import br.gov.ma.tce.obralegal.server.beans.solicitacao.SolicitacaoFacadeBean;
import br.gov.ma.tce.seguranca.interno.server.beans.usuario.Usuario;

public class IndexVM {
	private Usuario usuario;

	private Collection<ObraServico> novasObras;
	private Collection<Solicitacao> novasSolicitacoes;
	
	private ObraServicoFacadeBean obraServicoFacade;
	private SolicitacaoFacadeBean solicitacaoFacade;

	public IndexVM() {
		try {
			InitialContext ctx = new InitialContext();
			obraServicoFacade = (ObraServicoFacadeBean) ctx.lookup(ObraServicoFacadeBean.URI);
			solicitacaoFacade = (SolicitacaoFacadeBean) ctx.lookup(SolicitacaoFacadeBean.URI);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Init
	public void init(
			@ScopeParam(scopes = Scope.SESSION, value = "usuario") Usuario usuario) {
		this.usuario = usuario;
		novasObras = obraServicoFacade.findNovasObrasServicos(60);
		novasSolicitacoes = solicitacaoFacade.findNovasSolicitacoesNaoRevisadas(60);
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Collection<ObraServico> getNovasObras() {
		return novasObras;
	}

	public void setNovasObras(Collection<ObraServico> novasObras) {
		this.novasObras = novasObras;
	}

	public Collection<Solicitacao> getNovasSolicitacoes() {
		return novasSolicitacoes;
	}

	public void setNovasSolicitacoes(Collection<Solicitacao> novasSolicitacoes) {
		this.novasSolicitacoes = novasSolicitacoes;
	}
}

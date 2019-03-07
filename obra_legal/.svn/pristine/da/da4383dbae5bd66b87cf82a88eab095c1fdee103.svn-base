package br.gov.ma.tce.obralegal.pages;

import java.util.Collection;
import java.util.Date;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.annotation.Scope;
import org.zkoss.bind.annotation.ScopeParam;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Window;

import br.gov.ma.tce.obralegal.server.beans.obraservico.ObraServico;
import br.gov.ma.tce.obralegal.server.beans.obraservico.ObraServicoFacadeBean;
import br.gov.ma.tce.obralegal.server.beans.solicitacao.Solicitacao;
import br.gov.ma.tce.obralegal.server.beans.solicitacao.SolicitacaoFacadeBean;
import br.gov.ma.tce.obralegal.server.exception.BusinessException;
import br.gov.ma.tce.obralegal.util.UsuarioObraLegal;

public class SolicitacoesVM {
	private Solicitacao novaSolicitacao, solicitacaoSelecionada;
	private ObraServico obraServicoSelecionado;

	private UsuarioObraLegal usuarioObraLegal;

	private Collection<ObraServico> obras;
	private Collection<Solicitacao> solicitacoes;

	private ObraServicoFacadeBean obraServicoFacade;
	private SolicitacaoFacadeBean solicitacaoFacade;

	@Wire("#modalNovaSolicitacao")
	private Window modalNovaSolicitacao;

	@Wire("#modalNovaSolicitacao #comboObras")
	private Combobox comboObras;

	@Wire("#modalVisualizarSolicitacao")
	private Window modalVisualizarSolicitacao;

	@Wire("#modalExcluirSolicitacao")
	private Window modalExcluirSolicitacao;

	public SolicitacoesVM() {
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
			@ScopeParam(scopes = Scope.SESSION, value = "usuarioObraLegal") UsuarioObraLegal usuarioObraLegal) {
		this.usuarioObraLegal = usuarioObraLegal;
		try {
			solicitacoes = solicitacaoFacade.findSolicitacoesByMandato(usuarioObraLegal.getMandatoId());
			obras = obraServicoFacade.findObrasServicosByMandato(usuarioObraLegal.getMandato2().getMandatoId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@Command
	@NotifyChange(".")
	public void abrirModalNovaSolicitacao(@BindingParam("visible") Boolean visible) {
		try {
			novaSolicitacao = new Solicitacao();
			novaSolicitacao.setOperadorJurisdicionado(usuarioObraLegal.getOperadorJurisdicionado());
			modalNovaSolicitacao.setVisible(visible);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	@NotifyChange(".")
	public void verificarObraServico() {
		try {
			if (novaSolicitacao.getObraServico().getDiasAlterarExcluir() > 0) {
				novaSolicitacao.setObraServico(null);
				comboObras.setSelectedItem(null);
				throw new BusinessException("Essa obra ainda está dentro do prazo de alteração/exclusão.");
			} else if (solicitacaoFacade
					.findSolicitacaoNaoRevisadaByObraServico(novaSolicitacao.getObraServico()) != null) {
				novaSolicitacao.setObraServico(null);
				comboObras.setSelectedItem(null);
				throw new BusinessException("Já existe uma solicitação pendente para esta obra/serviço.");
			}
		} catch (BusinessException e) {
			Clients.showNotification(e.getMessage(), Clients.NOTIFICATION_TYPE_WARNING, null, null, 3000, true);
		}
	}

	@Command
	@NotifyChange(".")
	public void enviarSolicitacao() {
		try {
			if (novaSolicitacao.getDiasSolicitados() == null || novaSolicitacao.getObraServico() == null
					|| novaSolicitacao.getObservacaoPedido() == null) {
				throw new BusinessException("Preencha todos os campos exibidos.");
			}

			modalNovaSolicitacao.setVisible(false);
			novaSolicitacao.setDataInclusao(new Date());
			solicitacaoFacade.include(novaSolicitacao);
			solicitacoes.add(novaSolicitacao);
			Clients.showNotification("Solicitação enviada com sucesso!", Clients.NOTIFICATION_TYPE_INFO, null, null,
					3000, true);
		} catch (BusinessException e) {
			Clients.showNotification(e.getMessage(), Clients.NOTIFICATION_TYPE_WARNING, null, null, 3000, true);
		}
	}

	@Command
	@NotifyChange(".")
	public void abrirModalVisualizarSolicitacao(@BindingParam("visible") Boolean visible,
			@BindingParam("solicitacao") Solicitacao solicitacao) {
		try {
			solicitacaoSelecionada = solicitacao;
			modalVisualizarSolicitacao.setVisible(visible);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	@NotifyChange(".")
	public void abrirModalExcluirSolicitacao(@BindingParam("visible") Boolean visible,
			@BindingParam("solicitacao") Solicitacao solicitacao) {
		try {
			if (solicitacao.getStatus() != null) {
				throw new BusinessException("Não é possível excluir uma solicitação que já foi revisada.");
			}

			solicitacaoSelecionada = solicitacao;
			modalExcluirSolicitacao.setVisible(visible);
		} catch (BusinessException e) {
			Clients.showNotification(e.getMessage(), Clients.NOTIFICATION_TYPE_WARNING, null, null, 3000, true);
		}
	}

	@Command
	@NotifyChange(".")
	public void excluirSolicitacao() {
		try {
			solicitacaoFacade.delete(solicitacaoSelecionada.getSolicitacaoId());
			solicitacoes.remove(solicitacaoSelecionada);
			modalExcluirSolicitacao.setVisible(false);
			Clients.showNotification("Solicitação excluída com sucesso!", Clients.NOTIFICATION_TYPE_INFO, null, null,
					3000, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Solicitacao getNovaSolicitacao() {
		return novaSolicitacao;
	}

	public void setNovaSolicitacao(Solicitacao novaSolicitacao) {
		this.novaSolicitacao = novaSolicitacao;
	}

	public Solicitacao getSolicitacaoSelecionada() {
		return solicitacaoSelecionada;
	}

	public void setSolicitacaoSelecionada(Solicitacao solicitacaoSelecionada) {
		this.solicitacaoSelecionada = solicitacaoSelecionada;
	}

	public ObraServico getObraServicoSelecionado() {
		return obraServicoSelecionado;
	}

	public void setObraServicoSelecionado(ObraServico obraServicoSelecionado) {
		this.obraServicoSelecionado = obraServicoSelecionado;
	}

	public UsuarioObraLegal getUsuarioObraLegal() {
		return usuarioObraLegal;
	}

	public void setUsuarioObraLegal(UsuarioObraLegal usuarioObraLegal) {
		this.usuarioObraLegal = usuarioObraLegal;
	}

	public Collection<ObraServico> getObras() {
		return obras;
	}

	public void setObras(Collection<ObraServico> obras) {
		this.obras = obras;
	}

	public Collection<Solicitacao> getSolicitacoes() {
		return solicitacoes;
	}

	public void setSolicitacoes(Collection<Solicitacao> solicitacoes) {
		this.solicitacoes = solicitacoes;
	}

}

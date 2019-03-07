package br.gov.ma.tce.obralegal.pages;

import java.util.Collection;

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
import org.zkoss.zul.Window;

import br.gov.ma.tce.obralegal.server.beans.obraservico.ObraServico;
import br.gov.ma.tce.obralegal.server.beans.obraservico.ObraServicoFacadeBean;
import br.gov.ma.tce.obralegal.server.beans.situacao.SituacaoFacadeBean;
import br.gov.ma.tce.obralegal.util.UsuarioObraLegal;

public class DocumentosVM {
	private ObraServico obraServicoSelecionado;

	private UsuarioObraLegal usuarioObraLegal;

	private Collection<ObraServico> obras;

	private ObraServicoFacadeBean obraServicoFacade;
	private SituacaoFacadeBean situacaoFacade;

	@Wire("#modalListarDocumentos")
	private Window modalListarDocumentos;

	@Wire("#modalInserirDocumentos")
	private Window modalInserirDocumentos;

	public DocumentosVM() {
		try {
			InitialContext ctx = new InitialContext();
			obraServicoFacade = (ObraServicoFacadeBean) ctx.lookup(ObraServicoFacadeBean.URI);
			situacaoFacade = (SituacaoFacadeBean) ctx.lookup(SituacaoFacadeBean.URI);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Init
	public void init(
			@ScopeParam(scopes = Scope.SESSION, value = "usuarioObraLegal") UsuarioObraLegal usuarioObraLegal) {
		this.usuarioObraLegal = usuarioObraLegal;
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@Command
	@NotifyChange(".")
	public void carregarObrasServicosPorMandato() {
		try {
			obras = obraServicoFacade.findObrasServicosByMandato(usuarioObraLegal.getMandato2().getMandatoId());
			for (ObraServico os : obras) {
				os.setSituacaoAtual(situacaoFacade.findMaxSituacaoByObraServico(os));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	@NotifyChange(".")
	public void abrirModalListarDocumentos(@BindingParam("obra") ObraServico obra,
			@BindingParam("visible") boolean visible) {
		try {
			obraServicoSelecionado = obra;
			modalListarDocumentos.setVisible(visible);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	@NotifyChange(".")
	public void abrirModalInserirDocumentos(@BindingParam("obra") ObraServico obra,
			@BindingParam("visible") boolean visible) {
		try {
			obraServicoSelecionado = obra;
			modalInserirDocumentos.setVisible(visible);
		} catch (Exception e) {
			e.printStackTrace();
		}
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

}

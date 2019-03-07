package br.gov.ma.tce.obralegalfiscalizacao.pages;

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
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;

import br.gov.ma.tce.gestores.server.beans.ente.Ente;
import br.gov.ma.tce.gestores.server.beans.ente.EnteFacade;
import br.gov.ma.tce.gestores.server.beans.ente.EnteFacadeBean;
import br.gov.ma.tce.obralegal.server.beans.obraservico.ObraServico;
import br.gov.ma.tce.obralegal.server.beans.obraservico.ObraServicoFacadeBean;
import br.gov.ma.tce.obralegal.server.tipoexecucao.TipoExecucao;

public class ExecucaoIndiretaVM {
	private Ente enteSelecionado;

	private Collection<ObraServico> obras;
	private Collection<Ente> entes;

	private ObraServicoFacadeBean obraServicoFacade;
	private EnteFacade enteFacade;

	public ExecucaoIndiretaVM() {
		try {
			InitialContext ctx = new InitialContext();
			obraServicoFacade = (ObraServicoFacadeBean) ctx.lookup(ObraServicoFacadeBean.URI);
			enteFacade = (EnteFacade) ctx.lookup(EnteFacadeBean.JNDI_OBRA_LEGAL);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Init
	public void init() {
		try {
			enteSelecionado = (Ente) Sessions.getCurrent().getAttribute("enteSelecionado");
			if (enteSelecionado != null) {
				carregarObrasPorEnte();
			}

			entes = enteFacade.buscarTodosOsEntes();
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
	public void carregarObrasPorEnte() {
		try {
			obras = obraServicoFacade.findObrasServicosByEnteAndTipoExecucao(enteSelecionado.getEnteId(),
					TipoExecucao.EXECUCAO_INDIRETA);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	@NotifyChange(".")
	public void abrirPageFiscalizacaoObraServico(@BindingParam("obraServico") ObraServico obraServico) {
		try {
			Sessions.getCurrent().setAttribute("enteSelecionado", enteSelecionado);
			Executions.getCurrent().sendRedirect("fiscalizacao.zul?id=" + obraServico.getObraServicoId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Ente getEnteSelecionado() {
		return enteSelecionado;
	}

	public void setEnteSelecionado(Ente enteSelecionado) {
		this.enteSelecionado = enteSelecionado;
	}

	public Collection<ObraServico> getObras() {
		return obras;
	}

	public void setObras(Collection<ObraServico> obrasServicos) {
		this.obras = obrasServicos;
	}

	public Collection<Ente> getEntes() {
		return entes;
	}

	public void setEntes(Collection<Ente> entes) {
		this.entes = entes;
	}

}

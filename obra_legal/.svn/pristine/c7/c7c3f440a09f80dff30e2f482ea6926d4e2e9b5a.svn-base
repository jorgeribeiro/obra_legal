package br.gov.ma.tce.obralegal.pages;

import java.util.Collection;
import java.util.Date;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.io.FilenameUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.annotation.Scope;
import org.zkoss.bind.annotation.ScopeParam;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

import br.gov.ma.tce.obralegal.server.beans.obraservico.ObraServico;
import br.gov.ma.tce.obralegal.server.beans.obraservico.ObraServicoFacadeBean;
import br.gov.ma.tce.obralegal.server.beans.relatorioperiodico.RelatorioPeriodico;
import br.gov.ma.tce.obralegal.server.beans.relatorioperiodico.RelatorioPeriodicoFacadeBean;
import br.gov.ma.tce.obralegal.util.FTPUtil;
import br.gov.ma.tce.obralegal.util.UsuarioObraLegal;

public class RelatoriosPeriodicosObrasVM {
	private RelatorioPeriodico relatorio;

	private UsuarioObraLegal usuarioObraLegal;
	private Media media;

	private Collection<ObraServico> obras;
	private Collection<RelatorioPeriodico> relatorios;

	private ObraServicoFacadeBean obraServicoFacade;
	private RelatorioPeriodicoFacadeBean relatorioFacade;

	@Wire("#modalListarRelatórios")
	private Window modalListarRelatórios;

	@Wire("#modalInserirRelatorio")
	private Window modalInserirRelatorio;

	public RelatoriosPeriodicosObrasVM() {
		try {
			InitialContext ctx = new InitialContext();
			obraServicoFacade = (ObraServicoFacadeBean) ctx.lookup(ObraServicoFacadeBean.URI);
			relatorioFacade = (RelatorioPeriodicoFacadeBean) ctx.lookup(RelatorioPeriodicoFacadeBean.URI);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	@NotifyChange(".")
	public void abrirModalListarRelatorios(@BindingParam("obra") ObraServico obra,
			@BindingParam("visible") boolean visible) {
		try {
			if (visible) {
				relatorios = relatorioFacade.findRelatoriosByObraServico(obra);
			}
			modalListarRelatórios.setVisible(visible);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	@NotifyChange(".")
	public void abrirModalInserirRelatorio(@BindingParam("obra") ObraServico obra,
			@BindingParam("visible") boolean visible) {
		try {
			if (visible) {
				relatorio = new RelatorioPeriodico();
				relatorio.setObraServico(obra);
				relatorio.setDataInclusao(new Date());
				relatorio.setCumpriu(false);
			}
			media = null;
			modalInserirRelatorio.setVisible(visible);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	@NotifyChange(".")
	public void uploadRelatorio(@BindingParam("upEvent") UploadEvent event) {
		try {
			// TODO definir tamanho máximo de arquivo e formatos aceitos
			media = event.getMedia();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	@NotifyChange
	public void inserirRelatorio() {
		try {
			if (relatorio.getData() == null) {
				throw new Exception("Informa a data do relatório.");
			}
			if (media == null) {
				throw new Exception("Realize o upload do arquivo do relatório.");
			}
			relatorio = relatorioFacade.include(relatorio);
			String extensao = "." + FilenameUtils.getExtension(media.getName());
			try {
				FTPUtil.upload2(media, "relatorio_periodico_id_" + relatorio.getRelatorioPeriodicoId() + extensao,
						"relatorios_periodicos/relatorio_periodico_id_" + relatorio.getRelatorioPeriodicoId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			modalInserirRelatorio.setVisible(false);
			Clients.showNotification("Relatório inserido com sucesso!", Clients.NOTIFICATION_TYPE_INFO, null, null,
					3000, true);
		} catch (Exception e) {
			Clients.showNotification(e.getMessage(), Clients.NOTIFICATION_TYPE_WARNING, null, null, 3000, true);
		}
	}

	public RelatorioPeriodico getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(RelatorioPeriodico relatorio) {
		this.relatorio = relatorio;
	}

	public UsuarioObraLegal getUsuarioObraLegal() {
		return usuarioObraLegal;
	}

	public void setUsuarioObraLegal(UsuarioObraLegal usuarioObraLegal) {
		this.usuarioObraLegal = usuarioObraLegal;
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public Collection<ObraServico> getObras() {
		return obras;
	}

	public void setObras(Collection<ObraServico> obras) {
		this.obras = obras;
	}

	public Collection<RelatorioPeriodico> getRelatorios() {
		return relatorios;
	}

	public void setRelatorios(Collection<RelatorioPeriodico> relatorios) {
		this.relatorios = relatorios;
	}

}

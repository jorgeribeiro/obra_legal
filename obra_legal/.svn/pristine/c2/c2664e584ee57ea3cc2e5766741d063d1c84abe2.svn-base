package br.gov.ma.tce.obralegal.pages;

import java.util.ArrayList;
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
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Window;

import br.gov.ma.tce.gestores.server.beans.ente.Ente;
import br.gov.ma.tce.gestores.server.beans.mandato.Mandato;
import br.gov.ma.tce.gestores.server.beans.mandato.MandatoFacade;
import br.gov.ma.tce.gestores.server.beans.mandato.MandatoFacadeBean;
import br.gov.ma.tce.obralegal.server.exception.BusinessException;
import br.gov.ma.tce.obralegal.util.UsuarioObraLegal;

public class IndexVM {
	private UsuarioObraLegal usuarioObraLegal;

	private Ente enteSelecionado;

	private Collection<Mandato> mandatosResponsavel;
	private Collection<Mandato> mandatosPorEnte;
	private Collection<Ente> entesMandatos;

	private MandatoFacade mandatoFacade;

	@Wire("#modalSelecionaMandato")
	private Window modalSelecionaMandato;

	@Wire("#modalSelecionaMandato #comboEntes")
	private Combobox comboEntes;

	public IndexVM() {
		try {
			InitialContext ctx = new InitialContext();
			mandatoFacade = (MandatoFacade) ctx.lookup(MandatoFacadeBean.JNDI_OBRA_LEGAL);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Init
	public void init(
			@ScopeParam(scopes = Scope.SESSION, value = "usuarioObraLegal") UsuarioObraLegal usuarioObraLegal) {
		this.usuarioObraLegal = usuarioObraLegal;
		entesMandatos = new ArrayList<Ente>();
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		// TODO redirecionar para tela de erro caso exceção seja encontrada
		try {
			if (usuarioObraLegal.getOperadorJurisdicionado() == null) {
				mandatosResponsavel = mandatoFacade.findMandatoByResponsavelComResponsabilidadesSortByEnte(
						usuarioObraLegal.getUsuario().getResponsavelId());
				for (Mandato m : mandatosResponsavel) {
					if (!entesMandatos.contains(m.getUnidade().getOrgao().getEnte())) {
						entesMandatos.add(m.getUnidade().getOrgao().getEnte());
					}
				}

				if (usuarioObraLegal.getMandatoId() == null) {
					modalSelecionaMandato.setVisible(true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	@NotifyChange(".")
	public void carregarMandatosPorEnte() {
		try {
			mandatosPorEnte = mandatoFacade.findMandatoByResponsavelAndEnte(
					usuarioObraLegal.getUsuario().getResponsavelId(), enteSelecionado.getEnteId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	@NotifyChange(".")
	public void abrirModalSelecionaMandato(@BindingParam("visible") Boolean visible) {
		try {
			enteSelecionado = null;
			mandatosPorEnte = null;
			comboEntes.setSelectedItem(null);
			modalSelecionaMandato.setVisible(visible);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	@NotifyChange(".")
	public void selecionaMandato(@BindingParam("mandato") Mandato mandato) {
		try {
			if (!mandato.isJaValidado()) {
				throw new BusinessException(
						"Mandato não validado junto ao TCE-MA. Entre em contato com o órgão para regularização.");
			}

			usuarioObraLegal.setMandatoId(mandato.getMandatoId());
			Sessions.getCurrent().setAttribute("usuarioObraLegal", usuarioObraLegal);

			enteSelecionado = null;
			mandatosPorEnte = null;
			comboEntes.setSelectedItem(null);
			modalSelecionaMandato.setVisible(false);
			Clients.showNotification("Mandato selecionado com sucesso!", Clients.NOTIFICATION_TYPE_INFO, null, null,
					3000, true);
		} catch (BusinessException e) {
			Clients.showNotification(e.getMessage(), Clients.NOTIFICATION_TYPE_WARNING, null, null, 4000, true);
		}
	}

	@Command
	@NotifyChange(".")
	public void selecionarOutroMandato() {
		try {
			mandatosResponsavel = null;
			enteSelecionado = null;
			modalSelecionaMandato.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public UsuarioObraLegal getUsuarioObraLegal() {
		return usuarioObraLegal;
	}

	public void setUsuarioObraLegal(UsuarioObraLegal usuarioObraLegal) {
		this.usuarioObraLegal = usuarioObraLegal;
	}

	public Ente getEnteSelecionado() {
		return enteSelecionado;
	}

	public void setEnteSelecionado(Ente enteSelecionado) {
		this.enteSelecionado = enteSelecionado;
	}

	public Collection<Mandato> getMandatosResponsavel() {
		return mandatosResponsavel;
	}

	public void setMandatosResponsavel(Collection<Mandato> mandatos) {
		this.mandatosResponsavel = mandatos;
	}

	public Collection<Mandato> getMandatosPorEnte() {
		return mandatosPorEnte;
	}

	public void setMandatosPorEnte(Collection<Mandato> mandatosPorMunicipio) {
		this.mandatosPorEnte = mandatosPorMunicipio;
	}

	public Collection<Ente> getEntesMandatos() {
		return entesMandatos;
	}

	public void setEntesMandatos(Collection<Ente> entesMandatos) {
		this.entesMandatos = entesMandatos;
	}

}

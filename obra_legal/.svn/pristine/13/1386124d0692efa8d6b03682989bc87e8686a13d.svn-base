package br.gov.ma.tce.obralegal.pages;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.annotation.Scope;
import org.zkoss.bind.annotation.ScopeParam;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;

import br.gov.ma.tce.obralegal.util.UsuarioObraLegal;

public class MenubarVM {
	private UsuarioObraLegal usuarioObraLegal;

	@Init
	public void init(
			@ScopeParam(scopes = Scope.SESSION, value = "usuarioObraLegal") UsuarioObraLegal usuarioObraLegal) {
		this.usuarioObraLegal = usuarioObraLegal;
		if (usuarioObraLegal == null) {
			Executions.sendRedirect("/login.zul");
		}
	}

	@Command
	@NotifyChange(".")
	public void sair() {
		try {
			Sessions.getCurrent().invalidate();
			Executions.sendRedirect("/login.zul");
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

}

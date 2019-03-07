package br.gov.ma.tce.obralegal.util;

import java.util.Map;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Initiator;

public class InitPage implements Initiator {

	@Override
	public void doInit(Page arg0, Map<String, Object> arg1) throws Exception {
		UsuarioObraLegal usuarioObraLegal = (UsuarioObraLegal) Sessions.getCurrent().getAttribute("usuarioObraLegal");
		if (usuarioObraLegal == null) {
			Executions.sendRedirect("/login.zul");
		}
	}

}

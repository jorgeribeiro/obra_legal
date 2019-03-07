package br.gov.ma.tce.obralegal.pages;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.annotation.Scope;
import org.zkoss.bind.annotation.ScopeParam;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Clients;

import br.gov.ma.tce.gestores.server.beans.responsavel.Responsavel;
import br.gov.ma.tce.gestores.server.beans.responsavel.ResponsavelFacade;
import br.gov.ma.tce.gestores.server.beans.responsavel.ResponsavelFacadeBean;
import br.gov.ma.tce.gestores.server.beans.usuario.Usuario;
import br.gov.ma.tce.gestores.server.beans.usuario.UsuarioFacade;
import br.gov.ma.tce.gestores.server.beans.usuario.UsuarioFacadeBean;
import br.gov.ma.tce.obralegal.server.beans.operadorjurisdicionado.OperadorJurisdicionado;
import br.gov.ma.tce.obralegal.server.beans.operadorjurisdicionado.OperadorJurisdicionadoFacadeBean;
import br.gov.ma.tce.obralegal.server.exception.BusinessException;
import br.gov.ma.tce.obralegal.server.util.HashUtil;
import br.gov.ma.tce.obralegal.util.UsuarioObraLegal;

public class LoginVM {
	private String login = "";
	private String senha = "";

	private OperadorJurisdicionado operadorJurisdicionado;
	private UsuarioObraLegal usuarioObraLegal;

	private Usuario usuario;

	// private AcessoLogFacadeBean acessoLogFacade;
	private OperadorJurisdicionadoFacadeBean operadorJurisdicionadoFacade;
	private UsuarioFacade usuarioFacade;
	private ResponsavelFacade responsavelFacade;

	public LoginVM() {
		try {
			InitialContext ctx = new InitialContext();
			// acessoLogFacade = (AcessoLogFacadeBean) ctx.lookup(AcessoLogFacadeBean.URI);
			operadorJurisdicionadoFacade = (OperadorJurisdicionadoFacadeBean) ctx
					.lookup(OperadorJurisdicionadoFacadeBean.URI);
			usuarioFacade = (UsuarioFacade) ctx.lookup(UsuarioFacadeBean.JNDI_OBRA_LEGAL);
			responsavelFacade = (ResponsavelFacade) ctx.lookup(ResponsavelFacadeBean.JNDI_OBRA_LEGAL); //
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Init
	public void init(
			@ScopeParam(scopes = Scope.SESSION, value = "usuarioObraLegal") UsuarioObraLegal usuarioObraLegal) {
		if (usuarioObraLegal != null) {
			Executions.sendRedirect("/index.zul");
		}
	}

	@Command
	@NotifyChange(".")
	public void login() {
		try {
			if ((login == null || login.length() == 0) || (senha == null || senha.length() == 0)) {
				throw new BusinessException("Insira login e senha para entrar no sistema.");
			}

			usuario = usuarioFacade.findByLogin(login);
			if (usuario != null && !usuario.getSenha().equals(senha)) {
				throw new BusinessException("Login ou senha inválidos.");
			}

			operadorJurisdicionado = operadorJurisdicionadoFacade.findByCpf(login);
			if (operadorJurisdicionado != null && !HashUtil.rehashSenha(senha, operadorJurisdicionado.getSenha())) {
				throw new BusinessException("Login ou senha inválidos.");
			}

			// Se gestor
			if (usuario != null) {
				UsuarioObraLegal usuarioObraLegal = new UsuarioObraLegal(usuario.getUsuarioId(), null);
				Responsavel responsavel = responsavelFacade.buscarResponsavelPorId(usuario.getResponsavelId());
				usuarioObraLegal.setNomeUsuario(responsavel.getNome());
				Sessions.getCurrent().setAttribute("usuarioObraLegal", usuarioObraLegal);
				// Se operador
			} else if (operadorJurisdicionado != null) {
				if (!operadorJurisdicionado.getAtivo()) {
					throw new BusinessException(
							"Conta de operador desativada. Entre em contato com o gestor da sua unidade para mais informações.");
				}
				UsuarioObraLegal usuarioObraLegal = new UsuarioObraLegal(null,
						operadorJurisdicionado.getMandato().getMandatoId());
				usuarioObraLegal.setNomeUsuario(operadorJurisdicionado.getNome());
				usuarioObraLegal.setOperadorJurisdicionado(operadorJurisdicionado);
				Sessions.getCurrent().setAttribute("usuarioObraLegal", usuarioObraLegal);
			}
			Executions.sendRedirect("/index.zul");
		} catch (BusinessException e) {
			Clients.showNotification(e.getMessage(), Clients.NOTIFICATION_TYPE_WARNING, null, null, 3000, true);
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public OperadorJurisdicionado getOperadorJurisdicionado() {
		return operadorJurisdicionado;
	}

	public void setOperadorJurisdicionado(OperadorJurisdicionado operadorJurisdicionado) {
		this.operadorJurisdicionado = operadorJurisdicionado;
	}

	public UsuarioObraLegal getUsuarioObraLegal() {
		return usuarioObraLegal;
	}

	public void setUsuarioObraLegal(UsuarioObraLegal usuarioObraLegal) {
		this.usuarioObraLegal = usuarioObraLegal;
	}

}

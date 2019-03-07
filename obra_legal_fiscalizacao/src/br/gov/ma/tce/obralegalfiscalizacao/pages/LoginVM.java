package br.gov.ma.tce.obralegalfiscalizacao.pages;

import java.util.Collection;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.annotation.Scope;
import org.zkoss.bind.annotation.ScopeParam;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.util.Clients;

import br.gov.ma.tce.obralegal.server.exception.BusinessException;
import br.gov.ma.tce.seguranca.interno.server.beans.usuario.Usuario;
import br.gov.ma.tce.seguranca.interno.server.beans.usuario.UsuarioFacadeBean;
import br.gov.ma.tce.seguranca.interno.server.beans.usuariogrupo.UsuarioGrupo;
import br.gov.ma.tce.seguranca.interno.server.beans.usuariogrupo.UsuarioGrupoFacadeBean;

public class LoginVM {
	private Integer matricula;
	private String senha = "";

	private Usuario usuario;
	
	private UsuarioFacadeBean usuarioFacadeBean;
	private UsuarioGrupoFacadeBean usuarioGrupoFacadeBean;

	public LoginVM() {
		try {
			InitialContext ctx = new InitialContext();			
			usuarioFacadeBean = (UsuarioFacadeBean) ctx.lookup(UsuarioFacadeBean.JNDI_OBRALEGAL);
			usuarioGrupoFacadeBean = (UsuarioGrupoFacadeBean) ctx.lookup(UsuarioGrupoFacadeBean.JNDI_OBRALEGAL);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Init
	public void init(
			@ScopeParam(scopes = Scope.SESSION, value = "usuario") Usuario usuario) {
		if (usuario != null) {
			Executions.sendRedirect("/index.zul");
		}
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@Command
	@NotifyChange(".")
	public void login() {
		try {
			Boolean temErro = true;
			if (matricula == null || (senha == null || senha.length() == 0)) {
				throw new BusinessException("Insira matrícula e senha para entrar no sistema.");
			}
			
			usuario = usuarioFacadeBean.findByMatricula(matricula);
			if (usuario == null) {
				throw new BusinessException("Matrícula ou senha inválidos.");
			} else if (!usuario.getSenha().equals(senha)) {
				throw new BusinessException("Matrícula ou senha inválidos.");
			} else {
				Collection<UsuarioGrupo> usuarioGrupos = usuarioGrupoFacadeBean.findGruposByUsuario(usuario);
				for (UsuarioGrupo grupo : usuarioGrupos) {
					if (grupo.getGrupoUsuario().getSistema().getSigla().equals("OBRA")) {
						temErro = false;
						Sessions.getCurrent().setAttribute("usuario", usuario);
						Sessions.getCurrent().setAttribute("usuarioGrupo", grupo);
						Executions.sendRedirect("/index.zul");
					}
				}
				
				if (temErro) {
					throw new BusinessException("Usuário sem permissão de acesso ao Obra Legal.");
				}
			}
		} catch (BusinessException e) {
			Clients.showNotification(e.getMessage(), Clients.NOTIFICATION_TYPE_WARNING, null, null, 3800, true);
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

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer login) {
		this.matricula = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}

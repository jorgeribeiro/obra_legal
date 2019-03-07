package br.gov.ma.tce.obralegal.util;

import java.io.Serializable;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.gov.ma.tce.gestores.server.beans.mandato.Mandato;
import br.gov.ma.tce.gestores.server.beans.mandato.MandatoFacade;
import br.gov.ma.tce.gestores.server.beans.mandato.MandatoFacadeBean;
import br.gov.ma.tce.gestores.server.beans.usuario.Usuario;
import br.gov.ma.tce.gestores.server.beans.usuario.UsuarioFacade;
import br.gov.ma.tce.gestores.server.beans.usuario.UsuarioFacadeBean;
import br.gov.ma.tce.obralegal.server.beans.operadorjurisdicionado.OperadorJurisdicionado;

public class UsuarioObraLegal implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer usuarioId;
	private Integer mandatoId;
	private String nomeUsuario;
	
	// Se operadorJurisdicionado != null, quer dizer que o usuário logado é operador
	// Senão, quer dizer que o usuário logado é o próprio gestor
	private OperadorJurisdicionado operadorJurisdicionado;

	private UsuarioFacade usuarioFacade;
	private MandatoFacade mandatoFacade;

	public UsuarioObraLegal(Integer usuarioId, Integer mandatoId) {
		super();
		this.usuarioId = usuarioId;
		this.mandatoId = mandatoId;

		try {
			InitialContext ctx = new InitialContext();
			usuarioFacade = (UsuarioFacade) ctx.lookup(UsuarioFacadeBean.JNDI_OBRA_LEGAL);
			mandatoFacade = (MandatoFacade) ctx.lookup(MandatoFacadeBean.JNDI_OBRA_LEGAL);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public Usuario getUsuario() {
		if (usuarioId != null) {
			return usuarioFacade.findByPrimaryKey(this.usuarioId);
		}
		return null;
	}

	public Mandato getMandato() {
		if (mandatoId != null) {
			return mandatoFacade.findByPrimaryKey(mandatoId);
		}
		return null;
	}

	public Mandato getMandato2() {
		// Retorna mandato sem responsabilidades
		if (mandatoId != null) {
			return mandatoFacade.findByPrimaryKey2(mandatoId);
		}
		return null;
	}

	public String getMandatoStr() {
		try {
			Mandato mandato = getMandato2();
			return mandato.getNomeUnidade() + " - " + mandato.getNomeEnte();
		} catch (Exception e) {
			return "-";
		}
	}

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Integer getMandatoId() {
		return mandatoId;
	}

	public void setMandatoId(Integer mandatoId) {
		this.mandatoId = mandatoId;
	}

	public String getNomeUsuario() {
		if (getOperadorJurisdicionado() != null) {
			return nomeUsuario + " (Operador)";
		} else {
			return nomeUsuario;
		}		
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public OperadorJurisdicionado getOperadorJurisdicionado() {
		return operadorJurisdicionado;
	}

	public void setOperadorJurisdicionado(OperadorJurisdicionado operadorJurisdicionado) {
		this.operadorJurisdicionado = operadorJurisdicionado;
	}

}

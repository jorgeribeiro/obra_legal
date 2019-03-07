package br.gov.ma.tce.obralegal.server.beans.acessolog;

import java.io.Serializable;
import java.util.Date;

import javax.naming.InitialContext;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.gov.ma.tce.seguranca.interno.server.beans.usuario.Usuario;
import br.gov.ma.tce.seguranca.interno.server.beans.usuario.UsuarioFacadeBean;


@Entity(name = AcessoLog.NAME)
@Table(schema = "obralegal", name = "acesso_log")
public class AcessoLog implements Serializable {
	public static final String NAME = "obralegal_Acesso_Log";
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "obralegal.seq_acesso_log", sequenceName = "obralegal.seq_acesso_log", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "obralegal.seq_acesso_log")
	@Column(name = "acesso_log_id", columnDefinition = "INT4")
	private Integer acessoLogId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_hora_acesso")
	private Date dataHoraAcesso;

	@Column(name = "ip_acesso", columnDefinition = "TEXT")
	private String ipAcesso;

	@Column(name = "usuario_id", columnDefinition = "INT4")
	private Integer usuarioId;

	@Transient
	private Usuario usuario;

	public Integer getAcessoLogId() {
		return acessoLogId;
	}

	public void setAcessoLogId(Integer acessoLogId) {
		this.acessoLogId = acessoLogId;
	}

	public Date getDataHoraAcesso() {
		return dataHoraAcesso;
	}

	public void setDataHoraAcesso(Date dataHoraAcesso) {
		this.dataHoraAcesso = dataHoraAcesso;
	}

	public String getIpAcesso() {
		return ipAcesso;
	}

	public void setIpAcesso(String ipAcesso) {
		this.ipAcesso = ipAcesso;
	}

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Usuario getUsuario() {
		if (this.getUsuarioId() != null) {
			try {
				UsuarioFacadeBean usuarioFacade;
				InitialContext ctx = new InitialContext();
				usuarioFacade = (UsuarioFacadeBean) ctx.lookup(UsuarioFacadeBean.JNDI_OBRALEGAL);
				usuario = usuarioFacade.findByPrimaryKey(this.getUsuarioId());
			} catch (Exception e) {
				e.printStackTrace();
				usuario = null;
			}
			return usuario;
		} else {
			return null;
		}
	}

	public void setUsuario(Usuario usuario) {
		try {
			if (usuario == null) {
				this.usuario = null;
				this.usuarioId = null;
			} else {
				this.usuario = usuario;
				this.usuarioId = usuario.getUsuarioId();
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

}

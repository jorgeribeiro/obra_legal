package br.gov.ma.tce.obralegal.server.beans.solicitacao;

import java.io.Serializable;
import java.util.Date;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.gov.ma.tce.obralegal.server.beans.obraservico.ObraServico;
import br.gov.ma.tce.obralegal.server.beans.operadorjurisdicionado.OperadorJurisdicionado;
import br.gov.ma.tce.seguranca.interno.server.beans.usuario.Usuario;
import br.gov.ma.tce.seguranca.interno.server.beans.usuario.UsuarioFacadeBean;

@Entity(name = Solicitacao.NAME)
@Table(schema = "obralegal", name = "solicitacao")
public class Solicitacao implements Serializable {
	public static final String NAME = "obralegal_Solicitacao";
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "obralegal.seq_solicitacao", sequenceName = "obralegal.seq_solicitacao", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "obralegal.seq_solicitacao")
	@Column(name = "solicitacao_id", columnDefinition = "INT4")
	private Integer solicitacaoId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_inclusao")
	private Date dataInclusao;

	@Column(name = "dias_solicitados", columnDefinition = "INT4")
	private Integer diasSolicitados;

	@Column(name = "dias_autorizados", columnDefinition = "INT4")
	private Integer diasAutorizados;

	@Column(name = "observacao_pedido", columnDefinition = "TEXT")
	private String observacaoPedido;

	@Column(name = "observacao_atendimento", columnDefinition = "TEXT")
	private String observacaoAtendimento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_atendimento")
	private Date dataAtendimento;

	@Column(name = "status", columnDefinition = "BOOLEAN")
	private Boolean status;

	@Column(name = "usuario_id", columnDefinition = "INT4")
	private Integer usuarioId;

	@ManyToOne
	@JoinColumn(name = "obra_servico_id", columnDefinition = "INT4")
	private ObraServico obraServico;

	@ManyToOne
	@JoinColumn(name = "operador_jurisdicionado_id", columnDefinition = "INT4")
	private OperadorJurisdicionado operadorJurisdicionado;

	@Transient
	private Usuario usuario;

	@Transient
	public String getStatusStr() {
		try {
			if (status == null) {
				return "Solicitação ainda não revisada.";
			} else if (status) {
				return "Solicitação aprovada.";
			} else {
				return "Solicitação reprovada.";
			}
		} catch (Exception e) {
			return "-";
		}
	}

	@Transient
	public String getDiasAutorizadosStr() {
		try {
			if (status == null) {
				return "-";
			} else if (status) {
				return diasAutorizados.toString();
			} else {
				return "-";
			}
		} catch (Exception e) {
			return "-";
		}
	}

	@Transient
	public String getObservacaoAtendimentoStr() {
		try {
			if (observacaoAtendimento == null) {
				return "-";
			} else {
				return observacaoAtendimento;
			}
		} catch (Exception e) {
			return "-";
		}
	}

	@Transient
	public String getDataAtendimentoStr() {
		try {
			if (dataAtendimento == null) {
				return "-";
			} else {
				return dataAtendimento.toString();
			}
		} catch (Exception e) {
			return "-";
		}
	}

	public Integer getSolicitacaoId() {
		return solicitacaoId;
	}

	public void setSolicitacaoId(Integer solicitacaoId) {
		this.solicitacaoId = solicitacaoId;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Integer getDiasSolicitados() {
		return diasSolicitados;
	}

	public void setDiasSolicitados(Integer diasSolicitados) {
		this.diasSolicitados = diasSolicitados;
	}

	public Integer getDiasAutorizados() {
		return diasAutorizados;
	}

	public void setDiasAutorizados(Integer diasAutorizados) {
		this.diasAutorizados = diasAutorizados;
	}

	public String getObservacaoPedido() {
		return observacaoPedido;
	}

	public void setObservacaoPedido(String observacaoPedido) {
		this.observacaoPedido = observacaoPedido;
	}

	public String getObservacaoAtendimento() {
		return observacaoAtendimento;
	}

	public void setObservacaoAtendimento(String observacaoAtendimento) {
		this.observacaoAtendimento = observacaoAtendimento;
	}

	public Date getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(Date dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public ObraServico getObraServico() {
		return obraServico;
	}

	public void setObraServico(ObraServico obraServico) {
		this.obraServico = obraServico;
	}

	public OperadorJurisdicionado getOperadorJurisdicionado() {
		return operadorJurisdicionado;
	}

	public void setOperadorJurisdicionado(OperadorJurisdicionado operadorJurisdicionado) {
		this.operadorJurisdicionado = operadorJurisdicionado;
	}

	public Usuario getUsuario() {
		if (this.getUsuarioId() != null) {
			try {
				UsuarioFacadeBean usuarioFacade;
				InitialContext ctx = new InitialContext();
				usuarioFacade = (UsuarioFacadeBean) ctx.lookup(UsuarioFacadeBean.JNDI_OBRALEGAL);
				usuario = usuarioFacade.findByPrimaryKey(this.getUsuarioId());
			} catch (NamingException e) {
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

package br.gov.ma.tce.obralegal.server.beans.obraservico;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import br.gov.ma.tce.gestores.server.beans.ente.Ente;
import br.gov.ma.tce.gestores.server.beans.ente.EnteFacade;
import br.gov.ma.tce.gestores.server.beans.ente.EnteFacadeBean;
import br.gov.ma.tce.gestores.server.beans.mandato.Mandato;
import br.gov.ma.tce.gestores.server.beans.mandato.MandatoFacade;
import br.gov.ma.tce.gestores.server.beans.mandato.MandatoFacadeBean;
import br.gov.ma.tce.obralegal.server.beans.situacao.Situacao;
import br.gov.ma.tce.obralegal.server.beans.situacao.SituacaoFacadeBean;
import br.gov.ma.tce.obralegal.server.beans.solicitacao.Solicitacao;
import br.gov.ma.tce.obralegal.server.beans.solicitacao.SolicitacaoFacadeBean;
import br.gov.ma.tce.obralegal.server.setorbeneficiado.SetorBeneficiado;
import br.gov.ma.tce.obralegal.server.tipoexecucao.TipoExecucao;
import br.gov.ma.tce.obralegal.server.tipoobra.TipoObra;
import br.gov.ma.tce.obralegal.server.tipoobraservico.TipoObraServico;
import br.gov.ma.tce.obralegal.server.tiposervico.TipoServico;
import br.gov.ma.tce.sacop.server.beans.contrato.Contrato;
import br.gov.ma.tce.sacop.server.beans.contrato.ContratoFacadeBean;

@Entity(name = ObraServico.NAME)
@Table(schema = "obralegal", name = "obra_servico")
public class ObraServico implements Serializable {
	public static final String NAME = "obralegal_Obra_Servico";
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "obralegal.seq_obra_servico", sequenceName = "obralegal.seq_obra_servico", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "obralegal.seq_obra_servico")
	@Column(name = "obra_servico_id", columnDefinition = "INT4")
	private Integer obraServicoId;

	@Column(name = "bem_publico", columnDefinition = "TEXT")
	private String bemPublico;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_inclusao")
	private Date dataInclusao;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_inicio")
	private Date dataInicio;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_fim")
	private Date dataFim;

	@Column(name = "prazo", columnDefinition = "INT4")
	private Integer prazo;

	@Column(name = "n_ordem_servico", columnDefinition = "INT4")
	private Integer nOrdemServico;

	@Column(name = "descricao", columnDefinition = "TEXT")
	private String descricao;

	@Column(name = "endereco", columnDefinition = "TEXT")
	private String endereco;

	@Column(name = "bairro", columnDefinition = "TEXT")
	private String bairro;

	@Column(name = "cep", columnDefinition = "TEXT")
	private String cep;

	@Column(name = "valor_inicial", precision = 18, scale = 2, columnDefinition = "NUMERIC(18,2)")
	private Double valorInicial;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_entrega")
	private Date dataEntrega;

	@Column(name = "contrato_id", columnDefinition = "INT4")
	private Integer contratoId;

	@Column(name = "mandato_id", columnDefinition = "INT4")
	private Integer mandatoId;

	@Column(name = "ente_id", columnDefinition = "INT4")
	private Integer enteId;

	@Enumerated(EnumType.STRING)
	@Column(name = "setor_beneficiado", columnDefinition = "TEXT")
	private SetorBeneficiado setorBeneficiado;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_execucao", columnDefinition = "TEXT")
	private TipoExecucao tipoExecucao;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_obra", columnDefinition = "TEXT")
	private TipoObra tipoObra;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_servico", columnDefinition = "TEXT")
	private TipoServico tipoServico;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_obra_servico", columnDefinition = "TEXT")
	private TipoObraServico tipoObraServico;

	@Transient
	private Contrato contrato;

	@Transient
	private Mandato mandato;

	@Transient
	private Ente ente;

	@Transient
	private Situacao situacaoAtual;

	@Transient
	public String getObraServicoStr() {
		try {
			return "Código: " + getObraServicoId() + " / Bem público: " + getBemPublicoStr();
		} catch (Exception e) {
			return "-";
		}
	}

	@Transient
	public String getDataInclusaoStr() {
		try {
			DateFormat df = DateFormat.getDateInstance(DateFormat.DEFAULT);
			return df.format(this.getDataInclusao());
		} catch (Exception e) {
			return "-";
		}
	}

	@Transient
	public String getDatasStr() {
		try {
			String toReturn = "";
			DateFormat df = DateFormat.getDateInstance(DateFormat.DEFAULT);
			if (getDataInicio() != null) {
				toReturn = toReturn.concat("Data início: " + df.format(this.getDataInicio()));
			} else {
				toReturn = toReturn.concat("[Data início não informada] ");
			}

			if (getDataFim() != null) {
				toReturn = toReturn.concat(", Data fim: " + df.format(this.getDataFim()));
			} else {
				toReturn = toReturn.concat("[Data fim não informada] ");
			}

			if (getDataEntrega() != null) {
				toReturn = toReturn.concat(", Data de entrega: " + df.format(this.getDataEntrega()));
			} else {
				toReturn = toReturn.concat("[Data de entrega não informada] ");
			}
			return toReturn;
		} catch (Exception e) {
			return "-";
		}
	}

	@Transient
	public String getUnidadeStr() {
		try {
			return getMandato().getNomeUnidade();
		} catch (Exception e) {
			return "-";
		}
	}

	@Transient
	public String getEnteStr() {
		try {
			return getEnte().getNome();
		} catch (Exception e) {
			return "-";
		}
	}

	@Transient
	public String getSituacaoAtualStr() {
		try {
			if (getSituacaoAtual() != null) {
				return getSituacaoAtual().getTipoSituacao().getDescricao();
			} else {
				return "[Nenhuma situação informada]";
			}
		} catch (Exception e) {
			return "-";
		}
	}

	@Transient
	public String getSetorBeneficiadoStr() {
		try {
			if (getSetorBeneficiado() != null) {
				return getSetorBeneficiado().getDescricao();
			} else {
				return "[Setor beneficiado não informado]";
			}
		} catch (Exception e) {
			return "-";
		}
	}

	@Transient
	public String getTipoObraStr() {
		try {
			if (getTipoObra() != null) {
				return getTipoObra().getDescricao();
			} else {
				return "[Tipo de obra não informado]";
			}
		} catch (Exception e) {
			return "-";
		}
	}

	@Transient
	public String getEnderecoStr() {
		try {
			String toReturn = "";
			if (getEndereco() != null) {
				toReturn = toReturn.concat("Endereço: " + getEndereco());
			} else {
				toReturn = toReturn.concat("[Endereço não informado] ");
			}

			if (getBairro() != null) {
				toReturn = toReturn.concat(", Bairro: " + getBairro());
			} else {
				toReturn = toReturn.concat("[Bairro não informado] ");
			}

			if (getCep() != null) {
				toReturn = toReturn.concat(", CEP: " + getCep());
			} else {
				toReturn = toReturn.concat("[CEP não informado]");
			}
			return toReturn;
		} catch (Exception e) {
			return "-";
		}
	}

	@Transient
	public Integer getDiasAlterarExcluir() {
		try {
			LocalDate hoje = LocalDate.now();
			LocalDate diaLimite = LocalDate.fromDateFields(getDataInclusao()).plusDays(30);
			Integer dias = Days.daysBetween(hoje, diaLimite).getDays();
			if (dias <= 0) {
				SolicitacaoFacadeBean solicitacaoFacade;
				InitialContext ctx = new InitialContext();
				solicitacaoFacade = (SolicitacaoFacadeBean) ctx.lookup(SolicitacaoFacadeBean.URI);
				Solicitacao solicitacao = solicitacaoFacade.findSolicitacaoAtendidaByObraServico(this);
				Integer diasSolicitacao = 0;
				if (solicitacao != null) {
					LocalDate diaLimiteSolicitacao = LocalDate.fromDateFields(solicitacao.getDataAtendimento())
							.plusDays(solicitacao.getDiasAutorizados());
					diasSolicitacao = Days.daysBetween(hoje, diaLimiteSolicitacao).getDays();
					if (diasSolicitacao <= 0) {
						return 0;
					}
				}
				return diasSolicitacao;
			}
			return dias;
		} catch (Exception e) {
			return 0;
		}
	}

	@Transient
	public String getBemPublicoStr() {
		try {
			if (getBemPublico() != null) {
				return getBemPublico();
			} else {
				return "[Bem público não informado]";
			}
		} catch (Exception e) {
			return "-";
		}
	}

	@Transient
	public String getValorInicialStr() {
		try {
			if (getValorInicial() != null) {
				return getValorInicial().toString();
			} else {
				return "[Valor inicial não informado]";
			}
		} catch (Exception e) {
			return "-";
		}
	}

	@Transient
	public String getnOrdemServicoStr() {
		try {
			if (getnOrdemServico() != null) {
				return getnOrdemServico().toString();
			} else {
				return "[Nº de ordem de serviço não informado]";
			}
		} catch (Exception e) {
			return "-";
		}
	}

	@Transient
	public String getDescricaoStr() {
		try {
			if (getDescricao() != null) {
				return getDescricao();
			} else {
				return "[Descrição não informada]";
			}
		} catch (Exception e) {
			return "-";
		}
	}

	public Integer getObraServicoId() {
		return obraServicoId;
	}

	public void setObraServicoId(Integer obraServicoId) {
		this.obraServicoId = obraServicoId;
	}

	public String getBemPublico() {
		return bemPublico;
	}

	public void setBemPublico(String bemPublico) {
		this.bemPublico = bemPublico;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Integer getPrazo() {
		return prazo;
	}

	public void setPrazo(Integer prazo) {
		this.prazo = prazo;
	}

	public Integer getnOrdemServico() {
		return nOrdemServico;
	}

	public void setnOrdemServico(Integer nOrdemServico) {
		this.nOrdemServico = nOrdemServico;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Double getValorInicial() {
		return valorInicial;
	}

	public void setValorInicial(Double valorInicial) {
		this.valorInicial = valorInicial;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Integer getContratoId() {
		return contratoId;
	}

	public void setContratoId(Integer contratoId) {
		this.contratoId = contratoId;
	}

	public Integer getMandatoId() {
		return mandatoId;
	}

	public void setMandatoId(Integer mandatoId) {
		this.mandatoId = mandatoId;
	}

	public Integer getEnteId() {
		return enteId;
	}

	public void setEnteId(Integer enteId) {
		this.enteId = enteId;
	}

	public SetorBeneficiado getSetorBeneficiado() {
		return setorBeneficiado;
	}

	public void setSetorBeneficiado(SetorBeneficiado setorBeneficiado) {
		this.setorBeneficiado = setorBeneficiado;
	}

	public TipoExecucao getTipoExecucao() {
		return tipoExecucao;
	}

	public void setTipoExecucao(TipoExecucao tipoExecucao) {
		this.tipoExecucao = tipoExecucao;
	}

	public TipoObra getTipoObra() {
		return tipoObra;
	}

	public void setTipoObra(TipoObra tipoObra) {
		this.tipoObra = tipoObra;
	}

	public TipoServico getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}

	public TipoObraServico getTipoObraServico() {
		return tipoObraServico;
	}

	public void setTipoObraServico(TipoObraServico tipoObraServico) {
		this.tipoObraServico = tipoObraServico;
	}

	public Contrato getContrato() {
		if (this.getContratoId() != null) {
			try {
				ContratoFacadeBean contratoFacade;
				InitialContext ctx = new InitialContext();
				contratoFacade = (ContratoFacadeBean) ctx.lookup(ContratoFacadeBean.JNDI_OBRA_LEGAL);
				contrato = contratoFacade.findByPrimaryKey(this.getContratoId());
			} catch (NamingException e) {
				e.printStackTrace();
				contrato = null;
			}
			return contrato;
		} else {
			return null;
		}
	}

	public void setContrato(Contrato contrato) {
		try {
			if (contrato == null) {
				this.contrato = null;
				this.contratoId = null;
			} else {
				this.contrato = contrato;
				this.contratoId = contrato.getContratoId();
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	public Mandato getMandato() {
		if (this.getMandatoId() != null) {
			try {
				MandatoFacade mandatoFacade;
				InitialContext ctx = new InitialContext();
				mandatoFacade = (MandatoFacade) ctx.lookup(MandatoFacadeBean.JNDI_OBRA_LEGAL);
				mandato = mandatoFacade.findByPrimaryKey(this.getMandatoId());
			} catch (NamingException e) {
				e.printStackTrace();
				mandato = null;
			}
			return mandato;
		} else {
			return null;
		}
	}

	public void setMandato(Mandato mandato) {
		try {
			if (mandato == null) {
				this.mandato = null;
				this.mandatoId = null;
			} else {
				this.mandato = mandato;
				this.mandatoId = mandato.getMandatoId();
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	public Ente getEnte() {
		if (this.getEnteId() != null) {
			try {
				EnteFacade enteFacade;
				InitialContext ctx = new InitialContext();
				enteFacade = (EnteFacade) ctx.lookup(EnteFacadeBean.JNDI_OBRA_LEGAL);
				ente = enteFacade.findByPrimaryKey(this.getEnteId());
			} catch (Exception e) {
				e.printStackTrace();
				ente = null;
			}
			return ente;
		} else {
			return null;
		}
	}

	public void setEnte(Ente ente) {
		try {
			if (ente == null) {
				this.ente = null;
				this.enteId = null;
			} else {
				this.ente = ente;
				this.enteId = ente.getEnteId();
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	public Situacao getSituacaoAtual() {
		try {
			SituacaoFacadeBean situacaoFacade;
			InitialContext ctx = new InitialContext();
			situacaoFacade = (SituacaoFacadeBean) ctx.lookup(SituacaoFacadeBean.URI);
			Situacao s = situacaoFacade.findMaxSituacaoByObraServico(this);
			this.setSituacaoAtual(s);
			return s;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setSituacaoAtual(Situacao ultimaSituacao) {
		this.situacaoAtual = ultimaSituacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((obraServicoId == null) ? 0 : obraServicoId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ObraServico other = (ObraServico) obj;
		if (obraServicoId == null) {
			if (other.obraServicoId != null)
				return false;
		} else if (!obraServicoId.equals(other.obraServicoId))
			return false;
		return true;
	}

}

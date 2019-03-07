package br.gov.ma.tce.obralegal.server.beans.relatorioperiodico;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

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

@Entity(name = RelatorioPeriodico.NAME)
@Table(schema = "obralegal", name = "relatorio_periodico")
public class RelatorioPeriodico implements Serializable {
	public static final String NAME = "obralegal_Relatorio_Periodico";
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "obralegal.seq_relatorio_periodico", sequenceName = "obralegal.seq_relatorio_periodico", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "obralegal.seq_relatorio_periodico")
	@Column(name = "relatorio_periodico_id", columnDefinition="INT4")
	private Integer relatorioPeriodicoId;

	@Temporal(TemporalType.DATE)
	@Column(name = "data")
	private Date data;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_inclusao")
	private Date dataInclusao;

	@Column(name = "cumpriu", columnDefinition = "BOOLEAN")
	private Boolean cumpriu;

	@Column(name = "anotacoes", columnDefinition = "TEXT")
	private String anotacoes;

	@ManyToOne
	@JoinColumn(name = "obra_servico_id", columnDefinition = "INT4")
	private ObraServico obraServico;
	
	@Transient
	public String getDataStr() {
		try {
			DateFormat df = DateFormat.getDateInstance(DateFormat.DEFAULT);
			return df.format(this.getData());
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
	public String getAnotacoesStr() {
		return getAnotacoes() != null ? getAnotacoes() : "Relatório sem anotações."; 
	}

	public Integer getRelatorioPeriodicoId() {
		return relatorioPeriodicoId;
	}

	public void setRelatorioPeriodicoId(Integer relatorioPeriodicoId) {
		this.relatorioPeriodicoId = relatorioPeriodicoId;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Boolean getCumpriu() {
		return cumpriu;
	}

	public void setCumpriu(Boolean cumpriu) {
		this.cumpriu = cumpriu;
	}

	public String getAnotacoes() {
		return anotacoes;
	}

	public void setAnotacoes(String anotacoes) {
		this.anotacoes = anotacoes;
	}

	public ObraServico getObraServico() {
		return obraServico;
	}

	public void setObraServico(ObraServico obraServico) {
		this.obraServico = obraServico;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((relatorioPeriodicoId == null) ? 0 : relatorioPeriodicoId.hashCode());
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
		RelatorioPeriodico other = (RelatorioPeriodico) obj;
		if (relatorioPeriodicoId == null) {
			if (other.relatorioPeriodicoId != null)
				return false;
		} else if (!relatorioPeriodicoId.equals(other.relatorioPeriodicoId))
			return false;
		return true;
	}

}

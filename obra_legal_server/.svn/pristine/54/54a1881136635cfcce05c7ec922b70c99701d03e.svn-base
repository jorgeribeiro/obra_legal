package br.gov.ma.tce.obralegal.server.beans.obraprazo;

import java.io.Serializable;
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

import br.gov.ma.tce.obralegal.server.beans.obraservico.ObraServico;

@Entity(name = ObraPrazo.NAME)
@Table(schema = "obralegal", name = "obra_prazo")
public class ObraPrazo implements Serializable {
	public static final String NAME = "obralegal_Obra_Prazo";
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "obralegal.seq_obra_prazo", sequenceName = "obralegal.seq_obra_prazo", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "obralegal.seq_obra_prazo")
	@Column(name = "obra_prazo_id", columnDefinition = "INT4")
	private Integer obraPrazoId;

	@Temporal(TemporalType.DATE)
	@Column(name = "prazo_aditado")
	private Date prazoAditado;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_fim")
	private Date dataFim;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data")
	private Date data;

	@Column(name = "justificativa", columnDefinition = "TEXT")
	private String justificativa;

	@ManyToOne
	@JoinColumn(name = "obra_servico_id", columnDefinition = "INT4")
	private ObraServico obraServico;

	public Integer getObraPrazoId() {
		return obraPrazoId;
	}

	public void setObraPrazoId(Integer obraPrazoId) {
		this.obraPrazoId = obraPrazoId;
	}

	public Date getPrazoAditado() {
		return prazoAditado;
	}

	public void setPrazoAditado(Date prazoAditado) {
		this.prazoAditado = prazoAditado;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
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
		result = prime * result + ((obraPrazoId == null) ? 0 : obraPrazoId.hashCode());
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
		ObraPrazo other = (ObraPrazo) obj;
		if (obraPrazoId == null) {
			if (other.obraPrazoId != null)
				return false;
		} else if (!obraPrazoId.equals(other.obraPrazoId))
			return false;
		return true;
	}

}

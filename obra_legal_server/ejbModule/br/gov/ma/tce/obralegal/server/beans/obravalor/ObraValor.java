package br.gov.ma.tce.obralegal.server.beans.obravalor;

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

@Entity(name = ObraValor.NAME)
@Table(schema = "obralegal", name = "obra_valor")
public class ObraValor implements Serializable {
	public static final String NAME = "obralegal_Obra_Valor";
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "obralegal.seq_obra_valor", sequenceName = "obralegal.seq_obra_valor", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "obralegal.seq_obra_valor")
	@Column(name = "obra_valor_id", columnDefinition = "INT4")
	private Integer obraValorId;

	@Column(name = "valor_aditado", precision = 18, scale = 2, columnDefinition = "NUMERIC(18,2)")
	private Double valorAditado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data")
	private Date data;

	@Column(name = "justificativa", columnDefinition = "TEXT")
	private String justificativa;

	@ManyToOne
	@JoinColumn(name = "obra_servico_id", columnDefinition = "INT4")
	private ObraServico obraServico;

	public Integer getObraValorId() {
		return obraValorId;
	}

	public void setObraValorId(Integer obraValorId) {
		this.obraValorId = obraValorId;
	}

	public Double getValorAditado() {
		return valorAditado;
	}

	public void setValorAditado(Double valorAditado) {
		this.valorAditado = valorAditado;
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
		result = prime * result + ((obraValorId == null) ? 0 : obraValorId.hashCode());
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
		ObraValor other = (ObraValor) obj;
		if (obraValorId == null) {
			if (other.obraValorId != null)
				return false;
		} else if (!obraValorId.equals(other.obraValorId))
			return false;
		return true;
	}

}
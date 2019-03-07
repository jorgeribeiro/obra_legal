package br.gov.ma.tce.obralegal.server.beans.medicao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.gov.ma.tce.obralegal.server.beans.obraservico.ObraServico;

@Entity(name = Medicao.NAME)
@Table(schema = "obralegal", name = "medicao")
public class Medicao implements Serializable {
	public static final String NAME = "obralegal_medicao";
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "obralegal.seq_medicao", sequenceName = "obralegal.seq_medicao", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "obralegal.seq_medicao")
	@Column(name = "medicao_id", columnDefinition = "INT4")
	private Integer medicaoId;

	// TODO finalizar estrutura da medição

	@ManyToOne
	@JoinColumn(name = "obra_servico_id", columnDefinition = "INT4")
	private ObraServico obraServico;

	public Integer getMedicaoId() {
		return medicaoId;
	}

	public void setMedicaoId(Integer medicaoId) {
		this.medicaoId = medicaoId;
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
		result = prime * result + ((medicaoId == null) ? 0 : medicaoId.hashCode());
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
		Medicao other = (Medicao) obj;
		if (medicaoId == null) {
			if (other.medicaoId != null)
				return false;
		} else if (!medicaoId.equals(other.medicaoId))
			return false;
		return true;
	}
}

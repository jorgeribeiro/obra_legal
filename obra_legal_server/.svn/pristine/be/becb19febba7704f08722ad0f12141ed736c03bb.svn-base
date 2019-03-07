package br.gov.ma.tce.obralegal.server.beans.profissionalobra;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import br.gov.ma.tce.obralegal.server.beans.profissional.Profissional;
import br.gov.ma.tce.obralegal.server.tipoprofissional.TipoProfissional;

@Entity(name = ProfissionalObra.NAME)
@Table(schema = "obralegal", name = "profissional_obra")
public class ProfissionalObra implements Serializable {
	public static final String NAME = "obralegal_Profissional_Obra";
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "obralegal.seq_profissional_obra", sequenceName = "obralegal.seq_profissional_obra", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "obralegal.seq_profissional_obra")
	@Column(name = "profissional_obra_id", columnDefinition = "INT4")
	private Integer profissionalObraId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_inclusao")
	private Date dataInclusao;

	@ManyToOne
	@JoinColumn(name = "profissional_id", columnDefinition = "INT4")
	private Profissional profissional;

	@ManyToOne
	@JoinColumn(name = "obra_servico_id", columnDefinition = "INT4")
	private ObraServico obraServico;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_profissional", columnDefinition = "TEXT")
	private TipoProfissional tipoProfissional;

	@Transient
	public String getNomeProfissional() {
		return profissional.getNome();
	}

	public Integer getProfissionalObraId() {
		return profissionalObraId;
	}

	public void setProfissionalObraId(Integer profissionalObraId) {
		this.profissionalObraId = profissionalObraId;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public ObraServico getObraServico() {
		return obraServico;
	}

	public void setObraServico(ObraServico obraServico) {
		this.obraServico = obraServico;
	}

	public TipoProfissional getTipoProfissional() {
		return tipoProfissional;
	}

	public void setTipoProfissional(TipoProfissional tipoProfissional) {
		this.tipoProfissional = tipoProfissional;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((profissionalObraId == null) ? 0 : profissionalObraId.hashCode());
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
		ProfissionalObra other = (ProfissionalObra) obj;
		if (profissionalObraId == null) {
			if (other.profissionalObraId != null)
				return false;
		} else if (!profissionalObraId.equals(other.profissionalObraId))
			return false;
		return true;
	}

}

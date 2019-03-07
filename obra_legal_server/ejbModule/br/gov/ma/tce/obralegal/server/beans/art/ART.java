package br.gov.ma.tce.obralegal.server.beans.art;

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

@Entity(name = ART.NAME)
@Table(schema = "obralegal", name = "art")
public class ART implements Serializable {
	public static final String NAME = "obralegal_Art";
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "obralegal.seq_art", sequenceName = "obralegal.seq_art", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "obralegal.seq_art")
	@Column(name = "art_id", columnDefinition = "INT4")
	private Integer artId;

	@Column(name = "tipo", columnDefinition = "TEXT")
	private String tipo;

	@ManyToOne
	@JoinColumn(name = "obra_servico_id", columnDefinition = "INT4")
	private ObraServico obraServico;

	public Integer getArtId() {
		return artId;
	}

	public void setArtId(Integer artId) {
		this.artId = artId;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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
		result = prime * result + ((artId == null) ? 0 : artId.hashCode());
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
		ART other = (ART) obj;
		if (artId == null) {
			if (other.artId != null)
				return false;
		} else if (!artId.equals(other.artId))
			return false;
		return true;
	}
}

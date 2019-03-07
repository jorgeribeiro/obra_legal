package br.gov.ma.tce.obralegal.server.beans.localizacao;

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

@Entity(name = Localizacao.NAME)
@Table(schema = "obralegal", name = "localizacao")
public class Localizacao implements Serializable {
	public static final String NAME = "obralegal_Localizacao";
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "obralegal.seq_localizacao", sequenceName = "obralegal.seq_localizacao", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "obralegal.seq_localizacao")
	@Column(name = "localizacao_id", columnDefinition = "INT4")
	private Integer localizacaoId;	

	@Column(name = "latitude", columnDefinition = "FLOAT8")
	private Double latitude;

	@Column(name = "longitude", columnDefinition = "FLOAT8")
	private Double longitude;

	@Column(name = "descricao", columnDefinition = "TEXT")
	private String descricao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_inclusao")
	private Date dataInclusao;

	@ManyToOne
	@JoinColumn(name = "obra_servico_id", columnDefinition = "INT4")
	private ObraServico obraServico;

	public Integer getLocalizacaoId() {
		return localizacaoId;
	}

	public void setLocalizacaoId(Integer localizacaoId) {
		this.localizacaoId = localizacaoId;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
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
		result = prime * result + ((localizacaoId == null) ? 0 : localizacaoId.hashCode());
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
		Localizacao other = (Localizacao) obj;
		if (localizacaoId == null) {
			if (other.localizacaoId != null)
				return false;
		} else if (!localizacaoId.equals(other.localizacaoId))
			return false;
		return true;
	}

}

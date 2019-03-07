package br.gov.ma.tce.obralegal.server.beans.maodeobra;

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

@Entity(name = MaoDeObra.NAME)
@Table(schema = "obralegal", name = "mao_de_obra")
public class MaoDeObra implements Serializable {
	public static final String NAME = "obralegal_Mao_De_Obra";
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "obralegal.seq_mao_de_obra", sequenceName = "obralegal.seq_mao_de_obra", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "obralegal.seq_mao_de_obra")
	@Column(name = "mao_de_obra_id", columnDefinition = "INT4")
	private Integer maoDeObraId;

	@Column(name = "descricao", columnDefinition = "TEXT")
	private String descricao;

	@Column(name = "quantidade", columnDefinition = "INT4")
	private Integer quantidade;

	@ManyToOne
	@JoinColumn(name = "obra_servico_id", columnDefinition = "INT4")
	private ObraServico obraServico;

	public Integer getMaoDeObraId() {
		return maoDeObraId;
	}

	public void setMaoDeObraId(Integer maoDeObraId) {
		this.maoDeObraId = maoDeObraId;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
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
		result = prime * result + ((maoDeObraId == null) ? 0 : maoDeObraId.hashCode());
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
		MaoDeObra other = (MaoDeObra) obj;
		if (maoDeObraId == null) {
			if (other.maoDeObraId != null)
				return false;
		} else if (!maoDeObraId.equals(other.maoDeObraId))
			return false;
		return true;
	}

}

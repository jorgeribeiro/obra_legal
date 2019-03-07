package br.gov.ma.tce.obralegal.server.beans.equipamento;

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

@Entity(name = Equipamento.NAME)
@Table(schema = "obralegal", name = "equipamento")
public class Equipamento implements Serializable {
	public static final String NAME = "obralegal_Equipamento";
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "obralegal.seq_equipamento", sequenceName = "obralegal.seq_equipamento", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "obralegal.seq_equipamento")
	@Column(name = "equipamento_id", columnDefinition = "INT4")
	private Integer equipamentoId;

	@Column(name = "descricao", columnDefinition = "TEXT")
	private String descricao;

	@Column(name = "quantidade", columnDefinition = "INT4")
	private Integer quantidade;

	@ManyToOne
	@JoinColumn(name = "obra_servico_id", columnDefinition = "INT4")
	private ObraServico obraServico;

	public Integer getEquipamentoId() {
		return equipamentoId;
	}

	public void setEquipamentoId(Integer equipamentoId) {
		this.equipamentoId = equipamentoId;
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
		result = prime * result + ((equipamentoId == null) ? 0 : equipamentoId.hashCode());
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
		Equipamento other = (Equipamento) obj;
		if (equipamentoId == null) {
			if (other.equipamentoId != null)
				return false;
		} else if (!equipamentoId.equals(other.equipamentoId))
			return false;
		return true;
	}
}

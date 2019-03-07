package br.gov.ma.tce.obralegal.server.beans.situacao;

import java.io.Serializable;
import java.text.DateFormat;
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
import br.gov.ma.tce.obralegal.server.tiposituacao.TipoSituacao;

@Entity(name = Situacao.NAME)
@Table(schema = "obralegal", name = "situacao")
public class Situacao implements Serializable {
	public static final String NAME = "obralegal_Situacao";
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "obralegal.seq_situacao", sequenceName = "obralegal.seq_situacao", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "obralegal.seq_situacao")
	@Column(name = "situacao_id", columnDefinition = "INT4")
	private Integer situacaoId;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_situacao")
	private Date dataSituacao;

	@Column(name = "veiculo_publicacao", columnDefinition = "TEXT")
	private String veiculoPublicacao;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_publicacao")
	private Date dataPublicacao;

	@Column(name = "descricao", columnDefinition = "TEXT")
	private String descricao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_inclusao")
	private Date dataInclusao;

	@ManyToOne
	@JoinColumn(name = "obra_servico_id", columnDefinition = "INT4")
	private ObraServico obraServico;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_situacao", columnDefinition = "TEXT")
	private TipoSituacao tipoSituacao;
	
	@Transient
	public String getDataSituacaoStr() {
		try {
			DateFormat df = DateFormat.getDateInstance(DateFormat.DEFAULT);
			return df.format(this.getDataSituacao());
		} catch (Exception e) {
			return "-";
		}
	}

	public Integer getSituacaoId() {
		return situacaoId;
	}

	public void setSituacaoId(Integer situacaoId) {
		this.situacaoId = situacaoId;
	}

	public Date getDataSituacao() {
		return dataSituacao;
	}

	public void setDataSituacao(Date dataSituacao) {
		this.dataSituacao = dataSituacao;
	}

	public String getVeiculoPublicacao() {
		return veiculoPublicacao;
	}

	public void setVeiculoPublicacao(String veiculoPublicacao) {
		this.veiculoPublicacao = veiculoPublicacao;
	}

	public Date getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
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

	public TipoSituacao getTipoSituacao() {
		return tipoSituacao;
	}

	public void setTipoSituacao(TipoSituacao tipoSituacao) {
		this.tipoSituacao = tipoSituacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((situacaoId == null) ? 0 : situacaoId.hashCode());
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
		Situacao other = (Situacao) obj;
		if (situacaoId == null) {
			if (other.situacaoId != null)
				return false;
		} else if (!situacaoId.equals(other.situacaoId))
			return false;
		return true;
	}

}

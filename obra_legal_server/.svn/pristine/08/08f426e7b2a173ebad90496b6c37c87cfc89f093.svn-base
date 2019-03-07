package br.gov.ma.tce.obralegal.server.beans.profissional;

import java.io.Serializable;

import javax.naming.InitialContext;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.swing.text.MaskFormatter;

import br.gov.ma.tce.obralegal.server.modalidadeprofissional.ModalidadeProfissional;
import br.gov.ma.tce.pessoarf.server.beans.pessoafisica.PessoaFisica;
import br.gov.ma.tce.pessoarf.server.beans.pessoafisica.PessoaFisicaFacadeBean;

@Entity(name = Profissional.NAME)
@Table(schema = "obralegal", name = "profissional")
public class Profissional implements Serializable {
	public static final String NAME = "obralegal_Profissional";
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "obralegal.seq_profissional", sequenceName = "obralegal.seq_profissional", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "obralegal.seq_profissional")
	@Column(name = "profissional_id", columnDefinition = "INT4")
	private Integer profissionalId;

	@Column(name = "documento_profissional", columnDefinition = "TEXT")
	private String documentoProfissional;

	@Column(name = "titulo", columnDefinition = "TEXT")
	private String titulo;

	@Column(name = "pessoa_cpf", columnDefinition = "TEXT")
	private String pessoaCpf;

	@Enumerated(EnumType.STRING)
	@Column(name = "modalidade_profissional", columnDefinition = "TEXT")
	private ModalidadeProfissional modalidadeProfissional;

	@Transient
	private PessoaFisica pessoa;

	@Transient
	public String getNome() {
		if (getPessoa() != null) {
			return getPessoa().getNome();
		}
		return "";
	}

	@Transient
	public String getCpfStr() {
		String toReturn = "";
		try {
			MaskFormatter cpf = new MaskFormatter("###.###.###-##");
			cpf.setValueContainsLiteralCharacters(false);
			toReturn = cpf.valueToString(getPessoaCpf());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toReturn;
	}

	public Integer getProfissionalId() {
		return profissionalId;
	}

	public void setProfissionalId(Integer profissionalId) {
		this.profissionalId = profissionalId;
	}

	public String getDocumentoProfissional() {
		return documentoProfissional;
	}

	public void setDocumentoProfissional(String documentoProfissional) {
		this.documentoProfissional = documentoProfissional;
	}

	public String getPessoaCpf() {
		return pessoaCpf;
	}

	public void setPessoaCpf(String pessoaCpf) {
		this.pessoaCpf = pessoaCpf;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public ModalidadeProfissional getModalidadeProfissional() {
		return modalidadeProfissional;
	}

	public void setModalidadeProfissional(ModalidadeProfissional modalidadeProfissional) {
		this.modalidadeProfissional = modalidadeProfissional;
	}

	public PessoaFisica getPessoa() {
		if (this.getPessoaCpf() != null) {
			try {
				PessoaFisicaFacadeBean pessoaFisicaFacade;
				InitialContext ctx = new InitialContext();
				pessoaFisicaFacade = (PessoaFisicaFacadeBean) ctx.lookup(
						"java:global/obra_legal_ear/pessoa_rf_server/PessoaFisicaFacadeBean!br.gov.ma.tce.pessoarf.server.beans.pessoafisica.PessoaFisicaFacadeBean");
				pessoa = pessoaFisicaFacade.findByCpf(this.getPessoaCpf());
			} catch (Exception e) {
				e.printStackTrace();
				pessoa = null;
			}
			return pessoa;
		} else {
			return null;
		}
	}

	public void setPessoa(PessoaFisica pessoa) {
		try {
			if (pessoa == null) {
				this.pessoa = null;
				this.pessoaCpf = null;
			} else {
				this.pessoa = pessoa;
				this.pessoaCpf = pessoa.getCpf();
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((profissionalId == null) ? 0 : profissionalId.hashCode());
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
		Profissional other = (Profissional) obj;
		if (profissionalId == null) {
			if (other.profissionalId != null)
				return false;
		} else if (!profissionalId.equals(other.profissionalId))
			return false;
		return true;
	}

}

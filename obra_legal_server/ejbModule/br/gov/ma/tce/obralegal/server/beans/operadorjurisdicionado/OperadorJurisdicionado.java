package br.gov.ma.tce.obralegal.server.beans.operadorjurisdicionado;

import java.io.Serializable;
import java.util.Date;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.swing.text.MaskFormatter;

import br.gov.ma.tce.gestores.server.beans.mandato.Mandato;
import br.gov.ma.tce.gestores.server.beans.mandato.MandatoFacade;
import br.gov.ma.tce.gestores.server.beans.mandato.MandatoFacadeBean;
import br.gov.ma.tce.pessoarf.server.beans.pessoafisica.PessoaFisica;
import br.gov.ma.tce.pessoarf.server.beans.pessoafisica.PessoaFisicaFacadeBean;

@Entity(name = OperadorJurisdicionado.NAME)
@Table(schema = "obralegal", name = "operador_jurisdicionado")
public class OperadorJurisdicionado implements Serializable {
	public static final String NAME = "obralegal_Operador_Jurisdicionado";
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "obralegal.seq_operador_jurisdicionado", sequenceName = "obralegal.seq_operador_jurisdicionado", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "obralegal.seq_operador_jurisdicionado")
	@Column(name = "operador_jurisdicionado_id", columnDefinition = "INT4")
	private Integer operadorJurisdicionadoId;

	@Column(name = "pessoa_cpf", columnDefinition = "TEXT")
	private String pessoaCpf;

	@Column(name = "email", columnDefinition = "TEXT")
	private String email;

	@Column(name = "senha", columnDefinition = "TEXT")
	private String senha;

	@Column(name = "telefone", columnDefinition = "TEXT")
	private String telefone;

	@Column(name = "ativo", columnDefinition = "BOOLEAN")
	private Boolean ativo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_inclusao")
	private Date dataInclusao;

	@Column(name = "mandato_id", columnDefinition = "INT4")
	private Integer mandatoId;

	@Transient
	private PessoaFisica pessoa;

	@Transient
	private Mandato mandato;

	@Transient
	private String repeteSenha;

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

	public Integer getOperadorJurisdicionadoId() {
		return operadorJurisdicionadoId;
	}

	public void setOperadorJurisdicionadoId(Integer operadorJurisdicionadoId) {
		this.operadorJurisdicionadoId = operadorJurisdicionadoId;
	}

	public String getPessoaCpf() {
		return pessoaCpf;
	}

	public void setPessoaCpf(String pessoaCpf) {
		this.pessoaCpf = pessoaCpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Integer getMandatoId() {
		return mandatoId;
	}

	public void setMandatoId(Integer mandatoId) {
		this.mandatoId = mandatoId;
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

	public Mandato getMandato() {
		if (this.getMandatoId() != null) {
			try {
				MandatoFacade mandatoFacade;
				InitialContext ctx = new InitialContext();
				mandatoFacade = (MandatoFacade) ctx.lookup(MandatoFacadeBean.JNDI_OBRA_LEGAL);
				mandato = mandatoFacade.findByPrimaryKey(this.getMandatoId());
			} catch (NamingException e) {
				e.printStackTrace();
				mandato = null;
			}
			return mandato;
		} else {
			return null;
		}
	}

	public void setMandato(Mandato mandato) {
		try {
			if (mandato == null) {
				this.mandato = null;
				this.mandatoId = null;
			} else {
				this.mandato = mandato;
				this.mandatoId = mandato.getMandatoId();
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	public String getRepeteSenha() {
		return repeteSenha;
	}

	public void setRepeteSenha(String repeteSenha) {
		this.repeteSenha = repeteSenha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((operadorJurisdicionadoId == null) ? 0 : operadorJurisdicionadoId.hashCode());
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
		OperadorJurisdicionado other = (OperadorJurisdicionado) obj;
		if (operadorJurisdicionadoId == null) {
			if (other.operadorJurisdicionadoId != null)
				return false;
		} else if (!operadorJurisdicionadoId.equals(other.operadorJurisdicionadoId))
			return false;
		return true;
	}

}

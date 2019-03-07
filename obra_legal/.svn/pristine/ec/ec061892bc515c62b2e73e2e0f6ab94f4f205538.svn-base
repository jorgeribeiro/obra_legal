package br.gov.ma.tce.obralegal.pages;

import java.util.Collection;
import java.util.Date;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.annotation.Scope;
import org.zkoss.bind.annotation.ScopeParam;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

import br.gov.ma.tce.obralegal.server.beans.operadorjurisdicionado.OperadorJurisdicionado;
import br.gov.ma.tce.obralegal.server.beans.operadorjurisdicionado.OperadorJurisdicionadoFacadeBean;
import br.gov.ma.tce.obralegal.server.exception.BusinessException;
import br.gov.ma.tce.obralegal.server.util.HashUtil;
import br.gov.ma.tce.obralegal.util.UsuarioObraLegal;
import br.gov.ma.tce.pessoarf.server.beans.pessoafisica.PessoaFisica;
import br.gov.ma.tce.pessoarf.server.beans.pessoafisica.PessoaFisicaFacadeBean;

public class OperadoresVM {
	private OperadorJurisdicionado operadorJurisdicionado;

	private UsuarioObraLegal usuarioObraLegal;

	private PessoaFisica pessoaFisica;

	private Collection<OperadorJurisdicionado> operadores;

	private OperadorJurisdicionadoFacadeBean operadorJurisdicionadoFacade;
	private PessoaFisicaFacadeBean pessoaFisicaFacade;

	@Wire("#modalCadastrarOperador")
	private Window modalCadastrarOperador;

	@Wire("#modalAtualizarOperador")
	private Window modalAtualizarOperador;

	public OperadoresVM() {
		try {
			InitialContext ctx = new InitialContext();
			operadorJurisdicionadoFacade = (OperadorJurisdicionadoFacadeBean) ctx
					.lookup(OperadorJurisdicionadoFacadeBean.URI);
			pessoaFisicaFacade = (PessoaFisicaFacadeBean) ctx.lookup(
					"java:global/obra_legal_ear/pessoa_rf_server/PessoaFisicaFacadeBean!br.gov.ma.tce.pessoarf.server.beans.pessoafisica.PessoaFisicaFacadeBean");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Init
	public void init(
			@ScopeParam(scopes = Scope.SESSION, value = "usuarioObraLegal") UsuarioObraLegal usuarioObraLegal) {
		// Operadores não tem acesso ao gerenciamento de operadores
		if (usuarioObraLegal.getOperadorJurisdicionado() != null) {
			Executions.sendRedirect("/index.zul");
		} else {
			this.usuarioObraLegal = usuarioObraLegal;
			try {
				operadores = operadorJurisdicionadoFacade
						.findOperadoresByMandato(usuarioObraLegal.getMandato2().getMandatoId());
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@Command
	@NotifyChange(".")
	public void abrirModalCadastrarOperador(@BindingParam("visible") boolean visible) {
		try {
			if (visible) {
				pessoaFisica = null;
				operadorJurisdicionado = new OperadorJurisdicionado();
				operadorJurisdicionado.setMandato(usuarioObraLegal.getMandato2());
			}
			modalCadastrarOperador.setVisible(visible);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	@NotifyChange(".")
	public void validarPessoaFisica() {
		try {
			if (operadorJurisdicionado.getPessoaCpf() == null) {
				throw new BusinessException("Preencha o campo de CPF.");
			} else if ((operadorJurisdicionadoFacade.findByCpfAndMandato(operadorJurisdicionado.getPessoaCpf(),
					usuarioObraLegal.getMandatoId()) != null)) {
				throw new BusinessException(
						"Pessoa física já cadastrada no Módulo Jurisdicionado. Verifique a lista de operadores.");
			}

			pessoaFisica = pessoaFisicaFacade.findByCpf(operadorJurisdicionado.getPessoaCpf());
			if (pessoaFisica == null) {
				throw new BusinessException("CPF não encontrado.");
			}
			operadorJurisdicionado.setPessoa(pessoaFisica);
			Clients.showNotification("Pessoa física encontrada! Preencha as outras informações do operador.",
					Clients.NOTIFICATION_TYPE_INFO, null, null, 3000, true);
		} catch (BusinessException e) {
			Clients.showNotification(e.getMessage(), Clients.NOTIFICATION_TYPE_WARNING, null, null, 3000, true);
		}
	}

	@Command
	@NotifyChange(".")
	public void cadastrarOperador() {
		try {
			if (operadorJurisdicionado.getPessoa() == null) {
				throw new BusinessException("Informe o CPF do operador.");
			} else if (operadorJurisdicionado.getEmail() == null || operadorJurisdicionado.getSenha() == null
					|| operadorJurisdicionado.getRepeteSenha() == null
					|| operadorJurisdicionado.getTelefone() == null) {
				throw new BusinessException("Preencha todos os campos exibidos.");
			} else if (!operadorJurisdicionado.getSenha().equals(operadorJurisdicionado.getRepeteSenha())) {
				throw new BusinessException("As senhas informadas não coincidem.");
			}

			modalCadastrarOperador.setVisible(false);
			operadorJurisdicionado.setSenha(HashUtil.hashSenha(operadorJurisdicionado.getSenha()));
			operadorJurisdicionado.setAtivo(true);
			operadorJurisdicionado.setDataInclusao(new Date());
			operadorJurisdicionadoFacade.include(operadorJurisdicionado);
			operadores.add(operadorJurisdicionado);
			Clients.showNotification("Operador cadastrado com sucesso!", Clients.NOTIFICATION_TYPE_INFO, null, null,
					3000, true);
		} catch (BusinessException e) {
			Clients.showNotification(e.getMessage(), Clients.NOTIFICATION_TYPE_WARNING, null, null, 3000, true);
		}
	}

	@Command
	@NotifyChange(".")
	public void ativarDesativarOperador(@BindingParam("operador") OperadorJurisdicionado operador) {
		try {
			operador.setAtivo(!operador.getAtivo());
			operadorJurisdicionadoFacade.update(operador);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	@NotifyChange(".")
	public void abrirModalAtualizarOperador(@BindingParam("visible") Boolean visible,
			@BindingParam("operador") OperadorJurisdicionado operador) {
		try {
			if (!visible) {
				operadores = operadorJurisdicionadoFacade
						.findOperadoresByMandato(usuarioObraLegal.getMandato2().getMandatoId());
			}

			operadorJurisdicionado = operador;
			modalAtualizarOperador.setVisible(visible);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	@NotifyChange(".")
	public void atualizarOperador() {
		try {
			if (operadorJurisdicionado.getEmail() == null || operadorJurisdicionado.getSenha() == null
					|| operadorJurisdicionado.getRepeteSenha() == null
					|| operadorJurisdicionado.getTelefone() == null) {
				throw new BusinessException("Preencha todos os campos exibidos.");
			} else if (!operadorJurisdicionado.getSenha().equals(operadorJurisdicionado.getRepeteSenha())) {
				throw new BusinessException("As senhas informadas não coincidem.");
			}

			modalAtualizarOperador.setVisible(false);
			operadorJurisdicionadoFacade.update(operadorJurisdicionado);
		} catch (BusinessException e) {
			Clients.showNotification(e.getMessage(), Clients.NOTIFICATION_TYPE_WARNING, null, null, 3000, true);
		}
	}

	public OperadorJurisdicionado getOperadorJurisdicionado() {
		return operadorJurisdicionado;
	}

	public void setOperadorJurisdicionado(OperadorJurisdicionado operador) {
		this.operadorJurisdicionado = operador;
	}

	public UsuarioObraLegal getUsuarioObraLegal() {
		return usuarioObraLegal;
	}

	public void setUsuarioObraLegal(UsuarioObraLegal usuarioObraLegal) {
		this.usuarioObraLegal = usuarioObraLegal;
	}

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public Collection<OperadorJurisdicionado> getOperadores() {
		return operadores;
	}

	public void setOperadores(Collection<OperadorJurisdicionado> operadores) {
		this.operadores = operadores;
	}

}

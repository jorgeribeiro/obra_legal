package br.gov.ma.tce.obralegal.pages;

import java.util.ArrayList;
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
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import br.gov.ma.tce.obralegal.server.beans.localizacao.Localizacao;
import br.gov.ma.tce.obralegal.server.beans.localizacao.LocalizacaoFacadeBean;
import br.gov.ma.tce.obralegal.server.beans.obraservico.ObraServico;
import br.gov.ma.tce.obralegal.server.beans.obraservico.ObraServicoFacadeBean;
import br.gov.ma.tce.obralegal.server.beans.profissional.Profissional;
import br.gov.ma.tce.obralegal.server.beans.profissional.ProfissionalFacadeBean;
import br.gov.ma.tce.obralegal.server.beans.profissionalobra.ProfissionalObra;
import br.gov.ma.tce.obralegal.server.beans.profissionalobra.ProfissionalObraFacadeBean;
import br.gov.ma.tce.obralegal.server.beans.situacao.Situacao;
import br.gov.ma.tce.obralegal.server.beans.situacao.SituacaoFacadeBean;
import br.gov.ma.tce.obralegal.server.exception.BusinessException;
import br.gov.ma.tce.obralegal.server.modalidadeprofissional.ModalidadeProfissional;
import br.gov.ma.tce.obralegal.server.setorbeneficiado.SetorBeneficiado;
import br.gov.ma.tce.obralegal.server.tipoexecucao.TipoExecucao;
import br.gov.ma.tce.obralegal.server.tipoobra.TipoObra;
import br.gov.ma.tce.obralegal.server.tipoobraservico.TipoObraServico;
import br.gov.ma.tce.obralegal.server.tipoprofissional.TipoProfissional;
import br.gov.ma.tce.obralegal.server.tiposituacao.TipoSituacao;
import br.gov.ma.tce.obralegal.util.UsuarioObraLegal;
import br.gov.ma.tce.pessoarf.server.beans.pessoafisica.PessoaFisica;
import br.gov.ma.tce.pessoarf.server.beans.pessoafisica.PessoaFisicaFacadeBean;
import br.gov.ma.tce.sacop.server.beans.contrato.Contrato;
import br.gov.ma.tce.sacop.server.beans.contrato.ContratoFacadeBean;

public class ExecucaoDiretaVM {
	private ObraServico obraServico, obraServicoSelecionado;
	private Localizacao localizacao;
	private Profissional profissional;
	private ProfissionalObra profissionalObra;
	private Situacao situacao;

	private UsuarioObraLegal usuarioObraLegal;
	private Integer anoContrato;

	private PessoaFisica pessoaFisica;

	ListModelList<TipoObra> tiposObra = new ListModelList<TipoObra>(TipoObra.values());
	ListModelList<SetorBeneficiado> setores = new ListModelList<SetorBeneficiado>(SetorBeneficiado.values());
	ListModelList<ModalidadeProfissional> modalidadesProfissional = new ListModelList<ModalidadeProfissional>(
			ModalidadeProfissional.values());
	ListModelList<TipoProfissional> tiposProfissional = new ListModelList<TipoProfissional>(TipoProfissional.values());
	ListModelList<TipoSituacao> tiposSituacao = new ListModelList<TipoSituacao>(TipoSituacao.values());

	Collection<ObraServico> obras;
	Collection<ObraServico> servicos;
	Collection<Localizacao> localizacoes;
	Collection<ProfissionalObra> profissionaisObra;
	Collection<Profissional> profissionais;
	Collection<Situacao> situacoes;
	Collection<Contrato> contratos;

	private ObraServicoFacadeBean obraServicoFacade;
	private LocalizacaoFacadeBean localizacaoFacade;
	private ProfissionalFacadeBean profissionalFacade;
	private ProfissionalObraFacadeBean profissionalObraFacade;
	private SituacaoFacadeBean situacaoFacade;
	private PessoaFisicaFacadeBean pessoaFisicaFacade;
	private ContratoFacadeBean contratoFacade;

	@Wire("#modalNovaObra")
	private Window modalNovaObra;

	@Wire("#modalSelecionaContrato")
	private Window modalSelecionaContrato;

	@Wire("#modalPreencheDadosObra")
	private Window modalPreencheDadosObra;

	@Wire("#modalIncluirLocalizacao")
	private Window modalIncluirLocalizacao;

	@Wire("#modalIncluirProfissional")
	private Window modalIncluirProfissional;

	@Wire("#modalIncluirProfissionalSelecionaTipo")
	private Window modalIncluirProfissionalSelecionaTipo;

	@Wire("#modalCadastrarProfissional")
	private Window modalCadastrarProfissional;

	@Wire("#modalIncluirSituacao")
	private Window modalIncluirSituacao;

	@Wire("#modalAlterarObra")
	private Window modalAlterarObra;

	@Wire("#modalExcluirObraServico")
	private Window modalExcluirObraServico;

	public ExecucaoDiretaVM() {
		try {
			InitialContext ctx = new InitialContext();
			obraServicoFacade = (ObraServicoFacadeBean) ctx.lookup(ObraServicoFacadeBean.URI);
			localizacaoFacade = (LocalizacaoFacadeBean) ctx.lookup(LocalizacaoFacadeBean.URI);
			profissionalFacade = (ProfissionalFacadeBean) ctx.lookup(ProfissionalFacadeBean.URI);
			profissionalObraFacade = (ProfissionalObraFacadeBean) ctx.lookup(ProfissionalObraFacadeBean.URI);
			situacaoFacade = (SituacaoFacadeBean) ctx.lookup(SituacaoFacadeBean.URI);
			pessoaFisicaFacade = (PessoaFisicaFacadeBean) ctx.lookup(PessoaFisicaFacadeBean.JNDI_OBRA_LEGAL);
			contratoFacade = (ContratoFacadeBean) ctx.lookup(ContratoFacadeBean.JNDI_OBRA_LEGAL);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Init
	public void init(
			@ScopeParam(scopes = Scope.SESSION, value = "usuarioObraLegal") UsuarioObraLegal usuarioObraLegal) {
		this.usuarioObraLegal = usuarioObraLegal;
		localizacoes = new ArrayList<Localizacao>();
		profissionaisObra = new ArrayList<ProfissionalObra>();
		situacoes = new ArrayList<Situacao>();
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@Command
	@NotifyChange(".")
	public void carregarObrasPorMandato() {
		try {
			obras = obraServicoFacade.findObrasServicosByTipoExecucaoAndTipoObraServicoAndMandato(
					TipoExecucao.EXECUCAO_DIRETA, TipoObraServico.OBRA, usuarioObraLegal.getMandato2().getMandatoId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	@NotifyChange(".")
	public void abrirModalNovaObra(@BindingParam("visible") boolean visible) {
		try {
			obraServico = new ObraServico();
			modalNovaObra.setVisible(visible);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	@NotifyChange(".")
	public void abrirModalSelecionaContrato(@BindingParam("visible") boolean visible) {
		try {
			modalNovaObra.setVisible(!visible);
			modalSelecionaContrato.setVisible(visible);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	@NotifyChange(".")
	public void carregaContratosPorAno() {
		try {
			contratos = contratoFacade.findContratoByUnidadeAndAno(
					this.usuarioObraLegal.getMandato2().getUnidade().getUnidadeId(), anoContrato);
			if (contratos.isEmpty()) {
				throw new Exception("Não foram encontrados contratos para o ano informado.");
			}
		} catch (Exception e) {
			Clients.showNotification(e.getMessage(), Clients.NOTIFICATION_TYPE_WARNING, null, null, 3000, true);
		}
	}

	@Command
	@NotifyChange(".")
	public void selecionaContrato(@BindingParam("contrato") Contrato contrato) {
		try {
			obraServico.setContrato(contrato);
			modalSelecionaContrato.setVisible(false);
			modalNovaObra.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	@NotifyChange(".")
	public void abrirModalPreencheDadosObra(@BindingParam("visible") boolean visible) {
		try {
			if (visible) {
				if (obraServico.getContrato() == null) {
					throw new BusinessException("Selecione um contrato para continuar.");
				}
				obraServico.setDataInclusao(new Date());
				obraServico.setMandato(usuarioObraLegal.getMandato2());
				obraServico.setEnte(usuarioObraLegal.getMandato2().getUnidade().getOrgao().getEnte());
				obraServico.setTipoExecucao(TipoExecucao.EXECUCAO_DIRETA);
				obraServico.setTipoObraServico(TipoObraServico.OBRA);
				obraServicoFacade.include(obraServico);
				obras.add(obraServico);

				modalNovaObra.setVisible(!visible);
			}
			modalPreencheDadosObra.setVisible(visible);
		} catch (BusinessException e) {
			Clients.showNotification(e.getMessage(), Clients.NOTIFICATION_TYPE_WARNING, null, null, 3000, true);
		} catch (Exception e) {
			Clients.showNotification("Ocorreu um erro inesperado ao iniciar uma nova obra. Tente novamente mais tarde.",
					Clients.NOTIFICATION_TYPE_WARNING, null, null, 3000, true);
		}
	}

	@Command
	@NotifyChange(".")
	public void abrirModalIncluirLocalizacao(@BindingParam("visible") boolean visible) {
		try {
			if (visible) {
				localizacao = new Localizacao();
			}
			modalIncluirLocalizacao.setVisible(visible);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	@NotifyChange(".")
	public void incluirLocalizacao() {
		try {
			if (localizacao.getDescricao() == null || localizacao.getDescricao().equals("")
					|| localizacao.getLatitude() == null || localizacao.getLongitude() == null) {
				throw new BusinessException("Preencha todos os campos solicitados.");
			}
			localizacao.setDataInclusao(new Date());
			localizacao.setObraServico(obraServico);
			localizacaoFacade.include(localizacao);
			localizacoes.add(localizacao);
			modalIncluirLocalizacao.setVisible(false);
			Clients.showNotification("Localização incluída à obra com sucesso!", Clients.NOTIFICATION_TYPE_INFO, null,
					null, 3000, true);
		} catch (BusinessException e) {
			Clients.showNotification(e.getMessage(), Clients.NOTIFICATION_TYPE_WARNING, null, null, 3000, true);
		} catch (Exception e) {
			Clients.showNotification("Ocorreu um erro inesperado ao incluir localização. Tente novamente mais tarde.",
					Clients.NOTIFICATION_TYPE_WARNING, null, null, 3000, true);
		}
	}

	@Command
	@NotifyChange(".")
	public void abrirModalIncluirProfissional(@BindingParam("visible") boolean visible) {
		try {
			if (visible) {
				profissionais = profissionalFacade.findAll();
			}
			modalIncluirProfissional.setVisible(visible);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	@NotifyChange(".")
	public void abrirModalIncluirProfissionalSelecionaTipo(@BindingParam("profissional") Profissional profissional,
			@BindingParam("visible") boolean visible) {
		try {
			if (visible) {
				profissionalObra = new ProfissionalObra();
				profissionalObra.setProfissional(profissional);
			}
			modalIncluirProfissional.setVisible(!visible);
			modalIncluirProfissionalSelecionaTipo.setVisible(visible);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	@NotifyChange(".")
	public void incluirProfissional() {
		try {
			if (profissionalObra.getTipoProfissional() == null) {
				throw new BusinessException("Selecione o tipo de profissional.");
			}
			profissionalObra.setDataInclusao(new Date());
			profissionalObra.setObraServico(obraServico);
			profissionalObraFacade.include(profissionalObra);
			profissionaisObra.add(profissionalObra);
			modalIncluirProfissionalSelecionaTipo.setVisible(false);
			Clients.showNotification("Profissional incluído à obra com sucesso!", Clients.NOTIFICATION_TYPE_INFO, null,
					null, 3000, true);
		} catch (BusinessException e) {
			Clients.showNotification(e.getMessage(), Clients.NOTIFICATION_TYPE_WARNING, null, null, 3000, true);
		} catch (Exception e) {
			Clients.showNotification(
					"Ocorreu um erro inesperado ao incluir o profissional. Tente novamente mais tarde.",
					Clients.NOTIFICATION_TYPE_WARNING, null, null, 3000, true);
		}
	}

	@Command
	@NotifyChange(".")
	public void abrirModalCadastrarProfissional(@BindingParam("visible") boolean visible) {
		try {
			profissional = new Profissional();
			modalCadastrarProfissional.setVisible(visible);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	@NotifyChange(".")
	public void validarPessoaFisica() {
		try {
			if (profissional.getPessoaCpf() == null) {
				throw new BusinessException("Preencha o campo de CPF.");
			} else if (profissionalFacade.findByCpf(profissional.getPessoaCpf()) != null) {
				profissional = new Profissional();
				throw new BusinessException("Profissional já cadastrado.");
			}
			pessoaFisica = pessoaFisicaFacade.findByCpf(profissional.getPessoaCpf());
			if (pessoaFisica != null) {
				profissional.setPessoa(pessoaFisica);
				Clients.showNotification("Pessoa física encontrada! Preencha as outras informações do profissional.",
						Clients.NOTIFICATION_TYPE_INFO, null, null, 3000, true);
			} else {
				profissional = new Profissional();
				throw new BusinessException("CPF não encontrado.");
			}
		} catch (BusinessException e) {
			Clients.showNotification(e.getMessage(), Clients.NOTIFICATION_TYPE_WARNING, null, null, 3000, true);
		}
	}

	@Command
	@NotifyChange(".")
	public void cadastrarProfissional() {
		try {
			if (profissional.getDocumentoProfissional() == null || profissional.getDocumentoProfissional().equals("")
					|| profissional.getModalidadeProfissional() == null || profissional.getTitulo() == null
					|| profissional.getTitulo().equals("")) {
				throw new BusinessException("Preencha todos os campos solicitados.");
			} else {
				profissionalFacade.include(profissional);
				profissionais.add(profissional);
				modalCadastrarProfissional.setVisible(false);
				modalIncluirProfissional.setVisible(true);
				Clients.showNotification("Profissional cadastrado com sucesso!", Clients.NOTIFICATION_TYPE_INFO, null,
						null, 3000, true);
			}
		} catch (BusinessException e) {
			Clients.showNotification(e.getMessage(), Clients.NOTIFICATION_TYPE_WARNING, null, null, 3000, true);
		}
	}

	@Command
	@NotifyChange(".")
	public void abrirModalIncluirSituacao(@BindingParam("visible") boolean visible) {
		try {
			if (visible) {
				situacao = new Situacao();
			}
			modalIncluirSituacao.setVisible(visible);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	@NotifyChange(".")
	public void incluirSituacao() {
		try {
			if (situacao.getTipoSituacao() == null || situacao.getVeiculoPublicacao() == null
					|| situacao.getVeiculoPublicacao().equals("") || situacao.getDataPublicacao() == null
					|| situacao.getDataSituacao() == null || situacao.getDescricao() == null
					|| situacao.getDescricao().equals("")) {
				throw new BusinessException("Preencha todos os campos solicitados.");
			}
			situacao.setDataInclusao(new Date());
			situacao.setObraServico(obraServico);
			situacaoFacade.include(situacao);
			situacoes.add(situacao);
			obraServico.setSituacaoAtual(situacaoFacade.findMaxSituacaoByObraServico(obraServico));
			modalIncluirSituacao.setVisible(false);
			Clients.showNotification("Situação incluída à obra com sucesso!", Clients.NOTIFICATION_TYPE_INFO, null,
					null, 3000, true);
		} catch (BusinessException e) {
			Clients.showNotification(e.getMessage(), Clients.NOTIFICATION_TYPE_WARNING, null, null, 3000, true);
		} catch (Exception e) {
			e.printStackTrace();
			Clients.showNotification("Ocorreu um erro inesperado ao incluir situação. Tente novamente mais tarde.",
					Clients.NOTIFICATION_TYPE_WARNING, null, null, 3000, true);
		}
	}

	@Command
	@NotifyChange(".")
	public void abrirModalAlterarObraServico(@BindingParam("obra") ObraServico obraServico,
			@BindingParam("visible") boolean visible) {
		try {
			if (visible) {
				if (obraServico.getDiasAlterarExcluir() == 0) {
					throw new BusinessException("Período para alteração finalizado.");
				}
				this.obraServico = obraServico;
				localizacoes = localizacaoFacade.findLocalizacoesByObraServico(obraServico);
				profissionaisObra = profissionalObraFacade.findProfissionaisByObraServico(obraServico);
				situacoes = situacaoFacade.findSituacoesByObraServico(obraServico);
			}
			modalAlterarObra.setVisible(visible);
		} catch (BusinessException e) {
			Clients.showNotification(e.getMessage(), Clients.NOTIFICATION_TYPE_WARNING, null, null, 3000, true);
		}
	}

	@Command
	@NotifyChange(".")
	public void salvarAlteracoes() {
		try {
			obraServicoFacade.update(obraServico);
			Clients.showNotification("As alterações foram salvas com sucesso!", Clients.NOTIFICATION_TYPE_INFO, null,
					null, 3000, true);
		} catch (Exception e) {
			Clients.showNotification("Ocorreu um erro inesperado ao salvar as alterações. Tente novamente mais tarde.",
					Clients.NOTIFICATION_TYPE_WARNING, null, null, 3000, true);
		}
	}

	@Command
	@NotifyChange(".")
	public void abrirModalExcluirObra(@BindingParam("obra") ObraServico obraServico,
			@BindingParam("visible") boolean visible) {
		try {
			if (visible) {
				if (obraServico.getDiasAlterarExcluir() == 0) {
					throw new BusinessException("Período para exclusão finalizado.");
				}
				this.obraServico = obraServico;
			}
			modalExcluirObraServico.setVisible(visible);
		} catch (BusinessException e) {
			Clients.showNotification(e.getMessage(), Clients.NOTIFICATION_TYPE_WARNING, null, null, 3000, true);
		}
	}

	@Command
	@NotifyChange(".")
	public void excluirObraServico() {
		try {
			obraServicoFacade.delete(obraServico.getObraServicoId());
			if (obraServico.getTipoObraServico().equals(TipoObraServico.OBRA)) {
				obras.remove(obraServico);
			} else {
				servicos.remove(obraServico);
			}
			modalExcluirObraServico.setVisible(false);
			Clients.showNotification("Obra excluída com sucesso!", Clients.NOTIFICATION_TYPE_INFO, null, null, 3000,
					true);
		} catch (Exception e) {
			Clients.showNotification("Ocorreu um erro inesperado ao excluir essa obra. Tente novamente mais tarde.",
					Clients.NOTIFICATION_TYPE_WARNING, null, null, 3000, true);
		}
	}

	public ObraServico getObraServico() {
		return obraServico;
	}

	public void setObraServico(ObraServico obraServico) {
		this.obraServico = obraServico;
	}

	public ObraServico getObraServicoSelecionado() {
		return obraServicoSelecionado;
	}

	public void setObraServicoSelecionado(ObraServico obraServicoSelecionado) {
		this.obraServicoSelecionado = obraServicoSelecionado;
	}

	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public ProfissionalObra getProfissionalObra() {
		return profissionalObra;
	}

	public void setProfissionalObra(ProfissionalObra profissionalObra) {
		this.profissionalObra = profissionalObra;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public UsuarioObraLegal getUsuarioObraLegal() {
		return usuarioObraLegal;
	}

	public void setUsuarioObraLegal(UsuarioObraLegal usuarioObraLegal) {
		this.usuarioObraLegal = usuarioObraLegal;
	}

	public Integer getAnoContrato() {
		return anoContrato;
	}

	public void setAnoContrato(Integer anoContrato) {
		this.anoContrato = anoContrato;
	}

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public ListModelList<TipoObra> getTiposObra() {
		return tiposObra;
	}

	public void setTiposObra(ListModelList<TipoObra> tiposObra) {
		this.tiposObra = tiposObra;
	}

	public ListModelList<ModalidadeProfissional> getModalidadesProfissional() {
		return modalidadesProfissional;
	}

	public void setModalidadesProfissional(ListModelList<ModalidadeProfissional> modalidadesProfissional) {
		this.modalidadesProfissional = modalidadesProfissional;
	}

	public ListModelList<TipoProfissional> getTiposProfissional() {
		return tiposProfissional;
	}

	public void setTiposProfissional(ListModelList<TipoProfissional> tiposProfissional) {
		this.tiposProfissional = tiposProfissional;
	}

	public ListModelList<TipoSituacao> getTiposSituacao() {
		return tiposSituacao;
	}

	public void setTiposSituacao(ListModelList<TipoSituacao> tiposSituacao) {
		this.tiposSituacao = tiposSituacao;
	}

	public Collection<ObraServico> getObras() {
		return obras;
	}

	public void setObras(Collection<ObraServico> obras) {
		this.obras = obras;
	}

	public Collection<ObraServico> getServicos() {
		return servicos;
	}

	public void setServicos(Collection<ObraServico> servicos) {
		this.servicos = servicos;
	}

	public ListModelList<SetorBeneficiado> getSetores() {
		return setores;
	}

	public void setSetores(ListModelList<SetorBeneficiado> setores) {
		this.setores = setores;
	}

	public Collection<Localizacao> getLocalizacoes() {
		return localizacoes;
	}

	public void setLocalizacoes(Collection<Localizacao> localizacoes) {
		this.localizacoes = localizacoes;
	}

	public Collection<ProfissionalObra> getProfissionaisObra() {
		return profissionaisObra;
	}

	public void setProfissionaisObra(Collection<ProfissionalObra> profissionaisObra) {
		this.profissionaisObra = profissionaisObra;
	}

	public Collection<Profissional> getProfissionais() {
		return profissionais;
	}

	public void setProfissionais(Collection<Profissional> profissionais) {
		this.profissionais = profissionais;
	}

	public Collection<Situacao> getSituacoes() {
		return situacoes;
	}

	public void setSituacoes(Collection<Situacao> situacoes) {
		this.situacoes = situacoes;
	}

	public Collection<Contrato> getContratos() {
		return contratos;
	}

	public void setContratos(Collection<Contrato> contratos) {
		this.contratos = contratos;
	}

}

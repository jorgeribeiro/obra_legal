package br.gov.ma.tce.obralegal.server.beans.localizacao;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.gov.ma.tce.obralegal.server.beans.obraservico.ObraServico;

@Stateless
public class LocalizacaoFacadeBean {
	public static final String URI = "java:global/obra_legal_ear/obra_legal_server/LocalizacaoFacadeBean!br.gov.ma.tce.obralegal.server.beans.localizacao.LocalizacaoFacadeBean";

	@PersistenceContext(unitName = "obra_legal")
	public EntityManager manager;

	public Localizacao include(Localizacao localizacao) {
		manager.persist(localizacao);
		return localizacao;
	}

	public Localizacao update(Localizacao localizacao) {
		manager.merge(localizacao);
		return localizacao;
	}

	public void delete(int localizacaoId) {
		Localizacao localizacao = findByPrimaryKey(localizacaoId);
		manager.remove(localizacao);
	}

	public Localizacao findByPrimaryKey(Integer localizacaoId) {
		Localizacao localizacao = manager.find(Localizacao.class, localizacaoId);
		return localizacao;
	}

	public Collection<Localizacao> findAll() {
		Query q = manager.createQuery("select s from " + Localizacao.NAME + " s  order by s.localizacaoId");
		Collection<Localizacao> localizacoes = new ArrayList<Localizacao>();
		for (Object o : q.getResultList()) {
			localizacoes.add((Localizacao) o);
		}
		return localizacoes;
	}

	public Collection<Localizacao> findLocalizacoesByObraServico(ObraServico obraServico) {
		Query q = manager
				.createQuery(
						"select s from " + Localizacao.NAME + " s where s.obraServico=:arg0 order by s.localizacaoId")
				.setParameter("arg0", obraServico);
		Collection<Localizacao> localizacoes = new ArrayList<Localizacao>();
		for (Object o : q.getResultList()) {
			localizacoes.add((Localizacao) o);
		}
		return localizacoes;
	}
}

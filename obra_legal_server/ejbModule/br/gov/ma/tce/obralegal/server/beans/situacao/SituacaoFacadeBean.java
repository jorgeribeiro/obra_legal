package br.gov.ma.tce.obralegal.server.beans.situacao;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.gov.ma.tce.obralegal.server.beans.obraservico.ObraServico;

@Stateless
public class SituacaoFacadeBean {
	public static final String URI = "java:global/obra_legal_ear/obra_legal_server/SituacaoFacadeBean!br.gov.ma.tce.obralegal.server.beans.situacao.SituacaoFacadeBean";

	@PersistenceContext(unitName = "obra_legal")
	public EntityManager manager;

	public Situacao include(Situacao situacao) {
		manager.persist(situacao);
		return situacao;
	}

	public Situacao update(Situacao situacao) {
		manager.merge(situacao);
		return situacao;
	}

	public void delete(int situacaoId) {
		Situacao situacao = findByPrimaryKey(situacaoId);
		manager.remove(situacao);
	}

	public Situacao findByPrimaryKey(Integer situacaoId) {
		Situacao situacao = manager.find(Situacao.class, situacaoId);
		return situacao;
	}

	public Situacao findMaxSituacaoByObraServico(ObraServico obraServico) {
		Query q = manager
				.createQuery(
						"select s from " + Situacao.NAME + " s where s.dataSituacao=(select max(s.dataSituacao) from "
								+ Situacao.NAME + " s where s.obraServico=:arg0) order by s.dataInclusao")
				.setParameter("arg0", obraServico);
		return q.getResultList().isEmpty() ? null : (Situacao) q.getResultList().get(0);
	}

	public Collection<Situacao> findAll() {
		Query q = manager.createQuery("select s from " + Situacao.NAME + " s  order by s.situacaoId");
		Collection<Situacao> situacoes = new ArrayList<Situacao>();
		for (Object o : q.getResultList()) {
			situacoes.add((Situacao) o);
		}
		return situacoes;
	}

	public Collection<Situacao> findSituacoesByObraServico(ObraServico obraServico) {
		Query q = manager
				.createQuery("select s from " + Situacao.NAME + " s where s.obraServico=:arg0 order by s.dataSituacao")
				.setParameter("arg0", obraServico);
		Collection<Situacao> situacoes = new ArrayList<Situacao>();
		for (Object o : q.getResultList()) {
			situacoes.add((Situacao) o);
		}
		return situacoes;
	}

}

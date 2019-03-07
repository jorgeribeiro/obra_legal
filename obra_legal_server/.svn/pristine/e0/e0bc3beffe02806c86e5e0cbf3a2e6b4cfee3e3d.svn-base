package br.gov.ma.tce.obralegal.server.beans.relatorioperiodico;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.gov.ma.tce.obralegal.server.beans.obraservico.ObraServico;

@Stateless
public class RelatorioPeriodicoFacadeBean {
	public static final String URI = "java:global/obra_legal_ear/obra_legal_server/RelatorioPeriodicoFacadeBean!br.gov.ma.tce.obralegal.server.beans.relatorioperiodico.RelatorioPeriodicoFacadeBean";

	@PersistenceContext(unitName = "obra_legal")
	public EntityManager manager;

	public RelatorioPeriodico include(RelatorioPeriodico relatorioPeriodico) {
		manager.persist(relatorioPeriodico);
		return relatorioPeriodico;
	}

	public RelatorioPeriodico update(RelatorioPeriodico relatorioPeriodico) {
		manager.merge(relatorioPeriodico);
		return relatorioPeriodico;
	}

	public void delete(int relatorioPeriodicoId) {
		RelatorioPeriodico relatorioPeriodico = findByPrimaryKey(relatorioPeriodicoId);
		manager.remove(relatorioPeriodico);
	}

	public RelatorioPeriodico findByPrimaryKey(Integer relatorioPeriodicoId) {
		RelatorioPeriodico relatorioPeriodico = manager.find(RelatorioPeriodico.class, relatorioPeriodicoId);
		return relatorioPeriodico;
	}

	public Collection<RelatorioPeriodico> findAll() {
		Query q = manager
				.createQuery("select s from " + RelatorioPeriodico.NAME + " s  order by s.relatorioPeriodicoId");
		Collection<RelatorioPeriodico> relatorioPeriodicos = new ArrayList<RelatorioPeriodico>();
		for (Object o : q.getResultList()) {
			relatorioPeriodicos.add((RelatorioPeriodico) o);
		}
		return relatorioPeriodicos;
	}

	public Collection<RelatorioPeriodico> findRelatoriosByObraServico(ObraServico obraServico) {
		Query q = manager
				.createQuery(
						"select s from " + RelatorioPeriodico.NAME + " s where s.obraServico=:arg0 order by s.data")
				.setParameter("arg0", obraServico);
		Collection<RelatorioPeriodico> relatorioPeriodicos = new ArrayList<RelatorioPeriodico>();
		for (Object o : q.getResultList()) {
			relatorioPeriodicos.add((RelatorioPeriodico) o);
		}
		return relatorioPeriodicos;
	}
}

package br.gov.ma.tce.obralegal.server.beans.acessolog;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class AcessoLogFacadeBean {
	public static final String URI = "java:global/obra_legal_ear/obra_legal_server/AcessoLogFacadeBean!br.gov.ma.tce.obralegal.server.beans.acessolog.AcessoLogFacadeBean";
	
	@PersistenceContext(unitName = "obra_legal")
	public EntityManager manager;

	public AcessoLog include(AcessoLog acessoLog) {
		manager.persist(acessoLog);
		return acessoLog;
	}

	public AcessoLog update(AcessoLog acessoLog) {
		manager.merge(acessoLog);
		return acessoLog;
	}

	public void delete(int acessoLogId) {
		AcessoLog acessoLog = findByPrimaryKey(acessoLogId);
		manager.remove(acessoLog);
	}

	public AcessoLog findByPrimaryKey(Integer acessoLogId) {
		AcessoLog acessoLog = manager.find(AcessoLog.class, acessoLogId);
		return acessoLog;
	}

	public Collection<AcessoLog> findAll() {
		Query q = manager.createQuery("select s from " + AcessoLog.NAME + " s  order by s.acessoLogId");
		Collection<AcessoLog> acessoLogs = new ArrayList<AcessoLog>();
		for (Object o : q.getResultList()) {
			acessoLogs.add((AcessoLog) o);
		}
		return acessoLogs;
	}

}

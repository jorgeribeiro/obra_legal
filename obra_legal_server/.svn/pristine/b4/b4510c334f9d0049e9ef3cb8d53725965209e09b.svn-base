package br.gov.ma.tce.obralegal.server.beans.pendencia;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class PendenciaFacadeBean {
	public static final String URI = "java:global/obra_legal_ear/obra_legal_server/PendenciaFacadeBean!br.gov.ma.tce.obralegal.server.beans.pendencia.PendenciaFacadeBean";
	
	@PersistenceContext(unitName = "obra_legal")
	public EntityManager manager;

	public Pendencia include(Pendencia pendencia) {
		manager.persist(pendencia);
		return pendencia;
	}

	public Pendencia update(Pendencia pendencia) {
		manager.merge(pendencia);
		return pendencia;
	}

	public void delete(int pendenciaId) {
		Pendencia pendencia = findByPrimaryKey(pendenciaId);
		manager.remove(pendencia);
	}

	public Pendencia findByPrimaryKey(Integer pendenciaId) {
		Pendencia pendencia = manager.find(Pendencia.class, pendenciaId);
		return pendencia;
	}

	public Collection<Pendencia> findAll() {
		Query q = manager
				.createQuery("select s from " + Pendencia.NAME + " s  order by s.pendenciaId");
		Collection<Pendencia> pendencias = new ArrayList<Pendencia>();
		for (Object o : q.getResultList()) {
			pendencias.add((Pendencia) o);
		}
		return pendencias;
	}
}

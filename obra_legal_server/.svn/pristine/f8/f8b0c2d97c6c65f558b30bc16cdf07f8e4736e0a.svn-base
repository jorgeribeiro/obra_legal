package br.gov.ma.tce.obralegal.server.beans.obraprazo;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ObraPrazoFacadeBean {
	public static final String URI = "java:global/obra_legal_ear/obra_legal_server/ObraPrazoFacadeBean!br.gov.ma.tce.obralegal.server.beans.obraprazo.ObraPrazoFacadeBean";
	
	@PersistenceContext(unitName = "obra_legal")
	public EntityManager manager;

	public ObraPrazo include(ObraPrazo obraPrazo) {
		manager.persist(obraPrazo);
		return obraPrazo;
	}

	public ObraPrazo update(ObraPrazo obraPrazo) {
		manager.merge(obraPrazo);
		return obraPrazo;
	}

	public void delete(int obraPrazoId) {
		ObraPrazo obraPrazo = findByPrimaryKey(obraPrazoId);
		manager.remove(obraPrazo);
	}

	public ObraPrazo findByPrimaryKey(Integer obraPrazoId) {
		ObraPrazo obraPrazo = manager.find(ObraPrazo.class, obraPrazoId);
		return obraPrazo;
	}

	public Collection<ObraPrazo> findAll() {
		Query q = manager.createQuery("select s from " + ObraPrazo.NAME + " s  order by s.obraPrazoId");
		Collection<ObraPrazo> obraPrazos = new ArrayList<ObraPrazo>();
		for (Object o : q.getResultList()) {
			obraPrazos.add((ObraPrazo) o);
		}
		return obraPrazos;
	}
}

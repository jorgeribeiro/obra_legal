package br.gov.ma.tce.obralegal.server.beans.art;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ARTFacadeBean {
	public static final String URI = "java:global/obra_legal_ear/obra_legal_server/ARTFacadeBean!br.gov.ma.tce.obralegal.server.beans.art.ARTFacadeBean";
	
	@PersistenceContext(unitName = "obra_legal")
	public EntityManager manager;

	public ART include(ART art) {
		manager.persist(art);
		return art;
	}

	public ART update(ART art) {
		manager.merge(art);
		return art;
	}

	public void delete(int artId) {
		ART art = findByPrimaryKey(artId);
		manager.remove(art);
	}

	public ART findByPrimaryKey(Integer artId) {
		ART art = manager.find(ART.class, artId);
		return art;
	}

	public Collection<ART> findAll() {
		Query q = manager.createQuery("select s from " + ART.NAME + " s  order by s.artId");
		Collection<ART> arts = new ArrayList<ART>();
		for (Object o : q.getResultList()) {
			arts.add((ART) o);
		}
		return arts;
	}
}

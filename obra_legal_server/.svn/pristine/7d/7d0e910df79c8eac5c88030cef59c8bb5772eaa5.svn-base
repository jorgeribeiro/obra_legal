package br.gov.ma.tce.obralegal.server.beans.obravalor;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ObraValorFacadeBean {
	public static final String URI = "java:global/obra_legal_ear/obra_legal_server/ObraValorFacadeBean!br.gov.ma.tce.obralegal.server.beans.obravalor.ObraValorFacadeBean";
	
	@PersistenceContext(unitName = "obra_legal")
	public EntityManager manager;

	public ObraValor include(ObraValor obraValor) {
		manager.persist(obraValor);
		return obraValor;
	}

	public ObraValor update(ObraValor obraValor) {
		manager.merge(obraValor);
		return obraValor;
	}

	public void delete(int obraValorId) {
		ObraValor obraValor = findByPrimaryKey(obraValorId);
		manager.remove(obraValor);
	}

	public ObraValor findByPrimaryKey(Integer obraValorId) {
		ObraValor obraValor = manager.find(ObraValor.class, obraValorId);
		return obraValor;
	}

	public Collection<ObraValor> findAll() {
		Query q = manager.createQuery("select s from " + ObraValor.NAME + " s  order by s.obraValorId");
		Collection<ObraValor> obraValores = new ArrayList<ObraValor>();
		for (Object o : q.getResultList()) {
			obraValores.add((ObraValor) o);
		}
		return obraValores;
	}
}

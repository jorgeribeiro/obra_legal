package br.gov.ma.tce.obralegal.server.beans.medicao;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class MedicaoFacadeBean {
	public static final String URI = "java:global/obra_legal_ear/obra_legal_server/MedicaoFacadeBean!br.gov.ma.tce.obralegal.server.beans.medicao.MedicaoFacadeBean";
	
	@PersistenceContext(unitName = "obra_legal")
	public EntityManager manager;

	public Medicao include(Medicao medicao) {
		manager.persist(medicao);
		return medicao;
	}

	public Medicao update(Medicao medicao) {
		manager.merge(medicao);
		return medicao;
	}

	public void delete(int medicaoId) {
		Medicao medicao = findByPrimaryKey(medicaoId);
		manager.remove(medicao);
	}

	public Medicao findByPrimaryKey(Integer medicaoId) {
		Medicao medicao = manager.find(Medicao.class, medicaoId);
		return medicao;
	}

	public Collection<Medicao> findAll() {
		Query q = manager.createQuery("select s from " + Medicao.NAME + " s  order by s.medicaoId");
		Collection<Medicao> medicaos = new ArrayList<Medicao>();
		for (Object o : q.getResultList()) {
			medicaos.add((Medicao) o);
		}
		return medicaos;
	}
}

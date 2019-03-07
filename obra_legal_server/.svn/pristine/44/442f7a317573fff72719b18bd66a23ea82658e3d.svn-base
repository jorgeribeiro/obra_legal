package br.gov.ma.tce.obralegal.server.beans.profissional;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ProfissionalFacadeBean {
	public static final String URI = "java:global/obra_legal_ear/obra_legal_server/ProfissionalFacadeBean!br.gov.ma.tce.obralegal.server.beans.profissional.ProfissionalFacadeBean";
	
	@PersistenceContext(unitName = "obra_legal")
	public EntityManager manager;

	public Profissional include(Profissional profissional) {
		manager.persist(profissional);
		return profissional;
	}

	public Profissional update(Profissional profissional) {
		manager.merge(profissional);
		return profissional;
	}

	public void delete(int profissionalId) {
		Profissional profissional = findByPrimaryKey(profissionalId);
		manager.remove(profissional);
	}

	public Profissional findByPrimaryKey(Integer profissionalId) {
		Profissional profissional = manager.find(Profissional.class, profissionalId);
		return profissional;
	}

	public Collection<Profissional> findAll() {
		Query q = manager
				.createQuery("select s from " + Profissional.NAME + " s  order by s.profissionalId");
		Collection<Profissional> profissionais = new ArrayList<Profissional>();
		for (Object o : q.getResultList()) {
			profissionais.add((Profissional) o);
		}
		return profissionais;
	}
	
	public Profissional findByCpf(String cpf) {
		Query q = manager.createQuery("select s from " + Profissional.NAME
				+ " s  where s.pessoaCpf=:arg0 order by s.profissionalId").setParameter("arg0", cpf);
		return q.getResultList().isEmpty() ? null : (Profissional) q.getResultList().get(0);
	}
}

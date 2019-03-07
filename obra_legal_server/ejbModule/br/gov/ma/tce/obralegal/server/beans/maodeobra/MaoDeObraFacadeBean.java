package br.gov.ma.tce.obralegal.server.beans.maodeobra;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.gov.ma.tce.obralegal.server.beans.obraservico.ObraServico;

@Stateless
public class MaoDeObraFacadeBean {
	public static final String URI = "java:global/obra_legal_ear/obra_legal_server/MaoDeObraFacadeBean!br.gov.ma.tce.obralegal.server.beans.maodeobra.MaoDeObraFacadeBean";

	@PersistenceContext(unitName = "obra_legal")
	public EntityManager manager;

	public MaoDeObra include(MaoDeObra maoDeObra) {
		manager.persist(maoDeObra);
		return maoDeObra;
	}

	public MaoDeObra update(MaoDeObra maoDeObra) {
		manager.merge(maoDeObra);
		return maoDeObra;
	}

	public void delete(int maoDeObraId) {
		MaoDeObra maoDeObra = findByPrimaryKey(maoDeObraId);
		manager.remove(maoDeObra);
	}

	public MaoDeObra findByPrimaryKey(Integer maoDeObraId) {
		MaoDeObra maoDeObra = manager.find(MaoDeObra.class, maoDeObraId);
		return maoDeObra;
	}

	public Collection<MaoDeObra> findAll() {
		Query q = manager.createQuery("select s from " + MaoDeObra.NAME + " s  order by s.maoDeObraId");
		Collection<MaoDeObra> maoDeObras = new ArrayList<MaoDeObra>();
		for (Object o : q.getResultList()) {
			maoDeObras.add((MaoDeObra) o);
		}
		return maoDeObras;
	}

	public Collection<MaoDeObra> findMaoDeObrasByObraServico(ObraServico obraServico) {
		Query q = manager
				.createQuery("select s from " + MaoDeObra.NAME + " s where s.obraServico=:arg0 order by s.maoDeObraId")
				.setParameter("arg0", obraServico);
		Collection<MaoDeObra> maoDeObras = new ArrayList<MaoDeObra>();
		for (Object o : q.getResultList()) {
			maoDeObras.add((MaoDeObra) o);
		}
		return maoDeObras;
	}
}

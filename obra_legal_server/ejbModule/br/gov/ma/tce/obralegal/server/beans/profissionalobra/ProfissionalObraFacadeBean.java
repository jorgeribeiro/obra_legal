package br.gov.ma.tce.obralegal.server.beans.profissionalobra;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.gov.ma.tce.obralegal.server.beans.obraservico.ObraServico;

@Stateless
public class ProfissionalObraFacadeBean {
	public static final String URI = "java:global/obra_legal_ear/obra_legal_server/ProfissionalObraFacadeBean!br.gov.ma.tce.obralegal.server.beans.profissionalobra.ProfissionalObraFacadeBean";

	@PersistenceContext(unitName = "obra_legal")
	public EntityManager manager;

	public ProfissionalObra include(ProfissionalObra profissionalObra) {
		manager.persist(profissionalObra);
		return profissionalObra;
	}

	public ProfissionalObra update(ProfissionalObra profissionalObra) {
		manager.merge(profissionalObra);
		return profissionalObra;
	}

	public void delete(int profissionalObraId) {
		ProfissionalObra profissionalObra = findByPrimaryKey(profissionalObraId);
		manager.remove(profissionalObra);
	}

	public ProfissionalObra findByPrimaryKey(Integer profissionalObraId) {
		ProfissionalObra profissionalObra = manager.find(ProfissionalObra.class, profissionalObraId);
		return profissionalObra;
	}

	public Collection<ProfissionalObra> findAll() {
		Query q = manager.createQuery("select s from " + ProfissionalObra.NAME + " s order by s.profissionalObraId");
		Collection<ProfissionalObra> profissionalObras = new ArrayList<ProfissionalObra>();
		for (Object o : q.getResultList()) {
			profissionalObras.add((ProfissionalObra) o);
		}
		return profissionalObras;
	}

	public Collection<ProfissionalObra> findProfissionaisByObraServico(ObraServico obraServico) {
		Query q = manager
				.createQuery("select s from " + ProfissionalObra.NAME
						+ " s where s.obraServico=:arg0 order by s.profissionalObraId")
				.setParameter("arg0", obraServico);
		Collection<ProfissionalObra> profissionalObras = new ArrayList<ProfissionalObra>();
		for (Object o : q.getResultList()) {
			profissionalObras.add((ProfissionalObra) o);
		}
		return profissionalObras;
	}
}

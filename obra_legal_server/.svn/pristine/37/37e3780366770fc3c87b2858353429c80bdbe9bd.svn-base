package br.gov.ma.tce.obralegal.server.beans.material;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.gov.ma.tce.obralegal.server.beans.obraservico.ObraServico;

@Stateless
public class MaterialFacadeBean {
	public static final String URI = "java:global/obra_legal_ear/obra_legal_server/MaterialFacadeBean!br.gov.ma.tce.obralegal.server.beans.material.MaterialFacadeBean";

	@PersistenceContext(unitName = "obra_legal")
	public EntityManager manager;

	public Material include(Material material) {
		manager.persist(material);
		return material;
	}

	public Material update(Material material) {
		manager.merge(material);
		return material;
	}

	public void delete(int materialId) {
		Material material = findByPrimaryKey(materialId);
		manager.remove(material);
	}

	public Material findByPrimaryKey(Integer materialId) {
		Material material = manager.find(Material.class, materialId);
		return material;
	}

	public Collection<Material> findAll() {
		Query q = manager.createQuery("select s from " + Material.NAME + " s  order by s.materialId");
		Collection<Material> materiais = new ArrayList<Material>();
		for (Object o : q.getResultList()) {
			materiais.add((Material) o);
		}
		return materiais;
	}

	public Collection<Material> findMateriaisByObraServico(ObraServico obraServico) {
		Query q = manager
				.createQuery("select s from " + Material.NAME + " s where s.obraServico=:arg0 order by s.materialId")
				.setParameter("arg0", obraServico);
		Collection<Material> materiais = new ArrayList<Material>();
		for (Object o : q.getResultList()) {
			materiais.add((Material) o);
		}
		return materiais;
	}
}

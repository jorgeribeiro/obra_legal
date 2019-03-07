package br.gov.ma.tce.obralegal.server.beans.equipamento;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.gov.ma.tce.obralegal.server.beans.obraservico.ObraServico;

@Stateless
public class EquipamentoFacadeBean {
	public static final String URI = "java:global/obra_legal_ear/obra_legal_server/EquipamentoFacadeBean!br.gov.ma.tce.obralegal.server.beans.equipamento.EquipamentoFacadeBean";

	@PersistenceContext(unitName = "obra_legal")
	public EntityManager manager;

	public Equipamento include(Equipamento equipamento) {
		manager.persist(equipamento);
		return equipamento;
	}

	public Equipamento update(Equipamento equipamento) {
		manager.merge(equipamento);
		return equipamento;
	}

	public void delete(int equipamentoId) {
		Equipamento equipamento = findByPrimaryKey(equipamentoId);
		manager.remove(equipamento);
	}

	public Equipamento findByPrimaryKey(Integer equipamentoId) {
		Equipamento equipamento = manager.find(Equipamento.class, equipamentoId);
		return equipamento;
	}

	public Collection<Equipamento> findAll() {
		Query q = manager.createQuery("select s from " + Equipamento.NAME + " s  order by s.equipamentoId");
		Collection<Equipamento> equipamentos = new ArrayList<Equipamento>();
		for (Object o : q.getResultList()) {
			equipamentos.add((Equipamento) o);
		}
		return equipamentos;
	}

	public Collection<Equipamento> findEquipamentosByObraServico(ObraServico obraServico) {
		Query q = manager
				.createQuery("select s from " + Equipamento.NAME + " s where s.obraServico=:arg0 order by s.equipamentoId")
				.setParameter("arg0", obraServico);
		Collection<Equipamento> equipamentos = new ArrayList<Equipamento>();
		for (Object o : q.getResultList()) {
			equipamentos.add((Equipamento) o);
		}
		return equipamentos;
	}
}

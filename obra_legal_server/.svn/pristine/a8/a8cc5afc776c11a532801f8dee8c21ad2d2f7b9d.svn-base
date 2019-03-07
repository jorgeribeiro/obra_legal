package br.gov.ma.tce.obralegal.server.beans.operadorjurisdicionado;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class OperadorJurisdicionadoFacadeBean {
	public static final String URI = "java:global/obra_legal_ear/obra_legal_server/OperadorJurisdicionadoFacadeBean!br.gov.ma.tce.obralegal.server.beans.operadorjurisdicionado.OperadorJurisdicionadoFacadeBean";

	@PersistenceContext(unitName = "obra_legal")
	public EntityManager manager;

	public OperadorJurisdicionado include(OperadorJurisdicionado operadorJurisdicionado) {
		manager.persist(operadorJurisdicionado);
		return operadorJurisdicionado;
	}

	public OperadorJurisdicionado update(OperadorJurisdicionado operadorJurisdicionado) {
		manager.merge(operadorJurisdicionado);
		return operadorJurisdicionado;
	}

	public void delete(int operadorJurisdicionadoId) {
		OperadorJurisdicionado operadorJurisdicionado = findByPrimaryKey(operadorJurisdicionadoId);
		manager.remove(operadorJurisdicionado);
	}

	public OperadorJurisdicionado findByPrimaryKey(Integer operadorJurisdicionadoId) {
		OperadorJurisdicionado operadorJurisdicionado = manager.find(OperadorJurisdicionado.class,
				operadorJurisdicionadoId);
		return operadorJurisdicionado;
	}

	public Collection<OperadorJurisdicionado> findAll() {
		Query q = manager
				.createQuery("select s from " + OperadorJurisdicionado.NAME + " s order by s.operadorJurisdicionadoId");
		Collection<OperadorJurisdicionado> operadores = new ArrayList<OperadorJurisdicionado>();
		for (Object o : q.getResultList()) {
			operadores.add((OperadorJurisdicionado) o);
		}
		return operadores;
	}

	public Collection<OperadorJurisdicionado> findOperadoresByMandato(Integer mandatoId) {
		Query q = manager
				.createQuery("select s from " + OperadorJurisdicionado.NAME
						+ " s where s.mandatoId=:arg0 order by s.operadorJurisdicionadoId")
				.setParameter("arg0", mandatoId);
		Collection<OperadorJurisdicionado> operadores = new ArrayList<OperadorJurisdicionado>();
		for (Object o : q.getResultList()) {
			operadores.add((OperadorJurisdicionado) o);
		}
		return operadores;
	}

	public OperadorJurisdicionado findByCpf(String cpf) {
		Query q = manager.createQuery("select s from " + OperadorJurisdicionado.NAME
				+ " s  where s.pessoaCpf=:arg0 order by s.operadorJurisdicionadoId").setParameter("arg0", cpf);
		return q.getResultList().isEmpty() ? null : (OperadorJurisdicionado) q.getResultList().get(0);
	}

	public OperadorJurisdicionado findByCpfAndMandato(String cpf, Integer mandatoId) {
		Query q = manager
				.createQuery("select s from " + OperadorJurisdicionado.NAME
						+ " s  where s.pessoaCpf=:arg0 and s.mandatoId=:arg1 order by s.operadorJurisdicionadoId")
				.setParameter("arg0", cpf).setParameter("arg1", mandatoId);
		return q.getResultList().isEmpty() ? null : (OperadorJurisdicionado) q.getResultList().get(0);
	}
}

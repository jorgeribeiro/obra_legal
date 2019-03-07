package br.gov.ma.tce.obralegal.server.beans.documento;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.gov.ma.tce.obralegal.server.beans.obraservico.ObraServico;

@Stateless
public class DocumentoFacadeBean {
	public static final String URI = "java:global/obra_legal_ear/obra_legal_server/DocumentoFacadeBean!br.gov.ma.tce.obralegal.server.beans.documento.DocumentoFacadeBean";

	@PersistenceContext(unitName = "obra_legal")
	public EntityManager manager;

	public Documento include(Documento documento) {
		manager.persist(documento);
		return documento;
	}

	public Documento update(Documento documento) {
		manager.merge(documento);
		return documento;
	}

	public void delete(int documentoId) {
		Documento documento = findByPrimaryKey(documentoId);
		manager.remove(documento);
	}

	public Documento findByPrimaryKey(Integer documentoId) {
		Documento documento = manager.find(Documento.class, documentoId);
		return documento;
	}

	public Collection<Documento> findAll() {
		Query q = manager.createQuery("select s from " + Documento.NAME + " s  order by s.documentoId");
		Collection<Documento> documentos = new ArrayList<Documento>();
		for (Object o : q.getResultList()) {
			documentos.add((Documento) o);
		}
		return documentos;
	}

	public Collection<Documento> findDocumentosByObraServico(ObraServico obraServico) {
		Query q = manager
				.createQuery("select s from " + Documento.NAME + " s where s.obraServico=:arg0 order by s.documentoId")
				.setParameter("arg0", obraServico);
		Collection<Documento> documentos = new ArrayList<Documento>();
		for (Object o : q.getResultList()) {
			documentos.add((Documento) o);
		}
		return documentos;
	}
}

package practicaSII.ejb.clasesejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import practicaSII.Grupo;
import practicaSII.ejb.excetption.GrupoEncontradoException;
import practicaSII.ejb.excetption.GrupoNoEncontradoException;

@Stateless
public class GrupoEJB implements GestionGrupos{
	@PersistenceContext(name="Secretaria")
	private EntityManager em;

	@Override
	public void anadirGrupo(Grupo gr) throws GrupoEncontradoException {
		Grupo grEntity = em.find(Grupo.class, gr.getID());
		if (grEntity != null) {
			throw new GrupoEncontradoException();
		}
		em.persist(gr);
	}

	@Override
	public void modificarGrupo(Grupo gr) throws GrupoNoEncontradoException {
		Grupo grEntity = em.find(Grupo.class, gr.getID());
		if (grEntity != null) {
			throw new GrupoNoEncontradoException();
		}
		em.merge(gr);
	}

	@Override
	public void eliminarGrupo(Grupo gr) throws GrupoNoEncontradoException {
		Grupo grEntity = em.find(Grupo.class, gr.getID());
		if (grEntity != null) {
			throw new GrupoNoEncontradoException();
		}
		em.remove(gr);
	}
}

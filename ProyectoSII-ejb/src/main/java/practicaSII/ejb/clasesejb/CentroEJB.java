package practicaSII.ejb.clasesejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import practicaSII.Centro;
import practicaSII.ejb.excetption.CentroEncontradoException;
import practicaSII.ejb.excetption.CentroNoEncontradoException;


@Stateless
public class CentroEJB implements GestionCentro{

	@PersistenceContext(name="Secretaria")
	private EntityManager em;
	
	@Override
	public void anadirCentro(Centro cen) throws CentroEncontradoException {
		// TODO Auto-generated method stub
		
		Centro cenEntity = em.find(Centro.class, cen.getID());
		if (cenEntity != null) {
			throw new CentroEncontradoException();
		}
		em.persist(cen);
	}

	@Override
	public void modificarCentro(Centro cen) throws CentroNoEncontradoException {
		// TODO Auto-generated method stub
		Centro cenEntity = em.find(Centro.class, cen.getID());
		if (cenEntity == null) {
			throw new CentroNoEncontradoException();
		}
		em.merge(cen);
	}

	@Override
	public void eliminarCentro(Centro cen) throws CentroNoEncontradoException {
		// TODO Auto-generated method stub
		
		Centro cenEntity = em.find(Centro.class, cen.getID());
		if (cenEntity == null) {
			throw new CentroNoEncontradoException();
		}
		em.remove(cenEntity);
	}

}

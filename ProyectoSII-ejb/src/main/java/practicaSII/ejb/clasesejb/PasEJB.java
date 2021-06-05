package practicaSII.ejb.clasesejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import practicaSII.Pas;
import practicaSII.ejb.excetption.PasEncontradoException;
import practicaSII.ejb.excetption.PasNoEncontradoException;

@Stateless
public class PasEJB implements GestionPas {

	@PersistenceContext(name="Secretaria")
	private EntityManager em;
	
	@Override
	public void anadirPas(Pas pas) throws PasEncontradoException {
		// TODO Auto-generated method stub
		
		Pas pasEntity = em.find(Pas.class, pas.getID());
		if (pasEntity != null) {
			throw new PasEncontradoException();
		}
		em.persist(pas);
	}


	@Override
	public void eliminarPas(Pas pas) throws PasNoEncontradoException {
		// TODO Auto-generated method stub
		
		Pas pasEntity = em.find(Pas.class, pas.getID());
		if (pasEntity != null) {
			throw new PasNoEncontradoException();
		}
		em.remove(pas);
	}
}
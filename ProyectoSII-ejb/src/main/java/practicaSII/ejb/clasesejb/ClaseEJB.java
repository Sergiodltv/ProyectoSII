package practicaSII.ejb.clasesejb;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import practicaSII.Clase;
import practicaSII.ClasePK;
import practicaSII.ejb.excetption.ClaseEncontradaException;
import practicaSII.ejb.excetption.ClaseNoEncontradaException;

@Stateless
public class ClaseEJB implements GestionClase{

	@PersistenceContext(name="Secretaria")
	private EntityManager em;
	
	@Override
	public void anadirClase(Clase cl) throws ClaseEncontradaException {
		// TODO Auto-generated method stub
		ClasePK pk = new ClasePK();
		pk.setDia(cl.getId().getDia());
		pk.setHoraInicio(cl.getId().getHoraInicio());
		pk.setGrupoFk(cl.getId().getGrupoFk());
		Clase clEntity = em.find(Clase.class, pk);
		if (clEntity != null) {
			throw new ClaseEncontradaException();
		}
		em.persist(cl);
	}

	@Override
	public void eliminarClase(Clase cl) throws ClaseNoEncontradaException {
		// TODO Auto-generated method stub
		
		ClasePK pk = new ClasePK();
		pk.setDia(cl.getId().getDia());
		pk.setHoraInicio(cl.getId().getHoraInicio());
		pk.setGrupoFk(cl.getId().getGrupoFk());
		Clase clEntity = em.find(Clase.class, pk);
		if (clEntity == null) {
			throw new ClaseNoEncontradaException();
		}
		em.merge(cl);
	}

	@Override
	public void modificarClase(Clase cl) throws ClaseNoEncontradaException {
		// TODO Auto-generated method stub
		
		ClasePK pk = new ClasePK();
		pk.setDia(cl.getId().getDia());
		pk.setHoraInicio(cl.getId().getHoraInicio());
		pk.setGrupoFk(cl.getId().getGrupoFk());
		Clase clEntity = em.find(Clase.class, pk);
		if (clEntity == null) {
			throw new ClaseNoEncontradaException();
		}
		em.remove(clEntity);
	}
	
}

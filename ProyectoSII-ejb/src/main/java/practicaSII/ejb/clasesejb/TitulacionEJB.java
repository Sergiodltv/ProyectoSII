package practicaSII.ejb.clasesejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import practicaSII.Titulacion;
import practicaSII.ejb.excetption.TitulacionEncontradaException;
import practicaSII.ejb.excetption.TitulacionNoEncontradaException;

@Stateless
public class TitulacionEJB implements GestionTitulacion{
	@PersistenceContext(name="Secretaria")
	private EntityManager em;

	@Override
	public void anadirTitulacion(Titulacion titul) throws TitulacionEncontradaException {
		Titulacion titulEntity = em.find(Titulacion.class, titul.getCodigo());
		if (titulEntity != null) {
			throw new TitulacionEncontradaException();
		}
		em.persist(titul);
		
	}

	@Override
	public void modificarTitulacion(Titulacion titul) throws TitulacionNoEncontradaException {
		Titulacion titulEntity = em.find(Titulacion.class, titul.getCodigo());
		if (titulEntity != null) {
			throw new TitulacionNoEncontradaException();
		}
		em.merge(titul);
		
	}

	@Override
	public void eliminarTitulacion(Titulacion titul) throws TitulacionNoEncontradaException {
		Titulacion titulEntity = em.find(Titulacion.class, titul.getCodigo());
		if (titulEntity != null) {
			throw new TitulacionNoEncontradaException();
		}
		em.remove(titul);
		
	}
	
	@Override
	public Titulacion obtenerTitulacionConCodigo(Integer codigo) throws TitulacionNoEncontradaException {
		Titulacion titulEntity = em.find(Titulacion.class, codigo);
		if (titulEntity != null) {
			throw new TitulacionNoEncontradaException();
		}
		return titulEntity;
	}
}

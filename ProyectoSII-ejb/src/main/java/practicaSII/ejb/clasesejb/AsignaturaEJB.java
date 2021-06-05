package practicaSII.ejb.clasesejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import practicaSII.Asignatura;
import practicaSII.Optativa;
import practicaSII.ejb.excetption.AsignaturaEncontradaException;
import practicaSII.ejb.excetption.AsignaturaNoEncontradaException;
import practicaSII.ejb.excetption.OptativaNoEncontradaException;

@Stateless
public class AsignaturaEJB implements GestionAsignaturas {

	@PersistenceContext(name="Secretaria")
	private EntityManager em;
	
	@Override
	public void anadirAsingatura(Asignatura asi) throws AsignaturaEncontradaException {
		Asignatura asigEntity = em.find(Asignatura.class, asi.getReferencia());
		if (asigEntity != null) {
			throw new AsignaturaEncontradaException();
		}
		em.persist(asi);
		
	}

	@Override
	public void modificarAsignatura(Asignatura asi) throws AsignaturaNoEncontradaException {
		Asignatura asigEntity = em.find(Asignatura.class, asi.getReferencia());
		if (asigEntity == null) {
			throw new AsignaturaNoEncontradaException();
		}
		em.merge(asi);
	}

	@Override
	public void eliminarAsignatura(Asignatura asi) throws AsignaturaNoEncontradaException {
		Asignatura asigEntity = em.find(Asignatura.class, asi.getReferencia());
		if (asigEntity == null) {
			throw new AsignaturaNoEncontradaException();
		}
		em.remove(asigEntity);
		
	}

	@Override
	public Asignatura obtenerAsignaturaConCodigo(Integer referencia) throws AsignaturaNoEncontradaException {
		Asignatura asigEntity = em.find(Asignatura.class, referencia);
		if (asigEntity == null) {
			throw new AsignaturaNoEncontradaException();
		}
		return asigEntity;
	}
	
	@Override
	public void anyadirOptativa(Optativa opt) throws OptativaNoEncontradaException {
		Optativa optEntity = em.find(Optativa.class, opt.getAsignatura());
		if (optEntity == null) {
			throw new OptativaNoEncontradaException();
		}
	}
}

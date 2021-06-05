package practicaSII.ejb.clasesejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import practicaSII.Alumno;
import practicaSII.Expediente;
import practicaSII.ejb.excetption.AlumnoEncontradoException;
import practicaSII.ejb.excetption.AlumnoNoEncontradoException;
import practicaSII.ejb.excetption.ExpedienteEncontradoException;

@Stateless
public class AlumnosEJB implements GestionAlumnos{
	
	@PersistenceContext(name="Secretaria")
	private EntityManager em;

	@Override
	public void anadirAlumnoMatr(Alumno alm) throws AlumnoEncontradoException {
		// TODO Auto-generated method stub
		
		Alumno alumnoExistente = em.find(Alumno.class, alm.getId());
		if(alumnoExistente != null) {
			throw new AlumnoEncontradoException();
		}
		
		em.persist(alm);
	}

	@Override
	public void modificarAlumno(Alumno alm) throws AlumnoNoEncontradoException {
		// TODO Auto-generated method stub
		
		Alumno alumnoEncontrado = em.find(Alumno.class, alm.getId());
		if(alumnoEncontrado == null) {
			throw new AlumnoNoEncontradoException();
		}
		em.merge(alm);
		
	}

	@Override
	public void eliminarAlumno(Alumno alm) throws AlumnoNoEncontradoException {
		// TODO Auto-generated method stub
		
		Alumno alumnoEncontrado = em.find(Alumno.class, alm.getId());
		if(alumnoEncontrado == null) {
			throw new AlumnoNoEncontradoException();
		}
		
		em.remove(alumnoEncontrado);
		
	}
	
	@Override
	public Alumno obtenerAlumno(Integer id) throws AlumnoNoEncontradoException {
		// TODO Auto-generated method stub
		
		Alumno alumnoEncontrado = em.find(Alumno.class, id);
		if(alumnoEncontrado == null) {
			throw new AlumnoNoEncontradoException();
		}
		
		return alumnoEncontrado;
		
	}

}

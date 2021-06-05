package practicaSII.ejb.clasesejb;

import javax.ejb.Local;

import practicaSII.Asignatura;
import practicaSII.Optativa;
import practicaSII.ejb.excetption.AsignaturaEncontradaException;
import practicaSII.ejb.excetption.AsignaturaNoEncontradaException;
import practicaSII.ejb.excetption.OptativaNoEncontradaException;

@Local
public interface GestionAsignaturas {
     
	public void anadirAsingatura(Asignatura asi) throws AsignaturaEncontradaException;
	
	public void modificarAsignatura(Asignatura asi) throws AsignaturaNoEncontradaException;
	
	public void eliminarAsignatura(Asignatura asi) throws AsignaturaNoEncontradaException;
	
	public Asignatura obtenerAsignaturaConCodigo(Integer referencia) throws AsignaturaNoEncontradaException;
	
	public void anyadirOptativa (Optativa opt) throws OptativaNoEncontradaException;
}


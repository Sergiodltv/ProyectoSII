package practicaSII.ejb.clasesejb;

import javax.ejb.Local;
import practicaSII.Matricula;
import practicaSII.ejb.excetption.MatriculaEncontradaException;
import practicaSII.ejb.excetption.MatriculaNoEncontradaException;


@Local
public interface GestionMatriculas {

	public void anadirMatricula(Matricula mat) throws MatriculaEncontradaException;
	
	public void modificarMatricula(Matricula mat) throws MatriculaNoEncontradaException;
	
	public void borrarMatricula(Matricula mat) throws MatriculaNoEncontradaException;
}

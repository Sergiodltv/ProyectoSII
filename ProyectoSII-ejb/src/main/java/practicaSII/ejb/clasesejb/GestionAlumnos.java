package practicaSII.ejb.clasesejb;

import javax.ejb.Local;
import practicaSII.Alumno;
import practicaSII.Expediente;
import practicaSII.ejb.excetption.AlumnoEncontradoException;
import practicaSII.ejb.excetption.AlumnoNoEncontradoException;
import practicaSII.ejb.excetption.ExpedienteEncontradoException;

@Local
public interface GestionAlumnos {

	public void anadirAlumnoMatr(Alumno alm) throws AlumnoEncontradoException;
	
	public void modificarAlumno(Alumno alm) throws AlumnoNoEncontradoException;
	
	public void eliminarAlumno(Alumno alm) throws AlumnoNoEncontradoException;
	
	public Alumno obtenerAlumno(Integer ID) throws AlumnoNoEncontradoException;
}



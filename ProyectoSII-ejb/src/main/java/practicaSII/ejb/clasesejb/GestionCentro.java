package practicaSII.ejb.clasesejb;

import javax.ejb.Local;

import practicaSII.Centro;
import practicaSII.ejb.excetption.CentroEncontradoException;
import practicaSII.ejb.excetption.CentroNoEncontradoException;

@Local
public interface GestionCentro {

    public void anadirCentro (Centro cen) throws CentroEncontradoException;

    public void modificarCentro (Centro cen) throws CentroNoEncontradoException;

    public void eliminarCentro (Centro cen) throws CentroNoEncontradoException;

}

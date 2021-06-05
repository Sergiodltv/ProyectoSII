package practicaSII.ejb.clasesejb;

import practicaSII.Clase;
import practicaSII.ejb.excetption.ClaseNoEncontradaException;
import practicaSII.ejb.excetption.ClaseEncontradaException;

public interface GestionClase {

    public void anadirClase(Clase cl) throws ClaseEncontradaException;

    public void eliminarClase(Clase cl) throws ClaseNoEncontradaException;

    public void modificarClase(Clase cl) throws ClaseNoEncontradaException;
}
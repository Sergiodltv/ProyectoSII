package practicaSII.ejb.clasesejb;

import javax.ejb.Local;

import practicaSII.Titulacion;
import practicaSII.ejb.excetption.TitulacionEncontradaException;
import practicaSII.ejb.excetption.TitulacionNoEncontradaException;

@Local
public interface GestionTitulacion {

public void anadirTitulacion (Titulacion titul) throws TitulacionEncontradaException;

    public void modificarTitulacion (Titulacion titul) throws TitulacionNoEncontradaException;

    public void eliminarTitulacion (Titulacion titul) throws TitulacionNoEncontradaException;
    
    public Titulacion obtenerTitulacionConCodigo(Integer codigo) throws TitulacionNoEncontradaException;
}
package practicaSII.ejb.clasesejb;

import practicaSII.Pas;
import practicaSII.ejb.excetption.PasEncontradoException;
import practicaSII.ejb.excetption.PasNoEncontradoException;

public interface GestionPas {
	
	public void anadirPas(Pas pas) throws PasEncontradoException;
		
	public void eliminarPas(Pas pas) throws PasNoEncontradoException;
}

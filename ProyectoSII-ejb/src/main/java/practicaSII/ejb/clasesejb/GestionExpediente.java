package practicaSII.ejb.clasesejb;

import javax.ejb.Local;

import practicaSII.Expediente;
import practicaSII.ejb.excetption.ExpedienteEncontradoException;
import practicaSII.ejb.excetption.ExpedienteNoEncontradoException;

@Local
public interface GestionExpediente {
	
	public void anyadirExpediente(Expediente exp) throws ExpedienteEncontradoException;
	
	public void modificarExpediente(Expediente exp) throws ExpedienteNoEncontradoException;
	
	public void eliminarExpediente(Expediente exp) throws ExpedienteNoEncontradoException;
}

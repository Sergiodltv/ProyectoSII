package practicaSII.ejb.clasesejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import practicaSII.Expediente;
import practicaSII.ejb.excetption.ExpedienteEncontradoException;
import practicaSII.ejb.excetption.ExpedienteNoEncontradoException;

@Stateless
public class ExpedienteEJB implements GestionExpediente{

	@PersistenceContext(name="Secretaria")
	private EntityManager em;

	
	@Override
	public void modificarExpediente(Expediente exp) throws ExpedienteNoEncontradoException {
		Expediente expEntity = em.find(Expediente.class, exp.getNumExpediente());
		if (expEntity == null) {
			throw new ExpedienteNoEncontradoException();
		}
		em.merge(exp);
	}

	@Override
	public void eliminarExpediente(Expediente exp) throws ExpedienteNoEncontradoException {
		Expediente expEntity = em.find(Expediente.class, exp.getNumExpediente());
		if (expEntity == null) {
			throw new ExpedienteNoEncontradoException();
		}
		em.remove(expEntity);		
	}

	@Override
	public void anyadirExpediente(Expediente exp) throws ExpedienteEncontradoException {
		Expediente expEntity = em.find(Expediente.class, exp.getNumExpediente());
		if (expEntity != null) {
			throw new ExpedienteEncontradoException();
		}
		em.persist(exp);
	}
	
	
}

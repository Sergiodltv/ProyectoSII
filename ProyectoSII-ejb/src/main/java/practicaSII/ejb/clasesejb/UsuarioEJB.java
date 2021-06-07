package practicaSII.ejb.clasesejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import practicaSII.Titulacion;
import practicaSII.Usuario;
import practicaSII.ejb.excetption.TitulacionEncontradaException;
import practicaSII.ejb.excetption.UsuarioEncontradoException;
import practicaSII.ejb.excetption.UsuarioNoEncontradoException;

@Stateless
public class UsuarioEJB implements GestionUsuario{
	@PersistenceContext(name="Secretaria")
	private EntityManager em;
	
	@Override
	public Usuario getUsuario(String identificador) throws UsuarioNoEncontradoException {
		Usuario usuarioEntity = em.find(Usuario.class, identificador);
		if (usuarioEntity == null) {
			throw new UsuarioNoEncontradoException();
		}
		return usuarioEntity;
		
	}
		
	@Override
	public void crearUsuario(Usuario usuario) throws UsuarioEncontradoException {
		Usuario usuarioEntity = em.find(Usuario.class, usuario.getIdentificador());
		if (usuarioEntity != null) {
			throw new UsuarioEncontradoException();
		}
		em.persist(usuario);
	}	

}

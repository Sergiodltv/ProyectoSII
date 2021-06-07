package practicaSII.ejb.clasesejb;

import practicaSII.Usuario;
import practicaSII.ejb.excetption.UsuarioEncontradoException;
import practicaSII.ejb.excetption.UsuarioNoEncontradoException;

public interface GestionUsuario {
	public Usuario getUsuario(String documento) throws UsuarioNoEncontradoException;
	
	public void crearUsuario(Usuario usuario) throws UsuarioEncontradoException;
}

package es.uma.informatica.sii.proyectosii.jsf;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import practicaSII.ejb.clasesejb.AlumnosEJB;
import practicaSII.ejb.clasesejb.UsuarioEJB;

@Named
@RequestScoped
public class UsuarioBeans{
	
	//@Inject
	//private AlumnosEJB alumnoEJB;
	
	//@Inject 
	//private UsuarioEJB usuarioEJB;
	
	private String ident;
	private String doc;
	private String contrasena;
	
	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
	}
	public String getDoc() {
		return doc;
	}
	public void setDoc(String doc) {
		this.doc = doc;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	public String registrar() {
		return "vistaAlumno.xhtml";
	}
	
	public String acceder() {
		return "vistaAlumno.xhtml";
	}
	
}


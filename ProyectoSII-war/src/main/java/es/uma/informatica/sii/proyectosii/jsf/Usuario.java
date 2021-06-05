package es.uma.informatica.sii.proyectosii.jsf;


import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
@Stateful(passivationCapable=true)

public class Usuario {

	private String identificador; 
	private String contrasena;
	private String expediente;
	
	public String acceder() {
		return "vistaAlumno.xhtml" ;
	}
	
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public String getExpediente() {
		return expediente;
	}
	public void setExpediente(String expediente) {
		this.expediente = expediente;
	}
	
	
}

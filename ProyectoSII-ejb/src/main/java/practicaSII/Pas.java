package practicaSII;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: PAS
 *
 */
@Entity

public class Pas implements Serializable {

	   
	@Id
	private String ID;
	@Column (name = "dni", nullable = false, unique = true)
	private String DNI;
	@Column (name = "contrasena", nullable = false)
	private String Contrasenya;
	@Column (name = "nombre", nullable = false)
	private String Nombre;
	@Column (name = "apellido_1", nullable = false)
	private String Apellido1;
	private String Apellido2;
	@Column (name = "email_institucional", nullable = false)
	private String EmailInstitucional;
	@Column (name = "telefono_contacto", nullable = false)
	private Integer TelefonoContacto;
	private String Direccion;
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Centro cr_pas;
	
	public Pas() {
		super();
	}   
	public String getID() {
		return this.ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}   
	public String getDNI() {
		return this.DNI;
	}

	public void setDNI(String DNI) {
		this.DNI = DNI;
	}   
	public String getContrasenya() {
		return this.Contrasenya;
	}

	public void setContrasenya(String Contrasenya) {
		this.Contrasenya = Contrasenya;
	}   
	public String getNombre() {
		return this.Nombre;
	}

	public void setNombre(String Nombre) {
		this.Nombre = Nombre;
	}   
	public String getApellido1() {
		return this.Apellido1;
	}

	public void setApellido1(String Apellido1) {
		this.Apellido1 = Apellido1;
	}   
	public String getApellido2() {
		return this.Apellido2;
	}

	public void setApellido2(String Apellido2) {
		this.Apellido2 = Apellido2;
	}   
	public String getEmailInstitucional() {
		return this.EmailInstitucional;
	}

	public void setEmailInstitucional(String EmailInstitucional) {
		this.EmailInstitucional = EmailInstitucional;
	}   
	public Integer getTelefonoContacto() {
		return this.TelefonoContacto;
	}

	public void setTelefonoContacto(Integer TelefonoContacto) {
		this.TelefonoContacto = TelefonoContacto;
	}   
	public String getDireccion() {
		return this.Direccion;
	}

	public void setDireccion(String Direccion) {
		this.Direccion = Direccion;
	}

	@Override
	public boolean equals(Object obj) {
		boolean esPas = obj instanceof Pas;
		Pas pas = (Pas)obj;
		return esPas && pas.getID().equals(ID);
	}
   
}

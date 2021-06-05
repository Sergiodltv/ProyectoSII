package practicaSII;

import java.io.Serializable; 
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Alumno
 *
 */
@Entity

public class Alumno implements Serializable {


	@Id @GeneratedValue
	private Integer id;
	@Column (name = "dni", nullable = false, unique = true)
	private String dni;
	@Column (name = "contrasenya", nullable = false)
	private String contrasenya;
	@Column (name = "nombre", nullable = false)
	private String nombre;
	@Column (name = "apellido_1", nullable = false)
	private String apellido1;
	private String apellido2;
	@Column (name = "email_institucional", nullable = false)
	private String emailInstitucional;
	private String emailPersonal;
	private Integer telefono;
	private Integer movil;
	private String direccion;
	private String localidad;
	private String provincia;
	private Integer codigoPostal;
	private static final long serialVersionUID = 1L;
	
	@OneToMany (mappedBy = "al_exp")
	private List<Expediente> expAlum;

	public Alumno() {
		super();
	}
	
	public Alumno(String dni,String nombre, String apellido1, String apellido2,
			String emailInstitucional, String emailPersonal, Integer telefono, Integer movil, String direccion,
			String localidad, String provincia, Integer codigoPostal) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.emailInstitucional = emailInstitucional;
		this.emailPersonal = emailPersonal;
		this.telefono = telefono;
		this.movil = movil;
		this.direccion = direccion;
		this.localidad = localidad;
		this.provincia = provincia;
		this.codigoPostal = codigoPostal;
	}



	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}   
	public String getContrasenya() {
		return this.contrasenya;
	}

	public void setContrasenya(String Contrasenya) {
		this.contrasenya = Contrasenya;
	}   
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String Nombre) {
		this.nombre = Nombre;
	}   
	public String getApellido1() {
		return this.apellido1;
	}

	public void setApellido1(String Apellido1) {
		this.apellido1 = Apellido1;
	}   
	public String getApellido2() {
		return this.apellido2;
	}

	public void setApellido2(String Apellido2) {
		this.apellido2 = Apellido2;
	}   
	public String getEmailInstitucional() {
		return this.emailInstitucional;
	}

	public void setEmailInstitucional(String EmailInstitucional) {
		this.emailInstitucional = EmailInstitucional;
	}   
	public String getEmailPersonal() {
		return this.emailPersonal;
	}

	public void setEmailPersonal(String EmailPersonal) {
		this.emailPersonal = EmailPersonal;
	}   
	public Integer getTelefono() {
		return this.telefono;
	}

	public void setTelefono(Integer Telefono) {
		this.telefono = Telefono;
	}   
	public Integer getMovil() {
		return this.movil;
	}

	public void setMovil(Integer Movil) {
		this.movil = Movil;
	}   
	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String Direccion) {
		this.direccion = Direccion;
	}
	
	public void anyadirExpediente(Expediente exp) {
		expAlum.add(exp);
	}
	
	public boolean equals(Object obj) {
		boolean esAlumno = obj instanceof Alumno;
		Alumno alum = (Alumno)obj;
		return esAlumno && this.id.equals(alum.getId());
	}
   
}

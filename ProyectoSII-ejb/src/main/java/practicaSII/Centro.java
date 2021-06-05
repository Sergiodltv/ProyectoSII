package practicaSII;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Centro
 *
 */
@Entity

public class Centro implements Serializable {

	
	@Id
	private String ID;
	@Column (name = "nombre", nullable = false, unique = true)
	private String Nombre;
	@Column (name = "direccion", nullable = false)
	private String Direccion;
	private Integer TelefonoConserjeria;
	private static final long serialVersionUID = 1L;

	@ManyToMany
	private List<Titulacion> c_titul;
	
	@OneToMany (mappedBy = "cr_pas")
	private List<Pas> idPas;
	
	public Centro() {
		super();
	}   
	public String getID() {
		return this.ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}   
	public String getNombre() {
		return this.Nombre;
	}

	public void setNombre(String Nombre) {
		this.Nombre = Nombre;
	}   
	public String getDireccion() {
		return this.Direccion;
	}

	public void setDireccion(String Direccion) {
		this.Direccion = Direccion;
	}   
	public Integer getTelefonoConsergeria() {
		return this.TelefonoConserjeria;
	}

	public void setTelefonoConsergeria(Integer TelefonoConsergeria) {
		this.TelefonoConserjeria = TelefonoConsergeria;
	}
   
	@Override
	public boolean equals(Object obj) {
		boolean esCentro = obj instanceof Centro;
		Centro centro = (Centro)obj;
		return esCentro && centro.getID().equals(ID);
	}
}

package practicaSII;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Titulacion
 *
 */
@Entity
//@Table(name="e_r")

public class Titulacion implements Serializable {

	   
	@Id @GeneratedValue
	private Integer codigo;
	private String nombre;
	private Integer creditos;
	private static final long serialVersionUID = 1L;
	
	@OneToMany (mappedBy = "as_titul")
	private List<Asignatura> referencias;
	
	@OneToMany (mappedBy = "g_titul")
	private List<Grupo> idGrupos;
	
	
	@ManyToMany (mappedBy = "c_titul")
	private List<Centro> idCentros;


	public Titulacion() {
		super();
	}
	
	public Titulacion(Integer codigo, String nombre, Integer creditos ) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.creditos = creditos;
	}
	
	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer Codigo) {
		this.codigo = Codigo;
	}   
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String Nombre) {
		this.nombre = Nombre;
	}   
	public Integer getCreditos() {
		return this.creditos;
	}

	public void setCreditos(Integer Creditos) {
		this.creditos = Creditos;
	}
   	
	@Override
	public boolean equals(Object obj) {
		boolean esTitu = obj instanceof Titulacion;
		Titulacion titu = (Titulacion)obj;
		return esTitu && titu.getCodigo().equals(codigo);
	}
}

package practicaSII;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.sql.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Matricula
 *
 */
@Entity

public class Matricula implements Serializable {

	   
	@EmbeddedId
	private MatriculaPK id;
	@Column (name = "estado", nullable = false)
	private Boolean estado;
	@Column (name = "fecha_matricula", nullable = false) 
	private String fechaMatricula;
	private Integer numArchivo;
	private String turnoPreferente;
	private Boolean nuevoIngreso;
	private String gruposAsignados;
	private static final long serialVersionUID = 1L;
	
	@MapsId("exp_fk")
	@ManyToOne
	private Expediente mt_exp;

	public Matricula() {
		super();
	}   
	
	
	
	public Matricula(MatriculaPK id, Boolean estado, String fechaMatricula, Integer numArchivo, String turnoPreferente, 
			Expediente mt_exp, String gruposAsignados) {
		super();
		this.id = id;
		this.estado = estado;
		this.fechaMatricula = fechaMatricula;
		this.numArchivo = numArchivo;
		this.turnoPreferente = turnoPreferente;
		this.mt_exp = mt_exp;
		this.gruposAsignados = gruposAsignados;
	}



	@OneToMany(mappedBy ="asigmt_mt")
	private List<Asig_Matricula> mt_asigmt;
	 
	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}   
	public String getFechaMatricula() {
		return this.fechaMatricula;
	}

	public void setFechaMatricula(String fechaMatricula) {
		this.fechaMatricula = fechaMatricula;
	}   
	public Integer getNumArchivo() {
		return this.numArchivo;
	}

	public void setNumArchivo(Integer numArchivo) {
		this.numArchivo = numArchivo;
	}   
	public String getTurnoPreferido() {
		return this.turnoPreferente;
	}

	public void setTurnoPreferido(String turnoPreferido) {
		this.turnoPreferente = turnoPreferido;
	}   
	public Boolean getNuevoIngreso() {
		return this.nuevoIngreso;
	}

	public void setNuevoIngreso(Boolean nuevoIngreso) {
		this.nuevoIngreso = nuevoIngreso;
	}   
   
	public MatriculaPK getId() {
		return id;
	}
	
	public void setId(MatriculaPK mpk) {
		id = mpk;
	}

	@Override
	public boolean equals(Object obj) {
		boolean esMat = obj instanceof Matricula;
		Matricula mat = (Matricula)obj;
		return esMat && mat.getId().equals(id);
	}
}

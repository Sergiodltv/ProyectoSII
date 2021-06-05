package practicaSII;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Optativa
 *
 */
@Entity

public class Optativa implements Serializable {

	@Column (name = "plazas", nullable = false)
	private Integer plazas;
	private String mencion;
	private static final long serialVersionUID = 1L;
	
	@Id
	@OneToOne
	private Asignatura opt_asig;

	public Optativa() {
		super();
	}
	
	public Optativa(Asignatura asignatura,Integer plazas,String mencion) {
		super();
		this.opt_asig = asignatura;
		this.plazas = plazas;
		this.mencion = mencion; 
	}
	
	public Integer getPlazas() {
		return this.plazas;
	}

	public void setPlazas(Integer Plazas) {
		this.plazas = Plazas;
	}   
	public String getMencion() {
		return this.mencion;
	}

	public void setMencion(String Mencion) {
		this.mencion = Mencion;
	}
	public Asignatura getOpt_asig() {
		return opt_asig;
	}
	public void setOpt_asig(Asignatura opt_asig) {
		this.opt_asig = opt_asig;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Asignatura getAsignatura() {
		return opt_asig;
	}
	public void setAsignatura(Asignatura asig) {
		this.opt_asig = asig;
	}
}

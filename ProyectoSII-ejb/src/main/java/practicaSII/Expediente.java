package practicaSII;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Float;
import java.lang.Integer;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Expediente
 *
 */
@Entity

public class Expediente implements Serializable {

	   
	@Id
	private Integer numExpediente;
	private Boolean activo;
	private String notaMediaProvisional;
	private Integer crSuperados;
	private Integer crFb;
	private Integer crOb;
	private Integer crOp;
	private Integer crCf;
	private Integer crPe;
	private Integer crTf;
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Alumno al_exp;
	
	@OneToMany (mappedBy = "enc_exp")
	private List<Encuesta> fechaEnc;
	
	@OneToMany (mappedBy = "mt_exp")
	private List<Matricula> exp_mt;

	public Expediente() {
		super();
	}
	
	public Expediente(Integer numExpediente, String notaMediaProvisional, Integer crSuperados, Integer crFb, 
			Integer crOb, Integer crOp, Integer crCf, Integer crPe,Integer crTf) {
		super();
		this.numExpediente = numExpediente;
		this.notaMediaProvisional = notaMediaProvisional;
		this.crSuperados = crSuperados;
		this.crFb = crFb;
		this.crOb = crOb;
		this.crOp = crOp;
		this.crCf = crCf;
		this.crPe = crPe;
		this.crTf = crTf;
	}
	
	public Integer getNumExpediente() {
		return this.numExpediente;
	}

	public void setNumExpediente(Integer numExpediente) {
		this.numExpediente = numExpediente;
	}   
	public Boolean getActivo() {
		return this.activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}   
	public String getNotaMediaProvisional() {
		return this.notaMediaProvisional;
	}

	public void setNotaMediaProvisional(String notaMediaProvisional) {
		this.notaMediaProvisional = notaMediaProvisional;
	}
   
	public Integer getCrSuperados() {
		return crSuperados;
	}

	public void setCrSuperados(Integer crSuperados) {
		this.crSuperados = crSuperados;
	}

	public Integer getCrFb() {
		return crFb;
	}

	public void setCrFb(Integer crFb) {
		this.crFb = crFb;
	}

	public Integer getCrOb() {
		return crOb;
	}

	public void setCrOb(Integer crOb) {
		this.crOb = crOb;
	}

	public Integer getCrOp() {
		return crOp;
	}

	public void setCrOp(Integer crOp) {
		this.crOp = crOp;
	}

	public Integer getCrPe() {
		return crPe;
	}

	public void setCrPe(Integer crPe) {
		this.crPe = crPe;
	}

	public Integer getCrTf() {
		return crTf;
	}

	public void setCrTf(Integer crTf) {
		this.crTf = crTf;
	}

	public Alumno getAlumno() {
		return al_exp;
	}

	public void setAlumno(Alumno alumno) {
		this.al_exp = alumno;
	}
	
	public void anyadirMatricula(Matricula mat){
		exp_mt.add(mat);
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean esExpediente = obj instanceof Expediente;
		Expediente exp = (Expediente)obj;
		return esExpediente && exp.getNumExpediente().equals(numExpediente);
	}
}

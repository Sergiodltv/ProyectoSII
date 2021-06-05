package practicaSII;

import java.io.Serializable; 
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Asignatura
 *
 */
@Entity

public class Asignatura implements Serializable {

	@Id
	private Integer Referencia;
	@Column(name = "codigo", nullable = false)
	private Integer Codigo;
	@Column (name = "totalcreditos", nullable = false)
	private Integer TotalCreditos;
	@Column (name = "creditospr", nullable = false)
	private Integer CreditosPr;
	@Column (name = "creditosteo", nullable = false)
	private Integer CreditosTeo;
	@Column (name = "ofertada", nullable = false)
	private String Ofertada;
	@Column (name = "nombre", nullable = false)
	private String Nombre;
	private Integer Curso;
	private String Plazas;
	private String Duracion;
	private String Ingles;
	private static final long serialVersionUID = 1L;
	
	public Asignatura(Integer referencia, Integer codigo, Integer tc, Integer cpr,
			Integer cteo, String ofertada, String nombre, Integer curso, String plazas,
			String duracion, String ingles) {
		super();
		this.Referencia = referencia;
		this.Codigo = codigo;
		this.TotalCreditos = tc;
		this.CreditosPr = cpr;
		this.CreditosTeo = cteo;
		this.Ofertada = ofertada;
		this.Nombre = nombre;
		this.Curso = curso;
		this.Plazas = plazas;
		this.Duracion = duracion;
		this.Ingles = ingles;
	}
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Titulacion as_titul;
	
	@OneToMany(mappedBy = "asig_cl")
	private List<Clase> clases;

	@OneToOne (mappedBy="opt_asig")
	private Optativa asig_opt;
	
	@OneToMany (mappedBy = "asigmt_asig")
	private List<Asig_Matricula> asig_asigmt;
	
	@OneToMany (mappedBy = "asi")
	private List<Grupos_Por_Asignatura> gpa;
	
	public Asignatura() {
		super();
	}   
	public Integer getReferencia() {
		return this.Referencia;
	}

	public void setReferencia(Integer Referencia) {
		this.Referencia = Referencia;
	}   
	public Integer getCodigo() {
		return this.Codigo;
	}

	public void setCodigo(Integer Codigo) {
		this.Codigo = Codigo;
	}   
	public Integer getCreditosTotales() {
		return this.TotalCreditos;
	}

	public void setCreditosTotales(Integer Creditos) {
		this.TotalCreditos = Creditos;
	}
	public Integer getCreditosPr() {
		return CreditosPr;
	}
	public void setCreditosPr(Integer creditosPr) {
		CreditosPr = creditosPr;
	}
	public Integer getCreditosTeo() {
		return CreditosTeo;
	}
	public void setCreditosTeo(Integer creditosTeo) {
		CreditosTeo = creditosTeo;
	}
	public String getOfertada() {
		return this.Ofertada;
	}

	public void setOfertada(String Ofertada) {
		this.Ofertada = Ofertada;
	}   
	public String getNombre() {
		return this.Nombre;
	}

	public void setNombre(String Nombre) {
		this.Nombre = Nombre;
	}   
	public Integer getCurso() {
		return this.Curso;
	}

	public void setCurso(Integer Curso) {
		this.Curso = Curso;
	}   
	public String getPlazas() {
		return this.Plazas;
	}

	public void setPlazas(String Caracter) {
		this.Plazas = Caracter;
	}   
	public String getDuracion() {
		return this.Duracion;
	}

	public void setDuracion(String Duracion) {
		this.Duracion = Duracion;
	}   
	  
	public String getIngles() {
		return this.Ingles;
	}

	public void setIngles(String Ingles) {
		this.Ingles = Ingles;
	}
	
	public Titulacion getAs_titul() {
		return as_titul;
	}
	public void setAs_titul(Titulacion as_titul) {
		this.as_titul = as_titul;
	}
	public List<Clase> getClases() {
		return clases;
	}
	public void setClases(List<Clase> clases) {
		this.clases = clases;
	}
	public Optativa getAsig_opt() {
		return asig_opt;
	}
	public void setAsig_opt(Optativa asig_opt) {
		this.asig_opt = asig_opt;
	}
	
	public Titulacion getTitulacion() {
		return as_titul;
	}
	
	public void setTitulacion(Titulacion t) {
		as_titul = t;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
   
}

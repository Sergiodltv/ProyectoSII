package practicaSII;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Character;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Grupo
 *
 */
@Entity

public class Grupo implements Serializable {

	   
	@Id @GeneratedValue
	private Integer ID;
	@Column (name = "curso", nullable = false, unique = true)
	private String Curso;
	@Column (name = "letra", nullable = false, unique = true)
	private Character Letra;
	@Column (name = "turno_manyana_tarde", nullable = false, unique = true)
	private String TurnoManyanaTarde;
	@Column (name = "ingles", nullable = false)
	private Boolean Ingles;
	private Boolean Visible;
	private String Asignar;
	private Integer Plazas;
	@Column (name = "contrasenya", nullable = false)
	private String Contrasenya;
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Titulacion g_titul;
	
	@OneToMany (mappedBy = "gr_asig")
	private List<Asig_Matricula> asiGr;
	
	@ManyToOne
	private Grupo grupo_gr;
	
	@OneToMany(mappedBy = "grupo_gr")
	private List<Grupo> gr_grupo;
	
	@OneToMany (mappedBy = "gr_cl")
	private List<Clase> clases;

	@OneToMany (mappedBy = "grup")
	private List<Grupos_Por_Asignatura> gpa;
	
	public Grupo() {
		super();
	}   
	public Integer getID() {
		return this.ID;
	}

	public void setID(Integer ID) {
		this.ID = ID;
	}   
	public String getCurso() {
		return this.Curso;
	}

	public void setCurso(String Curso) {
		this.Curso = Curso;
	}   
	public Character getLetra() {
		return this.Letra;
	}

	public void setLetra(Character Letra) {
		this.Letra = Letra;
	}   
	public String getTurnoManyanaTarde() {
		return this.TurnoManyanaTarde;
	}

	public void setTurnoMañanaTarde(String TurnoManyanaTarde) {
		this.TurnoManyanaTarde = TurnoManyanaTarde;
	}   
	public Boolean getIngles() {
		return this.Ingles;
	}

	public void setIngles(Boolean Ingles) {
		this.Ingles = Ingles;
	}   
	public Boolean getVisible() {
		return this.Visible;
	}

	public void setVisible(Boolean Visible) {
		this.Visible = Visible;
	}   
	public String getAsignar() {
		return this.Asignar;
	}

	public void setAsignar(String Asignar) {
		this.Asignar = Asignar;
	}   
	public Integer getPlazas() {
		return this.Plazas;
	}

	public void setPlazas(Integer Plazas) {
		this.Plazas = Plazas;
	}   
	public String getContrasenya() {
		return this.Contrasenya;
	}

	public void setContrasenya(String Contrasenya) {
		this.Contrasenya = Contrasenya;
	}
   
	@Override
	public boolean equals(Object obj) {
		boolean esGrupo = obj instanceof Grupo;
		Grupo grupo = (Grupo)obj;
		return esGrupo && grupo.getID().equals(ID);
	}
}

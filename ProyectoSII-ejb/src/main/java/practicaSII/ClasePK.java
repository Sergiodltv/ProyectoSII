package practicaSII;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ID class for entity: Clase
 *
 */ 
@Embeddable
public class ClasePK implements Serializable {   
   
	@Temporal (TemporalType.DATE)         
	private Date dia;         
	private String horaInicio;
	private static final long serialVersionUID = 1L;
	private Integer grupo_fk;
     
	public ClasePK() {}

	
	public Integer getGrupoFk() {
		return grupo_fk;
	}
	
	public void setGrupoFk(Integer grupo_fk) {
		this.grupo_fk = grupo_fk;
	}

	public Date getDia() {
		return this.dia;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}
	

	public String getHoraInicio() {
		return this.horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	
   	
	public boolean equals(Object obj) {
		boolean esClase = obj instanceof ClasePK;
		ClasePK clase = (ClasePK)obj;
		return esClase && clase.getDia().equals(dia) && clase.getHoraInicio().equals(horaInicio) && clase.grupo_fk.equals(grupo_fk);
	}
	
	/*	 
	 * @see java.lang.Object#hashCode()
	 */	
	public int hashCode() {
		return dia.hashCode() + horaInicio.hashCode() + grupo_fk.hashCode();
	}
   
   
}


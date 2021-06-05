package practicaSII;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Integer;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Grupos_Por_Asignatura
 *
 */
@Entity

public class Grupos_Por_Asignatura implements Serializable {

	   
	
	@EmbeddedId
	private Grupo_Por_AsignaturaPK id;
	private Boolean Oferta;
	private static final long serialVersionUID = 1L;

	@ManyToMany (mappedBy = "enc_gr")
	private List<Encuesta> gr_enc;
	
	@MapsId("gr_fk")
	@ManyToOne
	private Grupo grup;
	
	@MapsId("asi_fk")
	@ManyToOne
	private Asignatura asi;
	
	public Grupos_Por_Asignatura() {
		super();
	}   
	   
	public Boolean getOferta() {
		return this.Oferta;
	}

	public void setOferta(Boolean Oferta) {
		this.Oferta = Oferta;
	}

	public Grupo_Por_AsignaturaPK getId() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		boolean esGpa = obj instanceof Centro;
		Grupos_Por_Asignatura gpa = (Grupos_Por_Asignatura)obj;
		return esGpa && gpa.getId().equals(id);
	}
   
}

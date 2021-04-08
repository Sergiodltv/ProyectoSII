package practicaSII;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Clase
 *
 */
@Entity


public class Clase implements Serializable {

	   
	@EmbeddedId
	private ClasePK id;
	@Temporal (TemporalType.TIME)
	private Date HoraFin;
	private static final long serialVersionUID = 1L;
	
	@MapsId("grupo_fk")
	@ManyToOne
	private Grupo gr_cl;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Asignatura asig_cl;

	public Clase() {
		super();
	}   

	public Date getHoraFin() {
		return this.HoraFin;
	}

	public void setHoraFin(Date HoraFin) {
		this.HoraFin = HoraFin;
	}
   
}

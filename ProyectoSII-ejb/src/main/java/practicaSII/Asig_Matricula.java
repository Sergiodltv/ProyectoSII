package practicaSII;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Asig_Matricula
 *
 */
@Entity

public class Asig_Matricula implements Serializable {

	@EmbeddedId
	private Asig_MatriculaPK id;
	
	@MapsId("matid")
	@ManyToOne
	private Matricula asigmt_mt;
	
	@MapsId("ref")
	@ManyToOne
	private Asignatura asigmt_asig;
	
	@ManyToOne
	private Grupo gr_asig;
	
	private static final long serialVersionUID = 1L;

	public Asig_Matricula() {
		super();
	}
   
}

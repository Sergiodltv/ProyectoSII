package practicaSII;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Encuesta
 *
 */
@Entity

public class Encuesta implements Serializable {

	   
	@Id @Temporal (TemporalType.DATE)
	private Date FechaEnvio;
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Expediente enc_exp;
	
	@ManyToMany
	private List<Grupos_Por_Asignatura> enc_gr;

	public Encuesta() {
		super();
	}   
	public Date getFechaEnvio() {
		return this.FechaEnvio;
	}

	public void setFechaEnvio(Date FechaEnvio) {
		this.FechaEnvio = FechaEnvio;
	}
   	
	@Override
	public boolean equals(Object obj) {
		boolean esEncuesta = obj instanceof Encuesta;
		Encuesta encuesta = (Encuesta)obj;
		return esEncuesta && encuesta.getFechaEnvio().equals(FechaEnvio);
	}
}

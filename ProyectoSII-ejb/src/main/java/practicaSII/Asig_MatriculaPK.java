package practicaSII;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Asig_MatriculaPK implements Serializable{
	private MatriculaPK matid;
	private Integer ref;
	
	public MatriculaPK getMatid() {
		return matid;
	}
	
	public Integer getRef() {
		return ref;
	}
	
	@Override
	public int hashCode() {
		return matid.hashCode() + ref.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		boolean esAsig = obj instanceof Asig_MatriculaPK;
		Asig_MatriculaPK asig = (Asig_MatriculaPK)obj;
		return esAsig && asig.getMatid().equals(matid) && asig.getRef().equals(ref);
	}
}

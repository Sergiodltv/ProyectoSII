package practicaSII;

import java.io.Serializable;

import javax.persistence.*;

@Embeddable
public class MatriculaPK implements Serializable{
	private String cursoAcademico;
	private Integer exp_fk;
	
	public MatriculaPK() {
	}
	
	public MatriculaPK(String cursoAcademico, Integer exp_fk) {
		this.cursoAcademico = cursoAcademico;
		this.exp_fk = exp_fk;
	}
	
	public String getCursoAcademico() {
		return cursoAcademico;
	}
	
	public void setCursoAcademico(String cursoAcademico) {
		this.cursoAcademico = cursoAcademico;
	}
	
	public Integer getExp_fk() {
		return exp_fk;
	}
	
	public void setExp_fk(Integer exp_fk) {
		this.exp_fk = exp_fk;
	}
	
	@Override
	public int hashCode() {
		return cursoAcademico.hashCode() + exp_fk.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		boolean esMatricula = obj instanceof MatriculaPK;
		MatriculaPK mat = (MatriculaPK)obj;
		return esMatricula && mat.getCursoAcademico().equals(cursoAcademico) && mat.getExp_fk().equals(exp_fk);
	}
}

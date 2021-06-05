package practicaSII;


import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Grupo_Por_AsignaturaPK  implements Serializable{
		
		private String cursoAcademico;
		private Integer asi_fk;
		private Integer gr_fk;
		public String getCursoAcademico() {
			return cursoAcademico;
		}
		public void setCursoAcademico(String cursoAcademico) {
			cursoAcademico = cursoAcademico;
		}
		public Integer getGrupo_asi_fk() {
			return asi_fk;
		}
		public void setGrupo_asi_fk(Integer grupo_p_a_fk) {
			this.asi_fk = grupo_p_a_fk;
		}
		public Integer getGrupo_gr_fk() {
			return gr_fk;
		}
		public void setGrupo_gr_fk(Integer gr_fk) {
			this.gr_fk = gr_fk;
		}
		@Override
		public boolean equals(Object obj) {
			boolean esGrupo = obj instanceof Grupo_Por_AsignaturaPK;
			Grupo_Por_AsignaturaPK grupo = (Grupo_Por_AsignaturaPK)obj;
			return esGrupo && grupo.getCursoAcademico().equals(cursoAcademico) 
					&& grupo.getGrupo_gr_fk().equals(gr_fk)
					&& grupo.getGrupo_asi_fk().equals(asi_fk);
		}
		@Override
		public int hashCode() {
			return cursoAcademico.hashCode() + gr_fk.hashCode() + asi_fk.hashCode();
		}

}
		


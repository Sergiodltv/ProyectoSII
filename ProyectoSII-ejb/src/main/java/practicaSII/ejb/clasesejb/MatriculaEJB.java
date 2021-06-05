package practicaSII.ejb.clasesejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import practicaSII.Matricula;
import practicaSII.MatriculaPK;
import practicaSII.ejb.excetption.MatriculaEncontradaException;
import practicaSII.ejb.excetption.MatriculaNoEncontradaException;

@Stateless
public class MatriculaEJB implements GestionMatriculas{
	@PersistenceContext(name="Secretaria")
	private EntityManager em;

	@Override
	public void anadirMatricula(Matricula mat) throws MatriculaEncontradaException {
		MatriculaPK pk = new MatriculaPK();
		pk.setCursoAcademico(mat.getId().getCursoAcademico());
		pk.setExp_fk(mat.getId().getExp_fk());
		Matricula matEntity = em.find(Matricula.class, pk);
		if (matEntity != null) {
			throw new MatriculaEncontradaException();
		}
		em.persist(mat);
	}

	@Override
	public void modificarMatricula(Matricula mat) throws MatriculaNoEncontradaException {
		MatriculaPK pk = new MatriculaPK();
		pk.setCursoAcademico(mat.getId().getCursoAcademico());
		pk.setExp_fk(mat.getId().getExp_fk());
		Matricula matEntity = em.find(Matricula.class, pk);
		if (matEntity != null) {
			throw new MatriculaNoEncontradaException();
		}
		em.merge(mat);
	}

	@Override
	public void borrarMatricula(Matricula mat) throws MatriculaNoEncontradaException {
		MatriculaPK pk = new MatriculaPK();
		pk.setCursoAcademico(mat.getId().getCursoAcademico());
		pk.setExp_fk(mat.getId().getExp_fk());
		Matricula matEntity = em.find(Matricula.class, pk);
		if (matEntity != null) {
			throw new MatriculaNoEncontradaException();
		}
		em.remove(matEntity);
	}
}

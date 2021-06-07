package practicaSII.ejb.test;

import java.util.Properties;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import practicaSII.Alumno;
import practicaSII.Expediente;
import practicaSII.Matricula;
import practicaSII.MatriculaPK;
import practicaSII.ejb.clasesejb.GestionAlumnos;
import practicaSII.ejb.clasesejb.GestionMatriculas;
import practicaSII.ejb.excetption.AlumnoEncontradoException;
import practicaSII.ejb.excetption.AlumnoNoEncontradoException;
import practicaSII.ejb.excetption.MatriculaEncontradaException;
import practicaSII.ejb.excetption.MatriculaNoEncontradaException;

public class MatriculasTests {
	private static final Logger LOG = Logger.getLogger(MatriculasTests.class.getCanonicalName());

	private static final String GLASSFISH_CONFIGI_FILE_PROPERTY = "org.glassfish.ejb.embedded.glassfish.configuration.file";
	private static final String CONFIG_FILE = "target/test-classes/META-INF/domain.xml";
	private static final String MATRICULAS_EJB = "java:global/classes/MatriculasEJB";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "practicaSII";
	
	private static EJBContainer ejbContainer;
	private static Context ctx;
	
	private GestionMatriculas gestionMatriculas;
	
	@BeforeClass
	public static void setUpClass() {
		Properties properties = new Properties();
		properties.setProperty(GLASSFISH_CONFIGI_FILE_PROPERTY, CONFIG_FILE);
		ejbContainer = EJBContainer.createEJBContainer(properties);
		ctx = ejbContainer.getContext();
	}
	
	@Before
	public void setup() throws NamingException  {
		gestionMatriculas = (GestionMatriculas) ctx.lookup(MATRICULAS_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}

	@Test
	public void anadirMatriculaEncontrada() {
		MatriculaPK mpk1 = new MatriculaPK();
		mpk1.setCursoAcademico("2");
		mpk1.setExp_fk(1);
		
		Matricula m1 = new Matricula();
		m1.setId(mpk1);
		m1.setEstado(true);
		m1.setFechaMatricula("12/10/21");
		m1.setNuevoIngreso(false);
		m1.setNumArchivo(1);
		m1.setTurnoPreferido("Tarde");
		
		try {
			gestionMatriculas.anadirMatricula(m1);
			fail("Matricula no esta en la base de datos");
		} catch (MatriculaEncontradaException e) {
			//OK
		}
	}
	
	@Test
	public void anadirMatriculaNoEncontrada() {
		MatriculaPK mpk2 = new MatriculaPK();
		mpk2.setCursoAcademico("3");
		mpk2.setExp_fk(2);
		
		Matricula m2 = new Matricula();
		m2.setId(mpk2);
		m2.setEstado(false);
		m2.setFechaMatricula("5/1/20");
		m2.setNuevoIngreso(false);
		m2.setNumArchivo(3);
		m2.setTurnoPreferido("Manana");
		
		try {
			gestionMatriculas.anadirMatricula(m2);
		} catch (MatriculaEncontradaException e) {
			fail("Matricula esta en la base de datos");
		}
	}
	
	@Test
	public void modificarMatriculaEncontrada() {
		MatriculaPK mpk1 = new MatriculaPK();
		mpk1.setCursoAcademico("2");
		mpk1.setExp_fk(1);
		
		Matricula m1 = new Matricula();
		m1.setId(mpk1);
		m1.setEstado(true);
		m1.setFechaMatricula("12/10/21");
		m1.setNuevoIngreso(false);
		m1.setNumArchivo(1);
		m1.setTurnoPreferido("Tarde");
		
		try {
			gestionMatriculas.modificarMatricula(m1);
		} catch (MatriculaNoEncontradaException e) {
			fail("Matricula no esta en la base de datos");
		}
	}
	
	@Test
	public void modificarMatriculaNoEncontrada() {
		MatriculaPK mpk2 = new MatriculaPK();
		mpk2.setCursoAcademico("3");
		mpk2.setExp_fk(2);
		
		Matricula m2 = new Matricula();
		m2.setId(mpk2);
		m2.setEstado(false);
		m2.setFechaMatricula("5/1/20");
		m2.setNuevoIngreso(false);
		m2.setNumArchivo(3);
		m2.setTurnoPreferido("Manana");
		
		try {
			gestionMatriculas.modificarMatricula(m2);
			fail("Matricula esta en la base de datos");
		} catch (MatriculaNoEncontradaException e) {
			//OK
		}
	}
	
	@Test
	public void eliminarMatriculaEncontrada() {
		MatriculaPK mpk1 = new MatriculaPK();
		mpk1.setCursoAcademico("2");
		mpk1.setExp_fk(1);
		
		Matricula m1 = new Matricula();
		m1.setId(mpk1);
		m1.setEstado(true);
		m1.setFechaMatricula("12/10/21");
		m1.setNuevoIngreso(false);
		m1.setNumArchivo(1);
		m1.setTurnoPreferido("Tarde");
		
		try {
			gestionMatriculas.borrarMatricula(m1);
		} catch (MatriculaNoEncontradaException e) {
			fail("Matricula no esta en la base de datos");
		}
	}
	
	@Test
	public void eliminarMatriculaNoEncontrada() {
		MatriculaPK mpk2 = new MatriculaPK();
		mpk2.setCursoAcademico("3");
		mpk2.setExp_fk(2);
		
		Matricula m2 = new Matricula();
		m2.setId(mpk2);
		m2.setEstado(false);
		m2.setFechaMatricula("5/1/20");
		m2.setNuevoIngreso(false);
		m2.setNumArchivo(3);
		m2.setTurnoPreferido("Manana");
		
		try {
			gestionMatriculas.borrarMatricula(m2);
			fail("Matricula esta en la base de datos");
		} catch (MatriculaNoEncontradaException e) {
			//OK
		}
	}
	
	@AfterClass
	public static void tearDownClass() {
		if (ejbContainer != null) {
			ejbContainer.close();
		}
	}
}

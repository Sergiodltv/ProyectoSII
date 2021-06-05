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
import practicaSII.Titulacion;
import practicaSII.ejb.clasesejb.GestionAlumnos;
import practicaSII.ejb.clasesejb.GestionTitulacion;
import practicaSII.ejb.excetption.AlumnoEncontradoException;
import practicaSII.ejb.excetption.AlumnoNoEncontradoException;
import practicaSII.ejb.excetption.TitulacionEncontradaException;
import practicaSII.ejb.excetption.TitulacionNoEncontradaException;

public class TitulacionTests {
	private static final Logger LOG = Logger.getLogger(TitulacionTests.class.getCanonicalName());

	private static final String GLASSFISH_CONFIGI_FILE_PROPERTY = "org.glassfish.ejb.embedded.glassfish.configuration.file";
	private static final String CONFIG_FILE = "target/test-classes/META-INF/domain.xml";
	private static final String TITULACION_EJB = "java:global/classes/TitulacionEJB";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "practicaSII";
	
	private static EJBContainer ejbContainer;
	private static Context ctx;
	
	private GestionTitulacion gestionTitulaciones;
	
	@BeforeClass
	public static void setUpClass() {
		Properties properties = new Properties();
		properties.setProperty(GLASSFISH_CONFIGI_FILE_PROPERTY, CONFIG_FILE);
		ejbContainer = EJBContainer.createEJBContainer(properties);
		ctx = ejbContainer.getContext();
	}
	
	@Before
	public void setup() throws NamingException  {
		gestionTitulaciones = (GestionTitulacion) ctx.lookup(TITULACION_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}

	@Test
	public void anadirAlumnoEncontrado() {
		Titulacion t1 = new Titulacion();
		t1.setCodigo(1);
		t1.setCreditos(60);
		t1.setNombre("GII");
		
		try {
			gestionTitulaciones.anadirTitulacion(t1);
			fail("Titulacion no esta en la base de datos");
		} catch (TitulacionEncontradaException e) {
			//OK
		}
	}
	
	@Test
	public void anadirAlumnoNoEncontrado() {
		Titulacion t2 = new Titulacion();
		t2.setCodigo(2);
		t2.setCreditos(100);
		t2.setNombre("GOO");
		
		try {
			gestionTitulaciones.anadirTitulacion(t2);
		} catch (TitulacionEncontradaException e) {
			fail("Titulacion esta en la base de datos");
		}
	}
	
	@Test
	public void modificarAlumnoEncontrado() {
		Titulacion t1 = new Titulacion();
		t1.setCodigo(1);
		t1.setCreditos(60);
		t1.setNombre("GII");
		
		try {
			gestionTitulaciones.modificarTitulacion(t1);
		} catch (TitulacionNoEncontradaException e) {
			fail("Titulacion no esta en la base de datos");
		}
	}
	
	@Test
	public void modificarAlumnoNoEncontrado() {
		Titulacion t2 = new Titulacion();
		t2.setCodigo(2);
		t2.setCreditos(100);
		t2.setNombre("GOO");
		
		try {
			gestionTitulaciones.modificarTitulacion(t2);
			fail("Titulacion esta en la base de datos");
		} catch (TitulacionNoEncontradaException e) {
			//OK
		}
	}
	
	@Test
	public void eliminarAlumnoEncontrado() {
		Titulacion t1 = new Titulacion();
		t1.setCodigo(1);
		t1.setCreditos(60);
		t1.setNombre("GII");
		
		try {
			gestionTitulaciones.eliminarTitulacion(t1);
		} catch (TitulacionNoEncontradaException e) {
			fail("Titulacion no esta en la base de datos");
		}
	}
	
	@Test
	public void eliminarAlumnoNoEncontrado() {
		Titulacion t2 = new Titulacion();
		t2.setCodigo(2);
		t2.setCreditos(100);
		t2.setNombre("GOO");
		
		try {
			gestionTitulaciones.eliminarTitulacion(t2);
			fail("Titulacion esta en la base de datos");
		} catch (TitulacionNoEncontradaException e) {
			//OK
		}
	}
	
	@Test
	public void obtenerAlumnoEncontrado() {
		Integer id1 = 1;
		
		try {
			gestionTitulaciones.obtenerTitulacionConCodigo(id1);
		} catch (TitulacionNoEncontradaException e) {
			fail("Titulacion no esta en la base de datos");
		}
	}
	
	@Test
	public void obtenerAlumnoNoEncontrado() {
		Integer id2 = 2;
		
		try {
			gestionTitulaciones.obtenerTitulacionConCodigo(id2);
			fail("Titulacion esta en la base de datos");
		} catch (TitulacionNoEncontradaException e) {
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

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
import practicaSII.Centro;
import practicaSII.Expediente;
import practicaSII.ejb.clasesejb.GestionAlumnos;
import practicaSII.ejb.clasesejb.GestionCentro;
import practicaSII.ejb.excetption.AlumnoEncontradoException;
import practicaSII.ejb.excetption.AlumnoNoEncontradoException;
import practicaSII.ejb.excetption.CentroEncontradoException;
import practicaSII.ejb.excetption.CentroNoEncontradoException;

public class CentrosTests {
	private static final Logger LOG = Logger.getLogger(CentrosTests.class.getCanonicalName());

	private static final String GLASSFISH_CONFIGI_FILE_PROPERTY = "org.glassfish.ejb.embedded.glassfish.configuration.file";
	private static final String CONFIG_FILE = "target/test-classes/META-INF/domain.xml";
	private static final String CENTROS_EJB = "java:global/classes/CentroEJB";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "practicaSII";
	
	private static EJBContainer ejbContainer;
	private static Context ctx;
	
	private GestionCentro gestionCentros;
	
	@BeforeClass
	public static void setUpClass() {
		Properties properties = new Properties();
		properties.setProperty(GLASSFISH_CONFIGI_FILE_PROPERTY, CONFIG_FILE);
		ejbContainer = EJBContainer.createEJBContainer(properties);
		ctx = ejbContainer.getContext();
	}
	
	@Before
	public void setup() throws NamingException  {
		gestionCentros = (GestionCentro) ctx.lookup(CENTROS_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}

	@Test
	public void anadirCentroEncontrado() {
		Centro c1 = new Centro();
		c1.setID("1");
		c1.setDireccion("Avenida Plutarco");
		c1.setNombre("ETSII");
		c1.setTelefonoConsergeria(123456789);
		
		try {
			gestionCentros.anadirCentro(c1);
			fail("Centro no esta en la base de datos");
		} catch (CentroEncontradoException e) {
			//OK
		}
	}
	
	@Test
	public void anadirCentroNoEncontrado() {
		Centro c2 = new Centro();
		c2.setID("2");
		c2.setDireccion("Avenida Aristofanes");
		c2.setNombre("ETSOO");
		c2.setTelefonoConsergeria(1111111);
		
		try {
			gestionCentros.anadirCentro(c2);
		} catch (CentroEncontradoException e) {
			fail("Centro esta en la base de datos");
		}
	}
	
	@Test
	public void modificarCentroEncontrado() {
		Centro c1 = new Centro();
		c1.setID("1");
		c1.setDireccion("Avenida Plutarco");
		c1.setNombre("ETSII");
		c1.setTelefonoConsergeria(123456789);
		
		try {
			gestionCentros.modificarCentro(c1);
		} catch (CentroNoEncontradoException e) {
			fail("Centro no esta en la base de datos");
		}
	}
	
	@Test
	public void modificarCentroNoEncontrado() {
		Centro c2 = new Centro();
		c2.setID("2");
		c2.setDireccion("Avenida Aristofanes");
		c2.setNombre("ETSOO");
		c2.setTelefonoConsergeria(1111111);
		
		try {
			gestionCentros.modificarCentro(c2);
			fail("Centro esta en la base de datos");
		} catch (CentroNoEncontradoException e) {
			//OK
		}
	}
	
	@Test
	public void eliminarCentroEncontrado() {
		Centro c1 = new Centro();
		c1.setID("1");
		c1.setDireccion("Avenida Plutarco");
		c1.setNombre("ETSII");
		c1.setTelefonoConsergeria(123456789);
		
		try {
			gestionCentros.eliminarCentro(c1);
		} catch (CentroNoEncontradoException e) {
			fail("Centro no esta en la base de datos");
		}
	}
	
	@Test
	public void eliminarCentroNoEncontrado() {
		Centro c2 = new Centro();
		c2.setID("2");
		c2.setDireccion("Avenida Aristofanes");
		c2.setNombre("ETSOO");
		c2.setTelefonoConsergeria(1111111);
		
		try {
			gestionCentros.eliminarCentro(c2);
			fail("Centro esta en la base de datos");
		} catch (CentroNoEncontradoException e) {
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

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
import practicaSII.Pas;
import practicaSII.ejb.clasesejb.GestionAlumnos;
import practicaSII.ejb.clasesejb.GestionPas;
import practicaSII.ejb.excetption.AlumnoEncontradoException;
import practicaSII.ejb.excetption.AlumnoNoEncontradoException;
import practicaSII.ejb.excetption.PasEncontradoException;
import practicaSII.ejb.excetption.PasNoEncontradoException;

public class PasTests {
	private static final Logger LOG = Logger.getLogger(PasTests.class.getCanonicalName());

	private static final String GLASSFISH_CONFIGI_FILE_PROPERTY = "org.glassfish.ejb.embedded.glassfish.configuration.file";
	private static final String CONFIG_FILE = "target/test-classes/META-INF/domain.xml";
	private static final String PAS_EJB = "java:global/classes/PasEJB";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "practicaSII";
	
	private static EJBContainer ejbContainer;
	private static Context ctx;
	
	private GestionPas gestionPas;
	
	@BeforeClass
	public static void setUpClass() {
		Properties properties = new Properties();
		properties.setProperty(GLASSFISH_CONFIGI_FILE_PROPERTY, CONFIG_FILE);
		ejbContainer = EJBContainer.createEJBContainer(properties);
		ctx = ejbContainer.getContext();
	}
	
	@Before
	public void setup() throws NamingException  {
		gestionPas = (GestionPas) ctx.lookup(PAS_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}

	@Test
	public void anadirPasEncontrado() {
		Pas p1 = new Pas();
		p1.setID("1");
		p1.setDNI("1234567A");
		p1.setContrasenya("contraseña");
		p1.setNombre("Paco");
		p1.setApellido1("Jimenez");
		p1.setApellido2("apellido2");
		p1.setEmailInstitucional("uncorreo@mail.com");
		p1.setTelefonoContacto(11223);
		p1.setDireccion("dirección");
		
		try {
			gestionPas.anadirPas(p1);
			fail("Pas no esta en la base de datos");
		} catch (PasEncontradoException e) {
			//OK
		}
	}
	
	@Test
	public void anadirPasoNoEncontrado() {
		Pas p2 = new Pas();
		p2.setID("2");
		p2.setDNI("1234567B");
		p2.setContrasenya("contraseña1");
		p2.setNombre("Paco1");
		p2.setApellido1("Jimenez1");
		p2.setApellido2("apellido21");
		p2.setEmailInstitucional("uncorreo@mail1.com");
		p2.setTelefonoContacto(11225);
		p2.setDireccion("dirección1");
		
		try {
			gestionPas.anadirPas(p2);
		} catch (PasEncontradoException e) {
			fail("Pas esta en la base de datos");
		}
	}
	
	@Test
	public void eliminarPasEncontrado() {
		Pas p1 = new Pas();
		p1.setID("1");
		p1.setDNI("1234567A");
		p1.setContrasenya("contraseña");
		p1.setNombre("Paco");
		p1.setApellido1("Jimenez");
		p1.setApellido2("apellido2");
		p1.setEmailInstitucional("uncorreo@mail.com");
		p1.setTelefonoContacto(11223);
		p1.setDireccion("dirección");
		
		try {
			gestionPas.eliminarPas(p1);
		} catch (PasNoEncontradoException e) {
			fail("Pas no esta en la base de datos");
		}
	}
	
	@Test
	public void eliminarPasNoEncontrado() {
		Pas p2 = new Pas();
		p2.setID("2");
		p2.setDNI("1234567B");
		p2.setContrasenya("contraseña1");
		p2.setNombre("Paco1");
		p2.setApellido1("Jimenez1");
		p2.setApellido2("apellido21");
		p2.setEmailInstitucional("uncorreo@mail1.com");
		p2.setTelefonoContacto(11225);
		p2.setDireccion("dirección1");
		
		try {
			gestionPas.eliminarPas(p2);
			fail("Pas esta en la base de datos");
		} catch (PasNoEncontradoException e) {
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

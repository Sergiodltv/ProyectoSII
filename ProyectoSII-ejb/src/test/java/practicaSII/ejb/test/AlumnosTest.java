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
import practicaSII.ejb.clasesejb.GestionAlumnos;
import practicaSII.ejb.excetption.AlumnoEncontradoException;
import practicaSII.ejb.excetption.AlumnoNoEncontradoException;

public class AlumnosTest {
	private static final Logger LOG = Logger.getLogger(AlumnosTest.class.getCanonicalName());

	private static final String GLASSFISH_CONFIGI_FILE_PROPERTY = "org.glassfish.ejb.embedded.glassfish.configuration.file";
	private static final String CONFIG_FILE = "target/test-classes/META-INF/domain.xml";
	private static final String ALUMNOS_EJB = "java:global/classes/AlumnosEJB";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "practicaSII";
	
	private static EJBContainer ejbContainer;
	private static Context ctx;
	
	private GestionAlumnos gestionAlumnos;
	
	@BeforeClass
	public static void setUpClass() {
		Properties properties = new Properties();
		properties.setProperty(GLASSFISH_CONFIGI_FILE_PROPERTY, CONFIG_FILE);
		ejbContainer = EJBContainer.createEJBContainer(properties);
		ctx = ejbContainer.getContext();
	}
	
	@Before
	public void setup() throws NamingException  {
		gestionAlumnos = (GestionAlumnos) ctx.lookup(ALUMNOS_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}

	@Test
	public void anadirAlumnoEncontrado() {
		Alumno a1 = new Alumno();
		a1.setId(1);
		a1.setDni("1234567A");
		a1.setContrasenya("contraseña");
		a1.setNombre("Paco");
		a1.setApellido1("Jimenez");
		a1.setApellido2("apellido2");
		a1.setEmailInstitucional("uncorreo@mail.com");
		a1.setEmailPersonal("mailpersonal@mail.com");
		a1.setTelefono(11223);
		a1.setMovil(32211);
		a1.setDireccion("dirección");
		
		try {
			gestionAlumnos.anadirAlumnoMatr(a1);
			fail("Alumno no esta en la base de datos");
		} catch (AlumnoEncontradoException e) {
			//OK
		}
	}
	
	@Test
	public void anadirAlumnoNoEncontrado() {
		Alumno a2 = new Alumno();
		a2.setId(2);
		a2.setDni("2334567B");
		a2.setContrasenya("contraseña2");
		a2.setNombre("Luis");
		a2.setApellido1("Gomez");
		a2.setApellido2("apellido5");
		a2.setEmailInstitucional("uncorreodistinto@mail.com");
		a2.setEmailPersonal("mailpersonalnuevo@mail.com");
		a2.setTelefono(964785);
		a2.setMovil(7941358);
		a2.setDireccion("direccióndistinta");
		
		try {
			gestionAlumnos.anadirAlumnoMatr(a2);
		} catch (AlumnoEncontradoException e) {
			fail("Alumno esta en la base de datos");
		}
	}
	
	@Test
	public void modificarAlumnoEncontrado() {
		Alumno a1 = new Alumno();
		a1.setId(1);
		a1.setDni("1234567A");
		a1.setContrasenya("contraseña");
		a1.setNombre("Paco");
		a1.setApellido1("Jimenez");
		a1.setApellido2("apellido2");
		a1.setEmailInstitucional("uncorreo@mail.com");
		a1.setEmailPersonal("mailpersonal@mail.com");
		a1.setTelefono(11223);
		a1.setMovil(32211);
		a1.setDireccion("dirección");
		
		try {
			gestionAlumnos.modificarAlumno(a1);
		} catch (AlumnoNoEncontradoException e) {
			fail("Alumno no esta en la base de datos");
		}
	}
	
	@Test
	public void modificarAlumnoNoEncontrado() {
		Alumno a2 = new Alumno();
		a2.setId(2);
		a2.setDni("2334567B");
		a2.setContrasenya("contraseña2");
		a2.setNombre("Luis");
		a2.setApellido1("Gomez");
		a2.setApellido2("apellido5");
		a2.setEmailInstitucional("uncorreodistinto@mail.com");
		a2.setEmailPersonal("mailpersonalnuevo@mail.com");
		a2.setTelefono(964785);
		a2.setMovil(7941358);
		a2.setDireccion("direccióndistinta");
		
		try {
			gestionAlumnos.modificarAlumno(a2);
			fail("Alumno esta en la base de datos");
		} catch (AlumnoNoEncontradoException e) {
			//OK
		}
	}
	
	@Test
	public void eliminarAlumnoEncontrado() {
		Alumno a1 = new Alumno();
		a1.setId(1);
		a1.setDni("1234567A");
		a1.setContrasenya("contraseña");
		a1.setNombre("Paco");
		a1.setApellido1("Jimenez");
		a1.setApellido2("apellido2");
		a1.setEmailInstitucional("uncorreo@mail.com");
		a1.setEmailPersonal("mailpersonal@mail.com");
		a1.setTelefono(11223);
		a1.setMovil(32211);
		a1.setDireccion("dirección");
		
		try {
			gestionAlumnos.eliminarAlumno(a1);
		} catch (AlumnoNoEncontradoException e) {
			fail("Alumno no esta en la base de datos");
		}
	}
	
	@Test
	public void eliminarAlumnoNoEncontrado() {
		Alumno a2 = new Alumno();
		a2.setId(2);
		a2.setDni("2334567B");
		a2.setContrasenya("contraseña2");
		a2.setNombre("Luis");
		a2.setApellido1("Gomez");
		a2.setApellido2("apellido5");
		a2.setEmailInstitucional("uncorreodistinto@mail.com");
		a2.setEmailPersonal("mailpersonalnuevo@mail.com");
		a2.setTelefono(964785);
		a2.setMovil(7941358);
		a2.setDireccion("direccióndistinta");
		
		try {
			gestionAlumnos.eliminarAlumno(a2);
			fail("Alumno esta en la base de datos");
		} catch (AlumnoNoEncontradoException e) {
			//OK
		}
	}
	
	@Test
	public void obtenerAlumnoEncontrado() {
		Integer id1 = 1;
		
		try {
			gestionAlumnos.obtenerAlumno(id1);
		} catch (AlumnoNoEncontradoException e) {
			fail("Alumno no esta en la base de datos");
		}
	}
	
	@Test
	public void obtenerAlumnoNoEncontrado() {
		Integer id2 = 2;
		
		try {
			gestionAlumnos.obtenerAlumno(id2);
			fail("Alumno esta en la base de datos");
		} catch (AlumnoNoEncontradoException e) {
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

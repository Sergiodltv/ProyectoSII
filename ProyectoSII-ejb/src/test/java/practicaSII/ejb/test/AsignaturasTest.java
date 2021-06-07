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
import practicaSII.Asignatura;
import practicaSII.Expediente;
import practicaSII.Optativa;
import practicaSII.Titulacion;
import practicaSII.ejb.clasesejb.GestionAlumnos;
import practicaSII.ejb.clasesejb.GestionAsignaturas;
import practicaSII.ejb.excetption.AlumnoEncontradoException;
import practicaSII.ejb.excetption.AlumnoNoEncontradoException;
import practicaSII.ejb.excetption.AsignaturaEncontradaException;
import practicaSII.ejb.excetption.AsignaturaNoEncontradaException;
import practicaSII.ejb.excetption.OptativaNoEncontradaException;

public class AsignaturasTests {
	private static final Logger LOG = Logger.getLogger(AsignaturasTests.class.getCanonicalName());

	private static final String GLASSFISH_CONFIGI_FILE_PROPERTY = "org.glassfish.ejb.embedded.glassfish.configuration.file";
	private static final String CONFIG_FILE = "target/test-classes/META-INF/domain.xml";
	private static final String ASIGNATURAS_EJB = "java:global/classes/AsignaturaEJB";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "practicaSII";
	
	private static EJBContainer ejbContainer;
	private static Context ctx;
	
	private GestionAsignaturas gestionAsignaturas;
	
	@BeforeClass
	public static void setUpClass() {
		Properties properties = new Properties();
		properties.setProperty(GLASSFISH_CONFIGI_FILE_PROPERTY, CONFIG_FILE);
		ejbContainer = EJBContainer.createEJBContainer(properties);
		ctx = ejbContainer.getContext();
	}
	
	@Before
	public void setup() throws NamingException  {
		gestionAsignaturas = (GestionAsignaturas) ctx.lookup(ASIGNATURAS_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}

	@Test
	public void anadirAsignaturaEncontrada() {
		Titulacion t1 = new Titulacion();
		t1.setCodigo(1);
		t1.setCreditos(60);
		t1.setNombre("GII");
		
		Asignatura a1 = new Asignatura();
		a1.setReferencia(1);
		a1.setCodigo(123);
		a1.setCreditosTotales(6);
		a1.setCreditosPr(3);
		a1.setCreditosTeo(3);
		a1.setOfertada("Si");
		a1.setNombre("Internet");
		a1.setCurso(2);
		a1.setPlazas("250");
		a1.setDuracion("35");
		a1.setIngles("Si");
		a1.setAs_titul(t1);
		
		try {
			gestionAsignaturas.anadirAsingatura(a1);
			fail("Asignatura no esta en la base de datos");
		} catch (AsignaturaEncontradaException e) {
			//OK
		}
	}
	
	@Test
	public void anadirAsignaturaNoEncontrada() {
		Titulacion t1 = new Titulacion();
		t1.setCodigo(1);
		t1.setCreditos(60);
		t1.setNombre("GII");
		
		Asignatura a2 = new Asignatura();
		a2.setReferencia(2);
		a2.setCodigo(133);
		a2.setCreditosTotales(6);
		a2.setCreditosPr(3);
		a2.setCreditosTeo(3);
		a2.setOfertada("Si");
		a2.setNombre("Sistemas");
		a2.setCurso(3);
		a2.setPlazas("2100");
		a2.setDuracion("40");
		a2.setIngles("No");
		a2.setAs_titul(t1);
		
		try {
			gestionAsignaturas.anadirAsingatura(a2);
		} catch (AsignaturaEncontradaException e) {
			fail("Asignatura esta en la base de datos");
		}
	}
	
	@Test
	public void modificarAsignaturaEncontrada() {
		Titulacion t1 = new Titulacion();
		t1.setCodigo(1);
		t1.setCreditos(60);
		t1.setNombre("GII");
		
		Asignatura a1 = new Asignatura();
		a1.setReferencia(1);
		a1.setCodigo(123);
		a1.setCreditosTotales(6);
		a1.setCreditosPr(3);
		a1.setCreditosTeo(3);
		a1.setOfertada("Si");
		a1.setNombre("Internet");
		a1.setCurso(2);
		a1.setPlazas("250");
		a1.setDuracion("35");
		a1.setIngles("Si");
		a1.setAs_titul(t1);
		
		try {
			gestionAsignaturas.modificarAsignatura(a1);
		} catch (AsignaturaNoEncontradaException e) {
			fail("Asignatura no esta en la base de datos");
		}
	}
	
	@Test
	public void modificarAlumnoNoEncontrado() {
		Titulacion t1 = new Titulacion();
		t1.setCodigo(1);
		t1.setCreditos(60);
		t1.setNombre("GII");
		
		Asignatura a2 = new Asignatura();
		a2.setReferencia(2);
		a2.setCodigo(133);
		a2.setCreditosTotales(6);
		a2.setCreditosPr(3);
		a2.setCreditosTeo(3);
		a2.setOfertada("Si");
		a2.setNombre("Sistemas");
		a2.setCurso(3);
		a2.setPlazas("2100");
		a2.setDuracion("40");
		a2.setIngles("No");
		a2.setAs_titul(t1);
		
		try {
			gestionAsignaturas.modificarAsignatura(a2);
			fail("Asignatura esta en la base de datos");
		} catch (AsignaturaNoEncontradaException e) {
			//OK
		}
	}
	
	@Test
	public void eliminarAsignaturaEncontrada() {
		Titulacion t1 = new Titulacion();
		t1.setCodigo(1);
		t1.setCreditos(60);
		t1.setNombre("GII");
		
		Asignatura a1 = new Asignatura();
		a1.setReferencia(1);
		a1.setCodigo(123);
		a1.setCreditosTotales(6);
		a1.setCreditosPr(3);
		a1.setCreditosTeo(3);
		a1.setOfertada("Si");
		a1.setNombre("Internet");
		a1.setCurso(2);
		a1.setPlazas("250");
		a1.setDuracion("35");
		a1.setIngles("Si");
		a1.setAs_titul(t1);
		
		try {
			gestionAsignaturas.eliminarAsignatura(a1);
		} catch (AsignaturaNoEncontradaException e) {
			fail("Asignatura no esta en la base de datos");
		}
	}
	
	@Test
	public void eliminarAsignaturaNoEncontrado() {
		Titulacion t1 = new Titulacion();
		t1.setCodigo(1);
		t1.setCreditos(60);
		t1.setNombre("GII");
		
		Asignatura a2 = new Asignatura();
		a2.setReferencia(2);
		a2.setCodigo(133);
		a2.setCreditosTotales(6);
		a2.setCreditosPr(3);
		a2.setCreditosTeo(3);
		a2.setOfertada("Si");
		a2.setNombre("Sistemas");
		a2.setCurso(3);
		a2.setPlazas("2100");
		a2.setDuracion("40");
		a2.setIngles("No");
		a2.setAs_titul(t1);
		
		try {
			gestionAsignaturas.eliminarAsignatura(a2);
			fail("Asignatura esta en la base de datos");
		} catch (AsignaturaNoEncontradaException e) {
			//OK
		}
	}
	
	@Test
	public void obtenerAsignaturaEncontrada() {
		Integer id1 = 1;
		
		try {
			gestionAsignaturas.obtenerAsignaturaConCodigo(id1);
		} catch (AsignaturaNoEncontradaException e) {
			fail("Asignatura no esta en la base de datos");
		}
	}
	
	@Test
	public void obtenerAsignaturaNoEncontrada() {
		Integer id2 = 1;
		
		try {
			gestionAsignaturas.obtenerAsignaturaConCodigo(id2);
			fail("Asignatura esta en la base de datos");
		} catch (AsignaturaNoEncontradaException e) {
			//OK
		}
	}
	
	@Test
	public void anyadirOptativaEncontrada() {
		Titulacion t1 = new Titulacion();
		t1.setCodigo(1);
		t1.setCreditos(60);
		t1.setNombre("GII");
		
		Asignatura a1 = new Asignatura();
		a1.setReferencia(1);
		a1.setCodigo(123);
		a1.setCreditosTotales(6);
		a1.setCreditosPr(3);
		a1.setCreditosTeo(3);
		a1.setOfertada("Si");
		a1.setNombre("Internet");
		a1.setCurso(2);
		a1.setPlazas("250");
		a1.setDuracion("35");
		a1.setIngles("Si");
		a1.setAs_titul(t1);
		
		Optativa opt1 = new Optativa();
		opt1.setAsignatura(a1);
		opt1.setMencion("Computacion");
		opt1.setPlazas(250);
		
		try {
			gestionAsignaturas.anyadirOptativa(opt1);
		} catch (OptativaNoEncontradaException e) {
			fail("Asignatura no esta en la base de datos");
		}
	}
	
	@Test
	public void anyadirOptativaNoEncontrada() {
		Titulacion t1 = new Titulacion();
		t1.setCodigo(1);
		t1.setCreditos(60);
		t1.setNombre("GII");
		
		Asignatura a1 = new Asignatura();
		a1.setReferencia(1);
		a1.setCodigo(123);
		a1.setCreditosTotales(6);
		a1.setCreditosPr(3);
		a1.setCreditosTeo(3);
		a1.setOfertada("Si");
		a1.setNombre("Internet");
		a1.setCurso(2);
		a1.setPlazas("250");
		a1.setDuracion("35");
		a1.setIngles("Si");
		a1.setAs_titul(t1);
		
		Optativa opt1 = new Optativa();
		opt1.setAsignatura(a1);
		opt1.setMencion("Tecnologia");
		opt1.setPlazas(100);
		
		try {
			gestionAsignaturas.anyadirOptativa(opt1);
			fail("Asignatura esta en la base de datos");
		} catch (OptativaNoEncontradaException e) {
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

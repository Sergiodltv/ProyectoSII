package practicaSII.ejb.test;

import java.util.Date;
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
import practicaSII.Clase;
import practicaSII.ClasePK;
import practicaSII.Expediente;
import practicaSII.ejb.clasesejb.GestionAlumnos;
import practicaSII.ejb.clasesejb.GestionClase;
import practicaSII.ejb.excetption.AlumnoEncontradoException;
import practicaSII.ejb.excetption.AlumnoNoEncontradoException;
import practicaSII.ejb.excetption.ClaseEncontradaException;
import practicaSII.ejb.excetption.ClaseNoEncontradaException;

public class ClasesTests {
	private static final Logger LOG = Logger.getLogger(ClasesTests.class.getCanonicalName());

	private static final String GLASSFISH_CONFIGI_FILE_PROPERTY = "org.glassfish.ejb.embedded.glassfish.configuration.file";
	private static final String CONFIG_FILE = "target/test-classes/META-INF/domain.xml";
	private static final String CLASE_EJB = "java:global/classes/ClaseEJB";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "practicaSII";
	
	private static EJBContainer ejbContainer;
	private static Context ctx;
	
	private GestionClase gestionClases;
	
	@BeforeClass
	public static void setUpClass() {
		Properties properties = new Properties();
		properties.setProperty(GLASSFISH_CONFIGI_FILE_PROPERTY, CONFIG_FILE);
		ejbContainer = EJBContainer.createEJBContainer(properties);
		ctx = ejbContainer.getContext();
	}
	
	@Before
	public void setup() throws NamingException  {
		gestionClases = (GestionClase) ctx.lookup(CLASE_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}

	@Test
	public void anadirClaseEncontrada() {
		Date d = new Date();
		
		ClasePK clpk1 = new ClasePK();
		clpk1.setGrupoFk(1);
		clpk1.setDia(d);
		clpk1.setHoraInicio("12");
		
		Clase cl1 = new Clase();
		cl1.setHoraFin(d);
		cl1.setId(clpk1);
		
		try {
			gestionClases.anadirClase(cl1);
			fail("Clase no esta en la base de datos");
		} catch (ClaseEncontradaException e) {
			//OK
		}
	}
	
	@Test
	public void anadirClaseNoEncontrada() {
		Date d = new Date();
		
		ClasePK clpk2 = new ClasePK();
		clpk2.setGrupoFk(2);
		clpk2.setDia(d);
		clpk2.setHoraInicio("13");
		
		Clase cl2 = new Clase();
		cl2.setHoraFin(d);
		cl2.setId(clpk2);
		
		try {
			gestionClases.anadirClase(cl2);
		} catch (ClaseEncontradaException e) {
			fail("Clase esta en la base de datos");
		}
	}
	
	@Test
	public void modificarClaseEncontrado() {
		Date d = new Date();
		
		ClasePK clpk1 = new ClasePK();
		clpk1.setGrupoFk(1);
		clpk1.setDia(d);
		clpk1.setHoraInicio("12");
		
		Clase cl1 = new Clase();
		cl1.setHoraFin(d);
		cl1.setId(clpk1);
		
		try {
			gestionClases.modificarClase(cl1);
		} catch (ClaseNoEncontradaException e) {
			fail("Clase no esta en la base de datos");
		}
	}
	
	@Test
	public void modificarClaseNoEncontrado() {
Date d = new Date();
		
		ClasePK clpk2 = new ClasePK();
		clpk2.setGrupoFk(2);
		clpk2.setDia(d);
		clpk2.setHoraInicio("13");
		
		Clase cl2 = new Clase();
		cl2.setHoraFin(d);
		cl2.setId(clpk2);
		
		try {
			gestionClases.modificarClase(cl2);
			fail("Clase esta en la base de datos");
		} catch (ClaseNoEncontradaException e) {
			//OK
		}
	}
	
	@Test
	public void eliminarClaseEncontrado() {
		Date d = new Date();
		
		ClasePK clpk1 = new ClasePK();
		clpk1.setGrupoFk(1);
		clpk1.setDia(d);
		clpk1.setHoraInicio("12");
		
		Clase cl1 = new Clase();
		cl1.setHoraFin(d);
		cl1.setId(clpk1);
		
		try {
			gestionClases.eliminarClase(cl1);
		} catch (ClaseNoEncontradaException e) {
			fail("Clase no esta en la base de datos");
		}
	}
	
	@Test
	public void eliminarClaseNoEncontrado() {
Date d = new Date();
		
		ClasePK clpk2 = new ClasePK();
		clpk2.setGrupoFk(2);
		clpk2.setDia(d);
		clpk2.setHoraInicio("13");
		
		Clase cl2 = new Clase();
		cl2.setHoraFin(d);
		cl2.setId(clpk2);
		
		try {
			gestionClases.eliminarClase(cl2);
			fail("Clase esta en la base de datos");
		} catch (ClaseNoEncontradaException e) {
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

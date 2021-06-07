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
import practicaSII.ejb.clasesejb.GestionExpediente;
import practicaSII.ejb.excetption.AlumnoEncontradoException;
import practicaSII.ejb.excetption.AlumnoNoEncontradoException;
import practicaSII.ejb.excetption.ExpedienteEncontradoException;
import practicaSII.ejb.excetption.ExpedienteNoEncontradoException;

public class ExpedientesTests {
	private static final Logger LOG = Logger.getLogger(ExpedientesTests.class.getCanonicalName());

	private static final String GLASSFISH_CONFIGI_FILE_PROPERTY = "org.glassfish.ejb.embedded.glassfish.configuration.file";
	private static final String CONFIG_FILE = "target/test-classes/META-INF/domain.xml";
	private static final String EXPEDIENTES_EJB = "java:global/classes/ExpedienteEJB";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "practicaSII";
	
	private static EJBContainer ejbContainer;
	private static Context ctx;
	
	private GestionExpediente gestionExpedientes;
	
	@BeforeClass
	public static void setUpClass() {
		Properties properties = new Properties();
		properties.setProperty(GLASSFISH_CONFIGI_FILE_PROPERTY, CONFIG_FILE);
		ejbContainer = EJBContainer.createEJBContainer(properties);
		ctx = ejbContainer.getContext();
	}
	
	@Before
	public void setup() throws NamingException  {
		gestionExpedientes = (GestionExpediente) ctx.lookup(EXPEDIENTES_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}

	@Test
	public void anadirExpedienteEncontrado() {
		Expediente exp1 = new Expediente();
		exp1.setNumExpediente(1);
		
		try {
			gestionExpedientes.anyadirExpediente(exp1);
			fail("Expediente no esta en la base de datos");
		} catch (ExpedienteEncontradoException e) {
			//OK
		}
	}
	
	@Test
	public void anadirExpedienteNoEncontrado() {
		Expediente exp2 = new Expediente();
		exp2.setNumExpediente(2);
		
		try {
			gestionExpedientes.anyadirExpediente(exp2);
		} catch (ExpedienteEncontradoException e) {
			fail("Expediente esta en la base de datos");
		}
	}
	
	@Test
	public void modificarExpedienteEncontrado() {
		Expediente exp1 = new Expediente();
		exp1.setNumExpediente(1);
		
		try {
			gestionExpedientes.modificarExpediente(exp1);
		} catch (ExpedienteNoEncontradoException e) {
			fail("Expediente no esta en la base de datos");
		}
	}
	
	@Test
	public void modificarExpedienteNoEncontrado() {
		Expediente exp2 = new Expediente();
		exp2.setNumExpediente(2);
		
		try {
			gestionExpedientes.modificarExpediente(exp2);
			fail("Expediente esta en la base de datos");
		} catch (ExpedienteNoEncontradoException e) {
			//OK
		}
	}
	
	@Test
	public void eliminarExpedienteEncontrado() {
		Expediente exp1 = new Expediente();
		exp1.setNumExpediente(1);
		
		try {
			gestionExpedientes.eliminarExpediente(exp1);
		} catch (ExpedienteNoEncontradoException e) {
			fail("Expediente no esta en la base de datos");
		}
	}
	
	@Test
	public void eliminarExpedienteNoEncontrado() {
		Expediente exp2 = new Expediente();
		exp2.setNumExpediente(2);
		
		try {
			gestionExpedientes.eliminarExpediente(exp2);
			fail("Expediente esta en la base de datos");
		} catch (ExpedienteNoEncontradoException e) {
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

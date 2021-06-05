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
import practicaSII.Grupo;
import practicaSII.ejb.clasesejb.GestionAlumnos;
import practicaSII.ejb.clasesejb.GestionGrupos;
import practicaSII.ejb.excetption.AlumnoEncontradoException;
import practicaSII.ejb.excetption.AlumnoNoEncontradoException;
import practicaSII.ejb.excetption.GrupoEncontradoException;
import practicaSII.ejb.excetption.GrupoNoEncontradoException;

public class GruposTests {
	private static final Logger LOG = Logger.getLogger(GruposTests.class.getCanonicalName());

	private static final String GLASSFISH_CONFIGI_FILE_PROPERTY = "org.glassfish.ejb.embedded.glassfish.configuration.file";
	private static final String CONFIG_FILE = "target/test-classes/META-INF/domain.xml";
	private static final String GRUPOS_EJB = "java:global/classes/GrupoEJB";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "practicaSII";
	
	private static EJBContainer ejbContainer;
	private static Context ctx;
	
	private GestionGrupos gestionGrupos;
	
	@BeforeClass
	public static void setUpClass() {
		Properties properties = new Properties();
		properties.setProperty(GLASSFISH_CONFIGI_FILE_PROPERTY, CONFIG_FILE);
		ejbContainer = EJBContainer.createEJBContainer(properties);
		ctx = ejbContainer.getContext();
	}
	
	@Before
	public void setup() throws NamingException  {
		gestionGrupos = (GestionGrupos) ctx.lookup(GRUPOS_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}

	@Test
	public void anadirGrupoEncontrado() {
		Grupo gr1 = new Grupo();
		gr1.setID(1);
		gr1.setLetra('A');
		gr1.setPlazas(100);
		gr1.setCurso("2");
		
		try {
			gestionGrupos.anadirGrupo(gr1);
			fail("Grupo no esta en la base de datos");
		} catch (GrupoEncontradoException e) {
			//OK
		}
	}
	
	@Test
	public void anadirGrupoNoEncontrado() {
		Grupo gr2 = new Grupo();
		gr2.setID(2);
		gr2.setLetra('B');
		gr2.setPlazas(50);
		gr2.setCurso("3");
		
		try {
			gestionGrupos.anadirGrupo(gr2);
		} catch (GrupoEncontradoException e) {
			fail("Grupo esta en la base de datos");
		}
	}
	
	@Test
	public void modificarGrupoEncontrado() {
		Grupo gr1 = new Grupo();
		gr1.setID(1);
		gr1.setLetra('A');
		gr1.setPlazas(100);
		gr1.setCurso("2");
		
		try {
			gestionGrupos.modificarGrupo(gr1);
		} catch (GrupoNoEncontradoException e) {
			fail("Grupo no esta en la base de datos");
		}
	}
	
	@Test
	public void modificarGrupoNoEncontrado() {
		Grupo gr2 = new Grupo();
		gr2.setID(2);
		gr2.setLetra('B');
		gr2.setPlazas(50);
		gr2.setCurso("3");
		
		try {
			gestionGrupos.modificarGrupo(gr2);
			fail("Grupo esta en la base de datos");
		} catch (GrupoNoEncontradoException e) {
			//OK
		}
	}
	
	@Test
	public void eliminarGrupoEncontrado() {
		Grupo gr1 = new Grupo();
		gr1.setID(1);
		gr1.setLetra('A');
		gr1.setPlazas(100);
		gr1.setCurso("2");
		
		try {
			gestionGrupos.eliminarGrupo(gr1);
		} catch (GrupoNoEncontradoException e) {
			fail("Grupo no esta en la base de datos");
		}
	}
	
	@Test
	public void eliminarGrupoNoEncontrado() {
		Grupo gr2 = new Grupo();
		gr2.setID(2);
		gr2.setLetra('B');
		gr2.setPlazas(50);
		gr2.setCurso("3");
		
		try {
			gestionGrupos.eliminarGrupo(gr2);
			fail("Grupo esta en la base de datos");
		} catch (GrupoNoEncontradoException e) {
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

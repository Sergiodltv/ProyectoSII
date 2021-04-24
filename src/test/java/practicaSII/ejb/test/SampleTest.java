package practicaSII.ejb.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

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
import practicaSII.ejb.excetption.ExpedienteEncontradoException;
import practicaSII.ejb.excetption.SecretariaException;

public class SampleTest {
	
	private static final Logger LOG = Logger.getLogger(SampleTest.class.getCanonicalName());

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
	public void testInsertarAlumno() {
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
		
		Expediente exp = new Expediente();
		exp.setNumExpediente(1);
		
		try {
			gestionAlumnos.anadirAlumnoNuevo(a1, exp);
		}catch (AlumnoEncontradoException|ExpedienteEncontradoException e) {
			fail("Lanzó error al insertar");
		}
		
	}
	
	/*
	@Test
	public void testInsertarLote() {
		
		final String productoSalchicha = "Salchicha";
		
		try {
			
			
			Lote lote = new Lote ("ST1", null, BigDecimal.TEN, Date.valueOf("2021-04-11"));
			lote.setLoteIngredientes(new HashMap<Ingrediente, String>());
			
			Producto salchicha = gestionProductos.obtenerProductoEIngredientes(productoSalchicha);
			salchicha.getIngredientes().forEach(ingrediente->{
				lote.getLoteIngredientes().put(ingrediente, "");
			});
			
			try {
				gestionLotes.insertarLote(productoSalchicha, lote);
			} catch (ProductoNoEncontradoException|IngredientesIncorrectosException|LoteExistenteException e) {
				fail("Lanzó excepción al insertar");
			}
		} catch (TrazabilidadException e) {
			throw new RuntimeException(e);
		}
				
		try {
			List<Lote> lotes = gestionLotes.obtenerLotesDeProducto(productoSalchicha);
			assertEquals(1, lotes.size());
			assertEquals(4,lotes.get(0).getLoteIngredientes().size());
			assertEquals("ST1", lotes.get(0).getCodigo());
			assertTrue(BigDecimal.valueOf(10L).compareTo(lotes.get(0).getCantidad())==0);
			assertEquals(Date.valueOf("2021-04-11"), lotes.get(0).getFechaFabricacion());
		} catch (TrazabilidadException e) {
			fail("No debería lanzar excepción");
		}
	}
	
	@Test
	public void testInsertarLoteProductoNoEncontrado() {
		try {
			final String productoSalchicha = "Salchicha";

			Lote lote = new Lote ("ST1", null, BigDecimal.TEN, Date.valueOf("2021-04-11"));
			lote.setLoteIngredientes(new HashMap<Ingrediente, String>());
			
			Producto salchicha = gestionProductos.obtenerProductoEIngredientes(productoSalchicha);
			salchicha.getIngredientes().forEach(ingrediente->{
				lote.getLoteIngredientes().put(ingrediente, "");
			});
			
			try {
				gestionLotes.insertarLote("Salchichón", lote);
				fail("Debe lanzar excepción");
			} catch (ProductoNoEncontradoException e) {
				// OK
			} catch (TrazabilidadException e) {
				fail("Debe lanzar excepción de producto no encontrado"); 
			}
			
		} catch (TrazabilidadException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testInsertarLoteIngredientesIncorrectos() {

		try {
			final String productoSalchicha = "Salchicha";

			Lote lote = new Lote ("ST1", null, BigDecimal.TEN, Date.valueOf("2021-04-11"));
			lote.setLoteIngredientes(new HashMap<Ingrediente, String>());

			Producto salchicha = gestionProductos.obtenerProductoEIngredientes(productoSalchicha);
			lote.getLoteIngredientes().put(
					salchicha.getIngredientes().stream()
					.findAny().get(), 
					"");

			LOG.info("Test: "+lote.getLoteIngredientes().keySet());
			
			try {
				gestionLotes.insertarLote(productoSalchicha, lote);
				fail("Debe lanzar excepción");
			} catch (IngredientesIncorrectosException e) {
				// OK
			} catch (TrazabilidadException e) {
				fail("Debe lanzar excepción de ingredientes incorrectos");
			} 

		} catch (TrazabilidadException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testInsertarLoteExistente() {

		try {
			final String nombreProducto = "Chorizo";

			Lote lote = new Lote ("LT1", null, BigDecimal.TEN, Date.valueOf("2021-04-11"));
			lote.setLoteIngredientes(new HashMap<Ingrediente, String>());

			Producto chorizo = gestionProductos.obtenerProductoEIngredientes(nombreProducto);
			lote.getLoteIngredientes().put(
					chorizo.getIngredientes().stream()
					.findAny().get(), 
					"");

			try {
				gestionLotes.insertarLote(nombreProducto, lote);
				fail("Debe lanzar excepción de lote existente");
			} catch (LoteExistenteException e) {
				// OK
			} catch (TrazabilidadException e) {
				fail("Debe lanzar excepción de lote existente");
			} 

		} catch (TrazabilidadException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testObtenerLotes() {
		try {
			List<Lote> lotes = gestionLotes.obtenerLotesDeProducto("Chorizo");
			assertEquals(2, lotes.size());
		} catch (TrazabilidadException e) {
			fail("No debería lanzar excepción");
		}
	}
	
	@Test
	public void testObtenerLotesProductoNoEncontrado() {
		try {
			List<Lote> lotes = gestionLotes.obtenerLotesDeProducto("Arroz");
			fail("Debería lanzar excepción de producto no encontrado");
		} catch (ProductoNoEncontradoException e) {
			// OK
		} catch (TrazabilidadException e) {
			fail("Debería lanzar excepción de producto no encontrado");
		}
	}
	
	@Test
	public void testActualizarLote() {
		
		final String nombreProducto = "Chorizo";
		
		final long nuevaCantidad = 1234L;
		final String nuevaFecha = "2020-01-01";
		final String nuevoLoteCarne = "ABCD";
		final String nombreIngrediente = "Carne picada";
		String codigoLote1=null;
		
		try {
			
			List<Lote> lotes = gestionLotes.obtenerLotesDeProducto(nombreProducto);
			Lote lote1 = lotes.get(0);
			
			codigoLote1 = lote1.getCodigo(); 
			
			lote1.setCantidad(BigDecimal.valueOf(nuevaCantidad));
			lote1.setFechaFabricacion(Date.valueOf(nuevaFecha));
			Ingrediente carne = lote1.getLoteIngredientes().keySet().stream()
				.filter(ingrediente->{return ingrediente.getNombre().equals(nombreIngrediente);})
				.findAny().get();
			
			lote1.getLoteIngredientes().put(carne, nuevoLoteCarne);
			
			gestionLotes.actualizarLote(nombreProducto, lote1);

		} catch (TrazabilidadException e) {
			fail("Lanzó excepción al actualizar");
		}


		try {
			final String codigoLoteActualizado=codigoLote1;
			Lote loteActualizado = gestionLotes.obtenerLotesDeProducto(nombreProducto).stream()
					.filter(lote->{return lote.getCodigo().equals(codigoLoteActualizado);})
					.findAny().get();
			
			assertTrue(BigDecimal.valueOf(nuevaCantidad).compareTo(loteActualizado.getCantidad())==0);
			assertEquals(Date.valueOf(nuevaFecha), loteActualizado.getFechaFabricacion());
			Ingrediente carne = loteActualizado.getLoteIngredientes().keySet().stream()
					.filter(ingrediente->{return ingrediente.getNombre().equals(nombreIngrediente);})
					.findAny().get();
			
			assertEquals(nuevoLoteCarne, loteActualizado.getLoteIngredientes().get(carne));
		} catch (TrazabilidadException e) {
			fail("No debería lanzar excepción");
		}
	}
	
	@Test
	public void testActualizarLoteProductoNoEncontrado() {
		final String nombreProducto = "Chorizo";
		final String otroProducto = "Arroz";
		
		final long nuevaCantidad = 1234L;
		final String nuevaFecha = "2020-01-01";
		final String nuevoLoteCarne = "ABCD";
		final String nombreIngrediente = "Carne picada";
		
		try {
			
			List<Lote> lotes = gestionLotes.obtenerLotesDeProducto(nombreProducto);
			Lote lote1 = lotes.get(0);
			
			lote1.setCantidad(BigDecimal.valueOf(nuevaCantidad));
			lote1.setFechaFabricacion(Date.valueOf(nuevaFecha));
			Ingrediente carne = lote1.getLoteIngredientes().keySet().stream()
				.filter(ingrediente->{return ingrediente.getNombre().equals(nombreIngrediente);})
				.findAny().get();
			
			lote1.getLoteIngredientes().put(carne, nuevoLoteCarne);
			
			gestionLotes.actualizarLote(otroProducto, lote1);
			fail("Debería lanzar excepción de producto no encontrado");
		} catch (ProductoNoEncontradoException e) {
			// OK
		} catch (TrazabilidadException e) {
			fail("Debería lanzar excepción de producto no encontrado");
		}
	}
	
	@Test
	public void testActualizarLoteoNoEncontrado() {
		final String nombreProducto = "Chorizo";
		final String nuevoCodigoLote = "OtroCodigo";
		
		try {
			List<Lote> lotes = gestionLotes.obtenerLotesDeProducto(nombreProducto);
			Lote lote1 = lotes.get(0);
			lote1.setCodigo(nuevoCodigoLote);
			gestionLotes.actualizarLote(nombreProducto, lote1);
			fail("Debería lanzar excepción de lote no encontrado");
		} catch (LoteNoEncontradoException e) {
			// OK
		} catch (TrazabilidadException e) {
			fail("Debería lanzar excepción de lote no encontrado");
		}
	}
	
	@Test
	public void testActualizarLoteIngredientesIncorrectos() {
		final String nombreProducto = "Chorizo";
		final String nombreIngrediente = "Carne picada";
		
		try {
			
			List<Lote> lotes = gestionLotes.obtenerLotesDeProducto(nombreProducto);
			Lote lote1 = lotes.get(0);
			
			Ingrediente carne = lote1.getLoteIngredientes().keySet().stream()
				.filter(ingrediente->{return ingrediente.getNombre().equals(nombreIngrediente);})
				.findAny().get();
			
			lote1.getLoteIngredientes().remove(carne);
			
			gestionLotes.actualizarLote(nombreProducto, lote1);
			fail("Debería lanzar excepción de ingredientes incorrectos");
		} catch (IngredientesIncorrectosException e) {
			// OK
		} catch (TrazabilidadException e) {
			fail("Debería lanzar excepción de ingredientes incorrectos");
		} 
	}
	
	@Test
	public void testEliminarLote() {
		try {
			final String nombreProducto = "Chorizo";
			List<Lote>  lotes = gestionLotes.obtenerLotesDeProducto(nombreProducto);
			
			Lote lote1 = lotes.get(0);
			
			gestionLotes.eliminarLote(nombreProducto, lote1);
			
			lotes = gestionLotes.obtenerLotesDeProducto(nombreProducto);
			assertEquals(1, lotes.size());
			
		} catch (TrazabilidadException e) {
			fail("No debería lanzarse excepción");
		}
	}
	
	@Test
	public void testEliminarLoteProductoNoEncontrado() {
		try {
			final String nombreProducto = "Chorizo";
			final String otroProducto = "Arroz";
			
			List<Lote>  lotes = gestionLotes.obtenerLotesDeProducto(nombreProducto);
			
			Lote lote1 = lotes.get(0);

			gestionLotes.eliminarLote(otroProducto, lote1);
			fail("Debería lanzar la excepción de producto no encontrado");
		} catch (ProductoNoEncontradoException e) {
			// OK
		} catch (TrazabilidadException e) {
			fail("Debería lanzar la excepción de producto no encontrado");
		}
	}
	
	@Test
	public void testEliminarLoteNoEncontrado() {
		try {
			final String nombreProducto = "Chorizo";
			
			List<Lote>  lotes = gestionLotes.obtenerLotesDeProducto(nombreProducto);
			Lote lote1 = lotes.get(0);
			
			lote1.setCodigo("Otro");

			gestionLotes.eliminarLote(nombreProducto, lote1);
			fail("Debería lanzar la excepción de lote no encontrado");
		} catch (LoteNoEncontradoException e) {
			// OK
		} catch (TrazabilidadException e) {
			fail("Debería lanzar la excepción de lote no encontrado");
		}
	}
	
	@Test
	public void testEliminarTodosLotes() {
		try {
			final String nombreProducto = "Chorizo";
			gestionLotes.eliminarTodosLotes(nombreProducto);
			
			List<Lote> lotes = gestionLotes.obtenerLotesDeProducto(nombreProducto);
			assertEquals(0, lotes.size());
			
		} catch (TrazabilidadException e) {
			fail("No debería lanzarse excepción");
		}
	}
	
	@Test
	public void testEliminarTodosLotesProductoNoEncontrado() {
		try {
			final String nombreProducto = "Arroz";
			gestionLotes.eliminarTodosLotes(nombreProducto);
			
			fail("Debería lanzar la excepción de producto no encontrado");
		} catch (ProductoNoEncontradoException e) {
			// OK
		} catch(TrazabilidadException e) {
			fail("Debería lanzar la excepción de producto no encontrado");
		}
	}
	*/
	@AfterClass
	public static void tearDownClass() {
		if (ejbContainer != null) {
			ejbContainer.close();
		}
	}

}

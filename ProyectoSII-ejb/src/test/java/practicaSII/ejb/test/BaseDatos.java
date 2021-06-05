package practicaSII.ejb.test;

import java.math.BigDecimal;

import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import practicaSII.Alumno;
import practicaSII.Asignatura;
import practicaSII.Centro;
import practicaSII.Clase;
import practicaSII.ClasePK;
import practicaSII.Expediente;
import practicaSII.Grupo;
import practicaSII.Matricula;
import practicaSII.MatriculaPK;
import practicaSII.Optativa;
import practicaSII.Pas;
import practicaSII.Titulacion;



public class BaseDatos {
	public static void inicializaBaseDatos(String nombreUnidadPersistencia) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombreUnidadPersistencia);
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
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
		
		em.persist(a1);
		
		Expediente exp1 = new Expediente();
		exp1.setNumExpediente(1);
		
		em.persist(exp1);
		
		Titulacion t1 = new Titulacion();
		t1.setCodigo(1);
		t1.setCreditos(60);
		t1.setNombre("GII");
		
		em.persist(t1);
		
		Asignatura as1 = new Asignatura();
		as1.setReferencia(1);
		as1.setCodigo(123);
		as1.setCreditosTotales(6);
		as1.setCreditosPr(3);
		as1.setCreditosTeo(3);
		as1.setOfertada("Si");
		as1.setNombre("Internet");
		as1.setCurso(2);
		as1.setPlazas("250");
		as1.setDuracion("35");
		as1.setIngles("Si");
		as1.setAs_titul(t1);
		
		em.persist(as1);
		
		Optativa opt1 = new Optativa();
		opt1.setAsignatura(as1);
		opt1.setMencion("Computacion");
		opt1.setPlazas(250);
		
		em.persist(opt1);
		
		Centro c1 = new Centro();
		c1.setID("1");
		c1.setDireccion("Avenida Plutarco");
		c1.setNombre("ETSII");
		c1.setTelefonoConsergeria(123456789);
		
		em.persist(c1);
		
		Date d = new Date();
		
		ClasePK clpk1 = new ClasePK();
		clpk1.setGrupoFk(1);
		clpk1.setDia(d);
		clpk1.setHoraInicio("12");
		
		em.persist(clpk1);
		
		Clase cl1 = new Clase();
		cl1.setHoraFin(d);
		cl1.setId(clpk1);
		
		em.persist(cl1);
		
		Grupo gr1 = new Grupo();
		gr1.setID(1);
		gr1.setLetra('A');
		gr1.setPlazas(100);
		gr1.setCurso("2");
		
		em.persist(gr1);
		
		MatriculaPK mpk1 = new MatriculaPK();
		mpk1.setCursoAcademico("2");
		mpk1.setExp_fk(1);
		
		em.persist(mpk1);
		
		Matricula m1 = new Matricula();
		m1.setId(mpk1);
		m1.setEstado(true);
		m1.setFechaMatricula("12/10/21");
		m1.setNuevoIngreso(false);
		m1.setNumArchivo(1);
		m1.setTurnoPreferido("Tarde");
		
		em.persist(m1);
		
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
		
		em.persist(p1);
		
		/*
		Ingrediente carne = new Ingrediente ("Carne picada");
		Ingrediente pimienta = new Ingrediente ("Pimienta");
		Ingrediente especias = new Ingrediente("Especias de hamburguesa");
		Ingrediente pimenton = new Ingrediente ("Pimentón");
		Ingrediente sal = new Ingrediente ("Sal");
		Ingrediente azucar = new Ingrediente ("Azúcar");
		Ingrediente perejil = new Ingrediente ("Perejil");
		
		for (Ingrediente ingrediente: new Ingrediente [] {carne, pimienta, especias, pimenton, sal, azucar, perejil}) {
			em.persist(ingrediente);
		}
		
		Producto chorizo = new Producto ("Chorizo");
		Producto salchicha = new Producto ("Salchicha");
		Producto hamburguesa = new Producto ("Hamburguesa");
		
		chorizo.setIngredientes(Stream.of(carne, pimienta, pimenton, sal)
				.collect(Collectors.toSet()));
		
		salchicha.setIngredientes(Stream.of(carne, sal, azucar, perejil)
				.collect(Collectors.toSet()));
		
		hamburguesa.setIngredientes(Stream.of(carne, especias, sal, azucar)
				.collect(Collectors.toSet()));
		
		for (Producto producto: new Producto [] {chorizo, salchicha, hamburguesa}) {
			em.persist(producto);
		}
		
		Lote lote = new Lote ("LT1", chorizo, BigDecimal.TEN, Date.valueOf("2021-04-11"));
		lote.setLoteIngredientes(new HashMap<Ingrediente, String>());
		lote.getLoteIngredientes().put(carne, "C1");
		lote.getLoteIngredientes().put(pimienta, "Pi1");
		lote.getLoteIngredientes().put(pimenton, "PM1");
		lote.getLoteIngredientes().put(sal, "S1");
		
		em.persist(lote);
		
		lote = new Lote ("LT2", chorizo, BigDecimal.valueOf(25L), Date.valueOf("2021-04-12"));
		lote.setLoteIngredientes(new HashMap<Ingrediente, String>());
		lote.getLoteIngredientes().put(carne, "C2");
		lote.getLoteIngredientes().put(pimienta, "Pi2");
		lote.getLoteIngredientes().put(pimenton, "PM2");
		lote.getLoteIngredientes().put(sal, "S2");
		
		em.persist(lote);
		*/
		
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}
}

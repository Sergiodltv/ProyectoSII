package practicaSII.ejb.clasesejb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import practicaSII.Alumno;
import practicaSII.Asig_Matricula;
import practicaSII.Expediente;
import practicaSII.Matricula;
import practicaSII.MatriculaPK;
import practicaSII.ejb.excetption.AlumnoEncontradoException;
import practicaSII.ejb.excetption.ExpedienteEncontradoException;
import practicaSII.ejb.excetption.MatriculaEncontradaException;

public class ImportarAlumnosEJB {
	private String fichero;
	private AlumnosEJB alumEjb;
	private MatriculaEJB matEjb;
	private ExpedienteEJB expEjb;
	
	
	public ImportarAlumnosEJB(String fichero) {
		this.fichero = fichero;
		alumEjb = new AlumnosEJB();
		matEjb = new MatriculaEJB();
		expEjb = new ExpedienteEJB();
	}
	
	public void insertarDatos() {
		try {
			Reader r = new FileReader(fichero);
			Iterable<CSVRecord> l = CSVFormat.RFC4180.parse(r);
			Iterator<CSVRecord> lines = l.iterator();
			String curso = lines.next().get(1);
			lines.next();
			String e = lines.next().get(1);
			boolean estado = e.equalsIgnoreCase("ACTIVA");
			
			while(lines.hasNext()) {
				CSVRecord line = lines.next();
				String dni = line.get(0);
				String nombre = line.get(1);
				String ap1 = line.get(2);
				String ap2 = line.get(3);
				Integer nExp = Integer.parseInt(line.get(4));
				Integer nArchivo = Integer.parseInt(line.get(5));
				String emailInst = line.get(6);
				String emailPers = line.get(7);
				Integer telefono = Integer.parseInt(line.get(8));
				Integer movil = Integer.parseInt(line.get(9));
				String direccion = line.get(10);
				String localidad = line.get(11);
				String provincia = line.get(12);
				Integer cp = Integer.parseInt(line.get(13));
				String fechaMat = line.get(14);
				String turnoPref = line.get(15);
				String grAsignados = line.get(16);
				String nMedia = line.get(17);
				Integer crSuperados = Integer.parseInt(line.get(18));
				Integer crFb = Integer.parseInt(line.get(19));
				Integer crOb = Integer.parseInt(line.get(20));
				Integer crOp = Integer.parseInt(line.get(21));
				Integer crCf = Integer.parseInt(line.get(22));
				Integer crPe = Integer.parseInt(line.get(23));
				Integer crTf = Integer.parseInt(line.get(24));
				
				Alumno alumno = new Alumno(dni,nombre,ap1,ap2,emailInst,emailPers,telefono,movil,direccion,localidad,provincia,cp);
				Expediente exp = new Expediente(nExp, nMedia, crSuperados, crFb, crOb, crOp, crCf, crPe, crTf);
				
				Matricula matricula = new Matricula(new MatriculaPK(curso,nExp),estado,fechaMat,nArchivo,turnoPref,exp,grAsignados);
				alumno.anyadirExpediente(exp);
				exp.anyadirMatricula(matricula);
				
				alumEjb.anadirAlumnoMatr(alumno);
				expEjb.anyadirExpediente(exp);
				matEjb.anadirMatricula(matricula);
				
			}
		}catch(FileNotFoundException e) {
			
		}catch (IOException e) {
			
		}catch (AlumnoEncontradoException e) {
			
		}catch (ExpedienteEncontradoException e) {
			
		}catch (MatriculaEncontradaException e) {
			
		}
	}
	
	public static void main (String[] args) {
		ImportarAlumnosEJB t = new ImportarAlumnosEJB("/home/alumno/Descargas/Titulacion.xlsx");
		t.insertarDatos();
	}
	
}

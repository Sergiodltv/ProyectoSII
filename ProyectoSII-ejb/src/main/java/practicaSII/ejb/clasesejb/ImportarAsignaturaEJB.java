package practicaSII.ejb.clasesejb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import practicaSII.Asignatura;
import practicaSII.Optativa;
import practicaSII.Titulacion;
import practicaSII.ejb.excetption.AsignaturaEncontradaException;
import practicaSII.ejb.excetption.AsignaturaNoEncontradaException;
import practicaSII.ejb.excetption.OptativaNoEncontradaException;
import practicaSII.ejb.excetption.TitulacionNoEncontradaException;

public class ImportarAsignaturaEJB {
	private String fichero;
	AsignaturaEJB asigEjb;
	TitulacionEJB titEjb;
	
	public ImportarAsignaturaEJB (String fichero){
		this.fichero = fichero;
		asigEjb = new AsignaturaEJB();
		titEjb = new TitulacionEJB();
	}
	
	public void insertarDatos() {
		insertarAsigGrados();
		insertarOptativas();
	}
	
	private void insertarOptativas() {
		try {
			FileInputStream excel = new FileInputStream(new File(fichero));
			Workbook wk = new XSSFWorkbook(excel);
			for (int i = 0; i < 2; i++) {
				Sheet sheet = wk.getSheetAt(i);
				Iterator<Row> it = sheet.iterator();
				it.next();
				while(it.hasNext()) {
					Row current = it.next();
					Integer referencia = new Integer ((int)current.getCell(0).getNumericCellValue());
					Integer plazas = new Integer ((int)current.getCell(1).getNumericCellValue());
					String mencion = current.getCell(2).getStringCellValue();
					if (mencion.equals("")) {mencion = "Informatica";}
					Asignatura asignatura = asigEjb.obtenerAsignaturaConCodigo(referencia);
					Optativa optativa = new Optativa(asignatura, plazas, mencion);
					asigEjb.anyadirOptativa(optativa);
				}	
			}
			
			wk.close();
			
		}catch (FileNotFoundException e) {
			
		}catch (IOException e) {
			
		}catch (AsignaturaNoEncontradaException e) {
			
		}catch (OptativaNoEncontradaException e) {
			
		}
	}
	
	private void insertarAsigGrados(){
		try {
			FileInputStream excel = new FileInputStream(new File(fichero));
			Workbook wk = new XSSFWorkbook(excel);
			for (int i = 2; i <= 6; i++) {
				Sheet sheet = wk.getSheetAt(i);
				Iterator<Row> it = sheet.iterator();
				it.next();
			
				while(it.hasNext()) {
					Row current = it.next();
					Integer titulacion = new Integer ((int)current.getCell(0).getNumericCellValue());
					String ofertada = current.getCell(1).getStringCellValue();
					Integer cod = new Integer ((int)current.getCell(2).getNumericCellValue());
					Integer referencia = new Integer ((int)current.getCell(3).getNumericCellValue());
					String nombre = current.getCell(4).getStringCellValue();
					Integer curso = new Integer ((int)current.getCell(5).getNumericCellValue());
					Integer credTeo = new Integer ((int)current.getCell(6).getNumericCellValue());
					Integer credPr = new Integer ((int)current.getCell(7).getNumericCellValue());
					Integer totalcred = new Integer ((int)current.getCell(8).getNumericCellValue());
					String duracion = current.getCell(9).getStringCellValue();
					String plazas = current.getCell(10).getStringCellValue();
					String ingles = current.getCell(11).getStringCellValue();
				
					Asignatura asignatura = new Asignatura(referencia, cod, totalcred, credPr,credTeo,ofertada,nombre,curso,duracion,plazas,ingles);	
					Titulacion t = titEjb.obtenerTitulacionConCodigo(titulacion);
					asignatura.setTitulacion(t);
					asigEjb.anadirAsingatura(asignatura);
				}
			}
		
		wk.close();
			
		}catch (FileNotFoundException e) {
		
		}catch (IOException e) {
		
		}catch (AsignaturaEncontradaException e) {
	
		}catch (TitulacionNoEncontradaException e) {
			
		}
	}

}

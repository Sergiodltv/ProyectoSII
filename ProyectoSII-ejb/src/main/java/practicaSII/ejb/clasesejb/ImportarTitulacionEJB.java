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

import practicaSII.Titulacion;
import practicaSII.ejb.excetption.TitulacionEncontradaException;

public class ImportarTitulacionEJB {
	private String fichero;
	TitulacionEJB ejb;
	
	public ImportarTitulacionEJB(String fichero) {
		this.fichero = fichero;
		ejb = new TitulacionEJB();
	}
	
	public void insertarDatos(){
		try {
			FileInputStream excel = new FileInputStream(new File(fichero));
			Workbook wk = new XSSFWorkbook(excel);
			Sheet sheet = wk.getSheetAt(0);
			Iterator<Row> it = sheet.iterator();
			it.next();
			while(it.hasNext()) {
				Row current = it.next();
				Integer codigo = new Integer((int)current.getCell(0).getNumericCellValue());
				String nombre = current.getCell(1).getStringCellValue();
				Integer creditos = new Integer((int)current.getCell(2).getNumericCellValue());
				Titulacion tl = new Titulacion(codigo, nombre, creditos);
				ejb.anadirTitulacion(tl);
			}
			
			wk.close();
			
		}catch (FileNotFoundException e) {
			
		}catch (IOException e) {
			
		}catch (TitulacionEncontradaException e) {
		
		}
	}
	public static void main (String[] args) {
		ImportarTitulacionEJB t = new ImportarTitulacionEJB("/home/alumno/Descargas/ParaAlumnos/Titulacion.xlsx");
		t.insertarDatos();
	}
}

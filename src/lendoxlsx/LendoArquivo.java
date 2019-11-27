/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lendoxlsx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public  class LendoArquivo { 
 

    public static Vector<Vector<Object>> ler(String nomeDoArquivo) {
        
        Vector<Vector<Object>> linhas = new Vector<>();
    
        
        
        FileInputStream fisPlanilha = null;

        try {
            File file = new File(nomeDoArquivo);
            fisPlanilha = new FileInputStream(file);

            //cria um workbook = planilha toda com todas as abas
            XSSFWorkbook workbook = new XSSFWorkbook(fisPlanilha);

            //recuperamos apenas a primeira aba ou primeira planilha
            XSSFSheet sheet = workbook.getSheetAt(0);

            //retorna todas as linhas da planilha 0 (aba 1)
            Iterator<Row> rowIterator = sheet.iterator();

            //varre todas as linhas da planilha 0
            while (rowIterator.hasNext()) {

                //recebe cada linha da planilha
                Row row = rowIterator.next();
               
                  
                //pegamos todas as celulas desta linha
                Iterator<Cell> cellIterator = row.iterator();

                //varremos todas as celulas da linha atual
                
                   Vector<Object> celulas = new Vector<>();
                while (cellIterator.hasNext()) {
                    

                    //criamos uma celula
                    Cell cell = cellIterator.next();


                    switch (cell.getCellType()) {

                        case Cell.CELL_TYPE_STRING:
                            celulas.add(cell.getStringCellValue());
                            break;

                        case Cell.CELL_TYPE_NUMERIC:
                            celulas.add(cell.getNumericCellValue());
                            break;

                        case Cell.CELL_TYPE_FORMULA:
                             celulas.add(cell.getCellFormula());
                    }
                    
                }
               linhas.add(celulas);
                
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LendoArquivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LendoArquivo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fisPlanilha.close();
            } catch (IOException ex) {
                Logger.getLogger(LendoArquivo.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return linhas;
    }
}

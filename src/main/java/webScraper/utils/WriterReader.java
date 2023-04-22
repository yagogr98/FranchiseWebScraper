package webScraper.utils;

import webScraper.beans.Franquicia;
import webScraper.error.FranquiciaException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriterReader {

    private static final String SEPARADOR_FRANQUICIAS = ";";
    private static final String SEPARADOR_DATOS = ",";

    /**
     *  escribe excel de salida
     * @param franquicias
     * @throws FranquiciaException
     */
    public static void writeExcel(List<Franquicia> franquicias) throws FranquiciaException {
        Workbook libro = new XSSFWorkbook();
        final String nombreArchivo = "Franquicias.xlsx";
        Sheet hoja = libro.createSheet("Resultados");
        crearCabecera(hoja);
        rellenarFilas(hoja,franquicias);

        File directory = new File(".");
        String path = directory.getAbsolutePath();
        String pathSalida = path.substring(0, path.length() - 1) + nombreArchivo;
        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(pathSalida);
            libro.write(outputStream);
            libro.close();
            System.out.println("Libro guardado correctamente");
        } catch (FileNotFoundException ex) {
            throw new FranquiciaException(ex.getMessage());
        } catch (IOException ex) {
            throw new FranquiciaException(ex.getMessage());
        }
    }

    /**
     * Crea cabecera de excel
     * @param hoja
     */
    private static void crearCabecera(Sheet hoja) {
        //Cabecera
        Row primeraFila = hoja.createRow(0);
        Cell nombre = primeraFila.createCell(0);
        nombre.setCellValue("Nombre Franquicia");

        Cell enlacesTwitter = primeraFila.createCell(1);
        enlacesTwitter.setCellValue("Número de enlaces a Twitter");
        Cell enlacesFacebook = primeraFila.createCell(2);
        enlacesFacebook.setCellValue("Número de enlaces a Facebook");
        Cell enlacesInstagram = primeraFila.createCell(3);
        enlacesInstagram.setCellValue("Número de enlaces a Instagram");
    }

    /**
     * rellena fila con franquicias
     * @param hoja
     * @param franquicias
     */
    private static void rellenarFilas(Sheet hoja, List<Franquicia> franquicias) {
        int num_fila = 0;
        for(Franquicia f : franquicias){
            num_fila++;
            Row fila = hoja.createRow(num_fila);
            Cell nombre = fila.createCell(0);
            nombre.setCellValue(f.getNombre());
            Cell enlacesTwitter = fila.createCell(1);
            enlacesTwitter.setCellValue(f.getEnlacesTwitter());
            Cell enlacesFacebook = fila.createCell(2);
            enlacesFacebook.setCellValue(f.getEnlacesFacebook());
            Cell enlacesInstagram = fila.createCell(3);
            enlacesInstagram.setCellValue(f.getEnlacesInstagram());

        }
    }

    /**
     * Lee ficheros CSV y los transforma en listado de franquicias
     * El CSV debe estar compuesto por "nombre Franquicia",
     * "enlace pagina de inicio",
     * "enlace pagina de contacto" sin comillas
     * Todas las franquicias iran separadas por ";"
     *s
     * @param path
     */
    public static List<Franquicia> readCSV(String path) throws FranquiciaException {
        List<Franquicia> franquiciasOut = new ArrayList<Franquicia>();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(path));
            //Se recorren todas las lineas del CSV
            String line = br.readLine();
            while (null != line) {
                //Se separan todas las franquicias
                String[] franquicias = line.split(SEPARADOR_FRANQUICIAS);

                //Se recorren todas las franquicias
                for (String franquiciaCampos : franquicias) {
                    //Se separan datos de franquicia
                    String[] datosFranquicias = franquiciaCampos.split(SEPARADOR_DATOS);
                    if (datosFranquicias.length == 3) {
                        //Correcto
                        Franquicia franquiciaTemporal = new Franquicia(datosFranquicias[0], datosFranquicias[1], datosFranquicias[2]);
                        franquiciasOut.add(franquiciaTemporal);
                    }
                }
                line = br.readLine();
            }

        } catch (Exception e) {
            throw new FranquiciaException(e.getMessage());
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    throw new FranquiciaException(e.getMessage());
                }
            }
        }
        return franquiciasOut;
    }
}

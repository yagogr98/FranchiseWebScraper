package webScraper.utils;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import webScraper.beans.Franquicia;
import webScraper.error.FranquiciaException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriterReader {

    private static final String SEPARADOR_FRANQUICIAS = ";";
    private static final String SEPARADOR_DATOS = ",";
    private static Workbook libro;

    /**
     * escribe excel de salida
     *
     * @param franquicias
     * @throws FranquiciaException
     */
    public static void writeExcel(List<Franquicia> franquicias) throws FranquiciaException {
        libro = new XSSFWorkbook();
        final String nombreArchivo = "Franquicias.xlsx";
        Sheet hoja = libro.createSheet("Resultados");
        crearCabecera(hoja);
        rellenarFilas(hoja, franquicias);

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
     *
     * @param hoja
     */
    private static void crearCabecera(Sheet hoja) {
        //Estilo
        CellStyle estilo = estiloCabecera();

        //Cabecera
        Row primeraFila = hoja.createRow(0);
        Cell nombre = primeraFila.createCell(0);
        nombre.setCellValue("Nombre Franquicia");
        nombre.setCellStyle(estilo);
        hoja.autoSizeColumn(0);

        Cell enlacesTwitter = primeraFila.createCell(1);
        enlacesTwitter.setCellValue("Número de enlaces a Twitter");
        enlacesTwitter.setCellStyle(estilo);
        hoja.autoSizeColumn(1);

        Cell enlacesFacebook = primeraFila.createCell(2);
        enlacesFacebook.setCellValue("Número de enlaces a Facebook");
        enlacesFacebook.setCellStyle(estilo);
        hoja.autoSizeColumn(2);

        Cell enlacesInstagram = primeraFila.createCell(3);
        enlacesInstagram.setCellValue("Número de enlaces a Instagram");
        enlacesInstagram.setCellStyle(estilo);
        hoja.autoSizeColumn(3);
    }

    private static CellStyle estiloCabecera() {
        //Estilo
        CellStyle estilo = libro.createCellStyle();
        estilo.setAlignment(HorizontalAlignment.CENTER);
        Font font = libro.createFont();

        font.setBold(true);
        font.setFontHeightInPoints((short) 15);
        font.setColor(IndexedColors.WHITE.index);
        estilo.setFont(font);
        estilo.setAlignment(HorizontalAlignment.CENTER);
        estilo.setVerticalAlignment(VerticalAlignment.CENTER);
        estilo.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.index);
        estilo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return estilo;
    }

    /**
     * rellena fila con franquicias
     *
     * @param hoja
     * @param franquicias
     */
    private static void rellenarFilas(Sheet hoja, List<Franquicia> franquicias) {
        int num_fila = 0;
        CellStyle estilo = estiloCuerpo();

        for (Franquicia f : franquicias) {
            num_fila++;
            Row fila = hoja.createRow(num_fila);
            Cell nombre = fila.createCell(0);
            nombre.setCellStyle(estilo);
            nombre.setCellValue(f.getNombre());

            Cell enlacesTwitter = fila.createCell(1);
            enlacesTwitter.setCellStyle(estilo);
            enlacesTwitter.setCellValue(f.getEnlacesTwitter());

            Cell enlacesFacebook = fila.createCell(2);
            enlacesFacebook.setCellStyle(estilo);
            enlacesFacebook.setCellValue(f.getEnlacesFacebook());

            Cell enlacesInstagram = fila.createCell(3);
            enlacesInstagram.setCellValue(f.getEnlacesInstagram());
            enlacesInstagram.setCellStyle(estilo);

        }
        hoja.autoSizeColumn(0);
        hoja.autoSizeColumn(1);
        hoja.autoSizeColumn(2);
        hoja.autoSizeColumn(3);

    }

    private static CellStyle estiloCuerpo() {
        //Estilo
        CellStyle estilo = libro.createCellStyle();
        estilo.setAlignment(HorizontalAlignment.LEFT);
        Font font = libro.createFont();

        font.setFontHeightInPoints((short) 13);
        estilo.setFont(font);
        estilo.setVerticalAlignment(VerticalAlignment.CENTER);

        estilo.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
        estilo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return estilo;
    }

    /**
     * Lee ficheros CSV y los transforma en listado de franquicias
     * El CSV debe estar compuesto por "nombre Franquicia",
     * "enlace pagina de inicio",
     * "enlace pagina de contacto" sin comillas
     * Todas las franquicias iran separadas por ";"
     * s
     *
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
                        if (Validator.validateURL(datosFranquicias[1]) || Validator.validateURL(datosFranquicias[2])) {
                            //Correcto
                            Franquicia franquiciaTemporal = new Franquicia(datosFranquicias[0], datosFranquicias[1], datosFranquicias[2]);
                            franquiciasOut.add(franquiciaTemporal);
                        }
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

    /**
     * Escribe listado de franquicias en un CSV
     * El CSV  estará compuesto por "nombre Franquicia",
     * "enlace pagina de inicio",
     * "enlace pagina de contacto" sin comillas
     * Todas las franquicias iran separadas por ";"
     * s
     *
     * @param franquicias lista de franquicias
     */
    public static void writeCSV(List<Franquicia> franquicias) throws FranquiciaException {
        //Iniciamos CSV
        File csvFile = new File("franquicias.csv");
        try {
            FileWriter fileWriter = new FileWriter(csvFile);


            for (Franquicia franquicia : franquicias) {
                //Creamos linea con los datos de la franquicia
                StringBuilder linea = new StringBuilder();
                linea.append(franquicia.getNombre() + SEPARADOR_DATOS + franquicia.getEnlaceInicio()
                        + SEPARADOR_DATOS + franquicia.getEnlaceContacto() + SEPARADOR_FRANQUICIAS);
                //escribimos linea
                fileWriter.write(linea.toString());
            }

            fileWriter.close();
        } catch (IOException e) {
            throw new FranquiciaException(e.getMessage());
        }

    }
}

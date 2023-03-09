package webScraper.utils;

import webScraper.beans.Franquicia;
import webScraper.error.FranquiciaExcepction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvWriterReader {

    private static final String SEPARADOR_FRANQUICIAS = ";";
    private static final String SEPARADOR_DATOS = ",";

    /**
     * Lee ficheros CSV y los transforma en listado de franquicias
     * El CSV debe estar compuesto por "nombre Franquicia",
     * "enlace pagina de inicio",
     * "enlace pagina de contacto" sin comillas
     * Todas las franquicias iran separadas por ";"
     *s
     * @param path
     */
    public static List<Franquicia> readCSV(String path) throws FranquiciaExcepction {
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
            throw new FranquiciaExcepction(e.getMessage());
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    throw new FranquiciaExcepction(e.getMessage());
                }
            }
        }
        return franquiciasOut;
    }
}

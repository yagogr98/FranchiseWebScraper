package webScraper.app;

import webScraper.error.FranquiciaException;
import webScraper.negocio.Scraper;
import webScraper.utils.Constants;


import java.util.Scanner;
import java.util.logging.*;

/**
 *
 */
public class WebScraperMain {
    private final static Logger LOGGER = Logger.getGlobal();
    public static void main(String[] args) {
        initializeLog();
        Scraper scraper = new Scraper();
        try {
            System.out.println(mensajeBienvenida());
            String param1;
            Scanner entradaEscaner = new Scanner (System.in);
            param1 = entradaEscaner.nextLine ();
            switch (param1) {
                case Constants.GENERACION_TOTAL:
                    scraper.operar(Constants.GENERACION_TOTAL, "");
                    break;
                case Constants.GENERACION_LISTA:
                    scraper.operar(Constants.GENERACION_LISTA, "");
                    break;
                case Constants.LECTURA_LISTA:
                    System.out.println("Introduzca dirección al archivo .csv");

                    entradaEscaner = new Scanner(System.in);
                    String param2 = entradaEscaner.nextLine();
                    scraper.operar(Constants.LECTURA_LISTA, param2);
                    break;
                default:
                    System.out.println("Entrada no valida");
                    break;
            }

        } catch (FranquiciaException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    private static String mensajeBienvenida(){
        return "--- FranchiseWebScraper ---" +
                System.getProperty("line.separator") +
                System.getProperty("line.separator") +
                "Introduzca 1 para leer lista de franquicias del directorio de la AEF " +
                System.getProperty("line.separator") +
                "analizar en busca de enlaces y por ultimo generar un archivo .xlsx con los datos." +
                System.getProperty("line.separator") +
                System.getProperty("line.separator") +
                "Introduzca 2 para leer lista de franquicias del directorio de la AEF " +
                System.getProperty("line.separator") +
                "y posteriormente generar un archivo .csv con ella (No se analizan las franquicias)" +
                System.getProperty("line.separator") +
                System.getProperty("line.separator") +
                "Introduzca 3 para leer un archivo .csv porporcionado por parámetro con una lista " +
                System.getProperty("line.separator") +
                "de franquicias para ser analizado y posteriormente generar un .xlsx con los datos" +
                System.getProperty("line.separator");

    }
    /**
     * Inicializa el Log
     */
    private static void initializeLog() {
        Handler consoleHandler = new ConsoleHandler();

        LOGGER.setUseParentHandlers(false);
        Handler[] handlers = LOGGER.getHandlers();
        for(Handler handler : handlers)
        {
            if(handler.getClass() == ConsoleHandler.class)
                LOGGER.removeHandler(handler);
        }

        LOGGER.addHandler(consoleHandler);
        consoleHandler.setLevel(Level.ALL);
    }

}
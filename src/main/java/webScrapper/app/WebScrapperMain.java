package webScrapper.app;

import webScrapper.negocio.Scrapper;

/**
 *
 */
public class WebScrapperMain {
    public static void main(String[] args) {
        Scrapper scrapper = new Scrapper();
        scrapper.sacaDatos();
    }

}
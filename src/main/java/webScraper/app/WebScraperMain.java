package webScraper.app;

import webScraper.negocio.Scraper;

/**
 *
 */
public class WebScraperMain {
    public static void main(String[] args) {
            Scraper scraper = new Scraper();
            scraper.operar("franquicias.csv");
    }

}
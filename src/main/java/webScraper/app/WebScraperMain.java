package webScraper.app;

import webScraper.negocio.Scraper;

import java.io.IOException;

/**
 *
 */
public class WebScraperMain {
    public static void main(String[] args) {
        Scraper scraper = new Scraper();
        try {
            scraper.operar();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
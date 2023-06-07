package webScraper.app;

import webScraper.error.FranquiciaException;
import webScraper.negocio.Scraper;

import java.util.logging.Logger;

/**
 *
 */
public class WebScraperMain {
    public static void main(String[] args) {
            Scraper scraper = new Scraper();
        try {
            scraper.operar("");
        } catch (FranquiciaException e) {
            Logger.getGlobal().severe(e.getMessage());
        }
    }

}
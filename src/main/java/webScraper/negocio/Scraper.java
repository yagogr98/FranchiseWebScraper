package webScraper.negocio;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import webScraper.beans.Franquicia;
import webScraper.error.FranquiciaExcepction;
import webScraper.utils.CsvWriterReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Scraper {

    WebClient cliente;
    List<HtmlPage> paginas;

    List<Franquicia> listaFranquicias;

    /**
     * Metodo operar
     */
    public void operar(String path){
        try {
            inicializarFranquicias(path);
            inicializarCliente();
            cargaPaginas();
            obtenerEnlaces();
            cierraCliente();
        }catch (FranquiciaExcepction e) {
            throw new RuntimeException(e);
        }
    }

    private void inicializarFranquicias(String path) throws FranquiciaExcepction {
        setListaFranquicias(CsvWriterReader.readCSV(path));
    }

    /**
     *
     */
    public void obtenerEnlaces() throws FranquiciaExcepction {
        try {
            for (Franquicia franquicia : listaFranquicias) {
                //List<String> linksout = new ArrayList<>();

                List<HtmlAnchor> linksInicio = franquicia.getPaginaInicio().getAnchors();
                List<HtmlAnchor> linksContacto = franquicia.getPaginaContacto().getAnchors();

                for (HtmlAnchor link : linksInicio) {
                    if (link.getHrefAttribute().contains("twitter")) {
                        franquicia.addNumEnlacesInicioTwitter();
                    }
                    if(link.getHrefAttribute().contains("instagram")){
                        franquicia.addNumEnlacesInicioInstagram();
                    }
                    if(link.getHrefAttribute().contains("facebook")){
                        franquicia.addNumEnlacesInicioFacebook();
                    }
                }

                for (HtmlAnchor link : linksContacto) {
                    if (link.getHrefAttribute().contains("twitter")) {
                        franquicia.addNumEnlacesContactoTwitter();
                    }
                    if(link.getHrefAttribute().contains("instagram")){
                        franquicia.addNumEnlacesContactoInstagram();
                    }
                    if(link.getHrefAttribute().contains("facebook")){
                        franquicia.addNumEnlacesContactoFacebook();
                    }
                }
            System.out.println("OK: ");
            }

        } catch (Exception e) {
            throw new FranquiciaExcepction(e.getMessage());
        }
    }

    /**
     *
     */
    private void inicializarCliente() {
        cliente = new WebClient(BrowserVersion.CHROME);

        //Elimia datos innecesarios
        cliente.getOptions().setCssEnabled(false);
        cliente.getOptions().setThrowExceptionOnFailingStatusCode(false);
        cliente.getOptions().setThrowExceptionOnScriptError(false);
        cliente.getOptions().setPrintContentOnFailingStatusCode(false);
    }

    /**
     * @throws FranquiciaExcepction
     */
    private void cargaPaginas() throws FranquiciaExcepction {
        this.paginas = new ArrayList<>();
        for (Franquicia franquicia : listaFranquicias) {
            try {
                franquicia.setPaginaInicio(cliente.getPage(franquicia.getEnlaceInicio()));
                franquicia.setPaginaContacto(cliente.getPage(franquicia.getEnlaceContacto()));
            } catch (IOException e) {
                throw new FranquiciaExcepction(e.getMessage());
            }


        }
    }


    /**
     *
     */
    private void cierraCliente() {
        cliente.getCurrentWindow().getJobManager().removeAllJobs();
        cliente.close();
    }

    public List<Franquicia> getListaFranquicias() {
        return listaFranquicias;
    }

    public void setListaFranquicias(List<Franquicia> listaFranquicias) {
        this.listaFranquicias = listaFranquicias;
    }
}

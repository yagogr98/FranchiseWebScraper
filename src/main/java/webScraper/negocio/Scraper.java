package webScraper.negocio;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import webScraper.beans.Franquicia;
import webScraper.error.FranquiciaException;
import webScraper.utils.Validator;
import webScraper.utils.WriterReader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Scraper {

    WebClient cliente;
    List<HtmlPage> paginas;

    List<Franquicia> listaFranquicias;
    private final static Logger LOGGER = Logger.getLogger("negocio.Scraper");;

    private final String ENLACE_AEF = "https://www.franquiciadores.com/buscador-de-franquicias/";
    private final String LINK_CLASS_AEF = "vc_gitem-link";

    /**
     * Metodo operar
     */
    public void operar(String path) throws FranquiciaException {
        initializeLog();
        //inicializarFranquicias(path);
        LOGGER.info(" ---- INICIANDO ----");
        inicializarCliente();
        cargarFranquicias();
        cargarPaginas();
        obtenerEnlaces();
        cierraCliente();
        generaExcel();
        LOGGER.info(" ---- FINALIZADO ----");

    }


    /**
     * inicializa lista de granquicias
     * @param path
     * @throws FranquiciaException
     */
    private void inicializarFranquicias(String path) throws FranquiciaException {
        setListaFranquicias(WriterReader.readCSV(path));
    }


    /**
     * Obtiene enlaces de paginas de franquicias
     * @throws FranquiciaException
     */
    private void obtenerEnlaces() throws FranquiciaException {
        LOGGER.info(" ---- INICIO obtenerEnlaces() ----");
        try {
            for (Franquicia franquicia : listaFranquicias) {
                LOGGER.info(" ---- OBTENIENDO ENLACES:"  + franquicia.getNombre() + "----");

                //List<String> linksout = new ArrayList<>();
                if(Validator.isNotNull(franquicia.getPaginaInicio())) {
                    List<HtmlAnchor> linksInicio = franquicia.getPaginaInicio().getAnchors();
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
                }
                if(Validator.isNotNull(franquicia.getPaginaContacto())) {
                    List<HtmlAnchor> linksContacto = franquicia.getPaginaContacto().getAnchors();
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
                }
            }

        } catch (Exception e) {
            throw new FranquiciaException(e.getMessage());
        }
        LOGGER.info(" ---- FIN obtenerEnlaces() ----");
    }

    /**
     * Genera excel
     * @throws FranquiciaException
     */
    private void generaExcel() throws FranquiciaException {
        WriterReader.writeExcel(listaFranquicias);
    }

    /**
     * inicializa cliente
     */
    private void inicializarCliente() {
        LOGGER.info(" ---- INICIALIZANDO CLIENTE  ----");
        cliente = new WebClient(BrowserVersion.CHROME);
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
        java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);
        //Elimia datos innecesarios
        cliente.getOptions().setCssEnabled(false);
        cliente.getOptions().setThrowExceptionOnFailingStatusCode(false);
        cliente.getOptions().setThrowExceptionOnScriptError(false);
        cliente.getOptions().setPrintContentOnFailingStatusCode(false);
        cliente.setJavaScriptTimeout(20000);
        cliente.getOptions().setTimeout(20000);
        LOGGER.info(" ---- FIN INICIALIZAR CLIENTE ----");
    }

    /**
     * @throws FranquiciaException
     */
    private void cargarPaginas() throws FranquiciaException {
        LOGGER.info(" ---- INICIO cargarPaginas() ----");
        this.paginas = new ArrayList<>();
        for (Franquicia franquicia : listaFranquicias) {

            LOGGER.info(" ---- CARGANDO DATOS FRANQUICIA:" + franquicia.getNombre() +  "----");

            try {
                if(Validator.validateURL(franquicia.getEnlaceInicio())){
                    LOGGER.info(" ---- CARGANDO DATOS FRANQUICIA:" + franquicia.getNombre() + " URL INICIO: " + franquicia.getEnlaceInicio() + "----");
                    franquicia.setPaginaInicio(cliente.getPage(franquicia.getEnlaceInicio()));
                }
                if(Validator.validateURL(franquicia.getEnlaceContacto())) {
                    LOGGER.info(" ---- CARGANDO DATOS FRANQUICIA:" + franquicia.getNombre() + " URL CONTACTO: " + franquicia.getEnlaceInicio() + "----");
                    franquicia.setPaginaContacto(cliente.getPage(franquicia.getEnlaceContacto()));
                }
            } catch (IOException e) {
                throw new FranquiciaException(e.getMessage());
            }

            LOGGER.info(" ---- FIN cargarPaginas() ----");
        }
    }
    /**
     * @throws FranquiciaException
     */
    private void cargarFranquicias() throws FranquiciaException {
        LOGGER.info(" ---- INICIO cargarFranquicias() ----");
        try {
            List<Franquicia> franquiciasLocal = new ArrayList<Franquicia>();
            HtmlPage aef = cliente.getPage(ENLACE_AEF);
            List<HtmlAnchor> links =aef.getAnchors() ;
            for (HtmlAnchor link : links) {
                if (link.getAttribute("class").contains(LINK_CLASS_AEF)){
                    Franquicia franquiciaTemporal = new Franquicia();
                    franquiciaTemporal.setNombre(link.getAttribute("title"));

                    HtmlPage aef_franquicia = cliente.getPage(link.getHrefAttribute());
                    final HtmlAnchor enlace = aef_franquicia.getFirstByXPath("//td/a");
                    if(enlace!=null){
                        franquiciaTemporal.setEnlaceInicio(enlace.getHrefAttribute());
                    }
                    franquiciasLocal.add(franquiciaTemporal);
                }
            }
            List<Franquicia> listaSalida = franquiciasLocal.stream()
                    .collect(Collectors.toSet())
                    .stream()
                    .collect(Collectors.toList());
            setListaFranquicias(listaSalida);
        } catch (MalformedURLException ex) {
            throw new FranquiciaException(ex.getMessage());
        } catch (IOException ex) {
            throw new FranquiciaException(ex.getMessage());
        }
        LOGGER.info(" ---- FIN cargarFranquicias() ----");
    }


    /**
     * cierra cliente
     */
    private void cierraCliente() {
        LOGGER.info(" ---- INICIO cierraCliente() ----");
        cliente.getCurrentWindow().getJobManager().removeAllJobs();
        cliente.close();
        LOGGER.info(" ---- FIN cierraCliente() ----");

    }

    /**
     *
     * @return lista de franquicias
     */
    public List<Franquicia> getListaFranquicias() {
        return listaFranquicias;
    }

    /**
     *
     * @param listaFranquicias
     */
    public void setListaFranquicias(List<Franquicia> listaFranquicias) {
        this.listaFranquicias = Collections.unmodifiableList(new ArrayList<>(listaFranquicias));
    }

    private static void initializeLog() {
        Handler consoleHandler = new ConsoleHandler();
        Handler fileHandler = null;
        try {
            fileHandler = new FileHandler("./FranchiseWebScrapper.log", false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LOGGER.addHandler(consoleHandler);
        LOGGER.addHandler(fileHandler);
        consoleHandler.setLevel(Level.ALL);
        fileHandler.setLevel(Level.ALL);
    }

}

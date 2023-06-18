package webScraper.negocio;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import webScraper.beans.Franquicia;
import webScraper.error.FranquiciaException;
import webScraper.utils.Constants;
import webScraper.utils.ProgressBar;
import webScraper.utils.Validator;
import webScraper.utils.WriterReader;

import java.io.IOException;
import java.util.*;
import java.util.logging.*;
import java.util.stream.Collectors;

public class Scraper {

    private final static Logger LOGGER = Logger.getLogger(Scraper.class.getName());
    WebClient cliente;
    List<HtmlPage> paginas;

    List<Franquicia> listaFranquicias;
    List<Franquicia> listaFranquiciasError;

    public Scraper(){
    }

    /**
     * Metodo operar
     * Gestiona todas las operaciones de la App
     * @param opcion opcion a ejecutar
     * @param path path al archivo
     * @throws FranquiciaException error
     */
    public void operar(String opcion, String path) throws FranquiciaException {


        inicializarCliente();
        if(opcion.equals(Constants.LECTURA_LISTA)){
            inicializarFranquiciasCSV(path);
            cargarPaginas();
            obtenerEnlaces();
            generaExcel();
        }else if (opcion.equals(Constants.GENERACION_LISTA)){
            cargarFranquiciasAEF();
            WriterReader.writeCSV(listaFranquicias);
        } else{
            cargarFranquiciasAEF();
            cargarPaginas();
            obtenerEnlaces();
            generaExcel();
        }
        cierraCliente();


    }


    /**
     * inicializa lista de franquicias desde un CSV
     * @param path path al CSV
     * @throws FranquiciaException fallo al leer csv
     */
    private void inicializarFranquiciasCSV(String path) throws FranquiciaException {
        setListaFranquicias(WriterReader.readCSV(path));
    }


    /**
     * Obtiene enlaces de paginas de franquicias
     */
    private void obtenerEnlaces(){

            for (Franquicia franquicia : listaFranquicias) {
                //List<String> linksout = new ArrayList<>();
                if(Validator.isNotNull(franquicia.getPaginaInicio())) {
                    List<HtmlAnchor> linksInicio = franquicia.getPaginaInicio().getAnchors();
                    for (HtmlAnchor link : linksInicio) {
                        if(link.getHrefAttribute().contains(Constants.TWITTER))  {
                            franquicia.addNumEnlacesInicioTwitter();
                        }
                        if(link.getHrefAttribute().contains(Constants.INSTAGRAM)){
                            franquicia.addNumEnlacesInicioInstagram();
                        }
                        if(link.getHrefAttribute().contains(Constants.FACEBOOK)){
                            franquicia.addNumEnlacesInicioFacebook();
                        }
                    }
                }
            }

        }

    /**
     * Genera excel
     * @throws FranquiciaException fallo al generar excel
     */
    private void generaExcel() throws FranquiciaException {
        WriterReader.writeExcel(listaFranquicias,listaFranquiciasError);
    }

    /**
     * inicializa cliente
     */
    private void inicializarCliente() {
        cliente = new WebClient(BrowserVersion.CHROME);
        java.util.logging.Logger.getLogger(Constants.LOG_HTMLUNIT).setLevel(java.util.logging.Level.OFF);
        java.util.logging.Logger.getLogger(Constants.LOG_APACHE_HTTP).setLevel(java.util.logging.Level.OFF);
        //Elimia datos innecesarios
        cliente.getOptions().setCssEnabled(false);
        cliente.getOptions().setThrowExceptionOnFailingStatusCode(false);
        cliente.getOptions().setThrowExceptionOnScriptError(false);
        cliente.getOptions().setPrintContentOnFailingStatusCode(false);
        cliente.setJavaScriptTimeout(10000);
        cliente.getOptions().setTimeout(10000);
    }

    /**
     * Carga páginas de franquicias.
     * @throws FranquiciaException fallo al cargar paginas
     */
    private void cargarPaginas() throws FranquiciaException {
        LOGGER.info("Iniciando analisis de franquicias.");
        this.paginas = new ArrayList<>();

        List<Franquicia> franquiciasLocalConErrores = new ArrayList<>();
        ProgressBar progressBar = new ProgressBar("Analizando franquicias", listaFranquicias.size());
        for (Franquicia franquicia : listaFranquicias) {

            progressBar.getSumaTotal();
                try {
                    if (Validator.validateURL(franquicia.getEnlaceInicio())) {

                        franquicia.setPaginaInicio(cliente.getPage(franquicia.getEnlaceInicio()));
                    }
                    else if (Validator.validateURL(franquicia.getEnlaceContacto())) {
                        franquicia.setPaginaContacto(cliente.getPage(franquicia.getEnlaceContacto()));
                    }else{
                        franquiciasLocalConErrores.add(franquicia);
                    }
                } catch (IOException e) {
                    throw new FranquiciaException("ERROR: al cargar pagina de " + franquicia.getNombre());
                }


        }
        List<Franquicia> modifiable = new ArrayList<>(listaFranquicias);
        modifiable.removeAll(franquiciasLocalConErrores);
        listaFranquicias = modifiable;
        listaFranquiciasError =  franquiciasLocalConErrores;
        LOGGER.info("Analisis de franquicias finalizado");
    }
    /**
     * Carga listado de franquicias del directorio de la AEF.
     * @throws FranquiciaException fallo al leer del directorio
     */
    private void cargarFranquiciasAEF() throws FranquiciaException {
        LOGGER.info("Iniciando carga de franquicias.");
        try {

            List<Franquicia> franquiciasLocal = new ArrayList<>();
            HtmlPage aef = cliente.getPage(Constants.ENLACE_AEF);
            List<HtmlAnchor> links =aef.getAnchors() ;

            ProgressBar progressBar = new ProgressBar("Cargando Franquicias");

            for (HtmlAnchor link : links) {

                if (link.getAttribute("class").contains(Constants.LINK_CLASS_AEF)){
                    Franquicia franquiciaTemporal = new Franquicia();
                    franquiciaTemporal.setNombre(link.getAttribute(Constants.TITLE_HTML));
                    progressBar.getSumaTotal();
                    HtmlPage aef_franquicia = cliente.getPage(link.getHrefAttribute());
                    final HtmlAnchor enlace = aef_franquicia.getFirstByXPath(Constants.SEPARADOR_FRANQUICIAS_XPATH);
                    progressBar.getSumaTotal();
                    if(enlace!=null){
                        franquiciaTemporal.setEnlaceInicio(enlace.getHrefAttribute());
                        franquiciasLocal.add(franquiciaTemporal);
                    }

                }
            }
            // Se eliminan duplicidades
            setListaFranquicias(franquiciasLocal.stream().distinct().collect(Collectors.toList()));

        } catch (IOException ex) {
            throw new FranquiciaException("ERROR: No se pudieron cargar franquicias de la AEF");
        }
        LOGGER.info("Franquicias cargadas.");
            }


    /**
     * cierra cliente
     */
    private void cierraCliente() {
        cliente.getCurrentWindow().getJobManager().removeAllJobs();
        cliente.close();

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
     * @param listaFranquicias listado de franquicias
     */
    public void setListaFranquicias(List<Franquicia> listaFranquicias) {
        this.listaFranquicias = Collections.unmodifiableList(new ArrayList<>(listaFranquicias));
    }

}

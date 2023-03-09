package webScraper.beans;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Franquicia {
    private String nombre;
    private String enlaceInicio;
    private String enlaceContacto;

    private HtmlPage paginaInicio;
    private HtmlPage paginaContacto;

    private int numEnlacesInicioTwitter;
    private int numEnlacesInicioFacebook;
    private int numEnlacesInicioInstagram;
    private int numEnlacesContactoTwitter;
    private int numEnlacesContactoFacebook;
    private int numEnlacesContactoInstagram;


    /**
     * Constructor basico
     */
    public Franquicia() {

    }

    /**
     * Constructor
     *
     * @param nombre
     * @param enlaceInicio
     * @param enlaceContacto
     */
    public Franquicia(String nombre, String enlaceInicio, String enlaceContacto) {
        setNombre(nombre);
        setEnlaceInicio(enlaceInicio);
        setEnlaceContacto(enlaceContacto);
    }

    /**
     * @return enlace a la pagina de contacto
     */
    public String getEnlaceContacto() {
        return enlaceContacto;
    }

    /**
     * @param enlaceContacto
     */
    public void setEnlaceContacto(String enlaceContacto) {
        this.enlaceContacto = enlaceContacto;
    }

    /**
     * @return enlace a la pagina de inicio
     */
    public String getEnlaceInicio() {
        return enlaceInicio;
    }

    /**
     * @param enlaceInicio
     */
    public void setEnlaceInicio(String enlaceInicio) {
        this.enlaceInicio = enlaceInicio;
    }

    /**
     * @return nombre de la franquicia
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public int getNumEnlacesContactoFacebook() {
        return numEnlacesContactoFacebook;
    }

    public int getNumEnlacesContactoInstagram() {
        return numEnlacesContactoInstagram;
    }

    public int getNumEnlacesContactoTwitter() {
        return numEnlacesContactoTwitter;
    }

    public void setNumEnlacesContactoFacebook(int numEnlacesContactoFacebook) {
        this.numEnlacesContactoFacebook = numEnlacesContactoFacebook;
    }

    public void setNumEnlacesContactoInstagram(int numEnlacesContactoInstagram) {
        this.numEnlacesContactoInstagram = numEnlacesContactoInstagram;
    }

    public void setNumEnlacesContactoTwitter(int numEnlacesContactoTwitter) {
        this.numEnlacesContactoTwitter = numEnlacesContactoTwitter;
    }

    public int getNumEnlacesInicioFacebook() {
        return numEnlacesInicioFacebook;
    }

    public int getNumEnlacesInicioInstagram() {
        return numEnlacesInicioInstagram;
    }

    public int getNumEnlacesInicioTwitter() {
        return numEnlacesInicioTwitter;
    }

    public void setNumEnlacesInicioFacebook(int numEnlacesInicioFacebook) {
        this.numEnlacesInicioFacebook = numEnlacesInicioFacebook;
    }

    public void setNumEnlacesInicioInstagram(int numEnlacesInicioInstagram) {
        this.numEnlacesInicioInstagram = numEnlacesInicioInstagram;
    }

    public void setNumEnlacesInicioTwitter(int numEnlacesInicioTwitter) {
        this.numEnlacesInicioTwitter = numEnlacesInicioTwitter;
    }

    public HtmlPage getPaginaContacto() {
        return paginaContacto;
    }

    public HtmlPage getPaginaInicio() {
        return paginaInicio;
    }

    public void setPaginaContacto(HtmlPage paginaContacto) {
        this.paginaContacto = paginaContacto;
    }

    public void setPaginaInicio(HtmlPage paginaInicio) {
        this.paginaInicio = paginaInicio;
    }

    public void addNumEnlacesInicioTwitter(){
        setNumEnlacesInicioTwitter(getNumEnlacesInicioTwitter()+1);
    }
    public void addNumEnlacesInicioFacebook(){
        setNumEnlacesInicioFacebook(getNumEnlacesInicioFacebook()+1);
    }
    public void addNumEnlacesInicioInstagram(){
        setNumEnlacesInicioInstagram(getNumEnlacesInicioInstagram()+1);
    }


    public void addNumEnlacesContactoTwitter(){
        setNumEnlacesContactoTwitter(getNumEnlacesContactoTwitter()+1);
    }
    public void addNumEnlacesContactoFacebook(){
        setNumEnlacesContactoFacebook(getNumEnlacesContactoFacebook()+1);
    }
    public void addNumEnlacesContactoInstagram(){
        setNumEnlacesContactoInstagram(getNumEnlacesContactoInstagram()+1);
    }
}

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

    /**
     *
     * @return enlaces en pagina contacto
     */
    public int getNumEnlacesContactoFacebook() {
        return numEnlacesContactoFacebook;
    }
    /**
     *
     * @return enlaces en pagina contacto
     */
    public int getNumEnlacesContactoInstagram() {
        return numEnlacesContactoInstagram;
    }
    /**
     *
     * @return enlaces en pagina contacto
     */
    public int getNumEnlacesContactoTwitter() {
        return numEnlacesContactoTwitter;
    }

    /**
     *
     * @param numEnlacesContactoFacebook
     */
    public void setNumEnlacesContactoFacebook(int numEnlacesContactoFacebook) {
        this.numEnlacesContactoFacebook = numEnlacesContactoFacebook;
    }

    /**
     *
     * @param numEnlacesContactoInstagram
     */
    public void setNumEnlacesContactoInstagram(int numEnlacesContactoInstagram) {
        this.numEnlacesContactoInstagram = numEnlacesContactoInstagram;
    }

    /**
     *
     * @param numEnlacesContactoTwitter
     */
    public void setNumEnlacesContactoTwitter(int numEnlacesContactoTwitter) {
        this.numEnlacesContactoTwitter = numEnlacesContactoTwitter;
    }

    /**
     *
     * @return
     */
    public int getNumEnlacesInicioFacebook() {
        return numEnlacesInicioFacebook;
    }

    /**
     *
     * @return
     */
    public int getNumEnlacesInicioInstagram() {
        return numEnlacesInicioInstagram;
    }

    /**
     *
     * @return
     */
    public int getNumEnlacesInicioTwitter() {
        return numEnlacesInicioTwitter;
    }

    /**
     *
     * @param numEnlacesInicioFacebook
     */
    public void setNumEnlacesInicioFacebook(int numEnlacesInicioFacebook) {
        this.numEnlacesInicioFacebook = numEnlacesInicioFacebook;
    }

    /**
     *
     * @param numEnlacesInicioInstagram
     */
    public void setNumEnlacesInicioInstagram(int numEnlacesInicioInstagram) {
        this.numEnlacesInicioInstagram = numEnlacesInicioInstagram;
    }

    /**
     *
     * @param numEnlacesInicioTwitter
     */
    public void setNumEnlacesInicioTwitter(int numEnlacesInicioTwitter) {
        this.numEnlacesInicioTwitter = numEnlacesInicioTwitter;
    }

    /**
     *
     * @return
     */
    public HtmlPage getPaginaContacto() {
        return paginaContacto;
    }

    /**
     *
     * @return
     */
    public HtmlPage getPaginaInicio() {
        return paginaInicio;
    }

    /**
     *
     * @param paginaContacto
     */
    public void setPaginaContacto(HtmlPage paginaContacto) {
        this.paginaContacto = paginaContacto;
    }

    /**
     *
     * @param paginaInicio
     */
    public void setPaginaInicio(HtmlPage paginaInicio) {
        this.paginaInicio = paginaInicio;
    }

    /**
     * anade un enlace a inicio
     */
    public void addNumEnlacesInicioTwitter(){
        setNumEnlacesInicioTwitter(getNumEnlacesInicioTwitter()+1);
    }

    /**
     * anade un enlace a inicio
     */
    public void addNumEnlacesInicioFacebook(){
        setNumEnlacesInicioFacebook(getNumEnlacesInicioFacebook()+1);
    }

    /**
     *  anade un enlace a inicio
     */
    public void addNumEnlacesInicioInstagram(){
        setNumEnlacesInicioInstagram(getNumEnlacesInicioInstagram()+1);
    }

    /**
     * anade un enlace a contacto
     */
    public void addNumEnlacesContactoTwitter(){
        setNumEnlacesContactoTwitter(getNumEnlacesContactoTwitter()+1);
    }
    /**
     * anade un enlace a contacto
     */
    public void addNumEnlacesContactoFacebook(){
        setNumEnlacesContactoFacebook(getNumEnlacesContactoFacebook()+1);
    }
    /**
     * anade un enlace a contacto
     */
    public void addNumEnlacesContactoInstagram(){
        setNumEnlacesContactoInstagram(getNumEnlacesContactoInstagram()+1);
    }

    /**
     *
     * @return enlaces totales twitter
     */
    public int getEnlacesTwitter(){
        return getNumEnlacesContactoTwitter()+getNumEnlacesInicioTwitter();
    }

    /**
     *
     * @return enlaces totales instagram
     */
    public int getEnlacesInstagram(){
        return getNumEnlacesContactoInstagram()+getNumEnlacesInicioInstagram();
    }

    /**
     *
     * @return enlaces totales facebook
     */
    public int getEnlacesFacebook(){
        return getNumEnlacesContactoFacebook()+getNumEnlacesInicioFacebook();
    }

}

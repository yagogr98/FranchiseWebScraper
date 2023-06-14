package webScraper.beans;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.util.Objects;

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
     * @param nombre nombre de la franquicia
     * @param enlaceInicio url a la web
     */
    public Franquicia(String nombre, String enlaceInicio) {
        setNombre(nombre);
        setEnlaceInicio(enlaceInicio);

    }

    /**
     * @return enlace a la pagina de contacto
     */
    public String getEnlaceContacto() {
        return enlaceContacto;
    }

    /**
     * @param enlaceContacto url a la web
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
     * @param enlaceInicio url a la web
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
     * @param nombre nombre de la franquicia
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
     * @param numEnlacesContactoFacebook numero de enlaces a red social
     */
    public void setNumEnlacesContactoFacebook(int numEnlacesContactoFacebook) {
        this.numEnlacesContactoFacebook = numEnlacesContactoFacebook;
    }

    /**
     *
     * @param numEnlacesContactoInstagram numero de enlaces a red social
     */
    public void setNumEnlacesContactoInstagram(int numEnlacesContactoInstagram) {
        this.numEnlacesContactoInstagram = numEnlacesContactoInstagram;
    }

    /**
     *
     * @param numEnlacesContactoTwitter numero de enlaces a red social
     */
    public void setNumEnlacesContactoTwitter(int numEnlacesContactoTwitter) {
        this.numEnlacesContactoTwitter = numEnlacesContactoTwitter;
    }

    /**
     *
     * @return numero de enlaces a red social
     */
    public int getNumEnlacesInicioFacebook() {
        return numEnlacesInicioFacebook;
    }

    /**
     *
     * @return numero de enlaces a red social
     */
    public int getNumEnlacesInicioInstagram() {
        return numEnlacesInicioInstagram;
    }

    /**
     *
     * @return numero de enlaces a red social
     */
    public int getNumEnlacesInicioTwitter() {
        return numEnlacesInicioTwitter;
    }

    /**
     *
     * @param numEnlacesInicioFacebook numero de enlaces a red social
     */
    public void setNumEnlacesInicioFacebook(int numEnlacesInicioFacebook) {
        this.numEnlacesInicioFacebook = numEnlacesInicioFacebook;
    }

    /**
     *
     * @param numEnlacesInicioInstagram numero de enlaces a red social
     */
    public void setNumEnlacesInicioInstagram(int numEnlacesInicioInstagram) {
        this.numEnlacesInicioInstagram = numEnlacesInicioInstagram;
    }

    /**
     *
     * @param numEnlacesInicioTwitter numero de enlaces a red social
     */
    public void setNumEnlacesInicioTwitter(int numEnlacesInicioTwitter) {
        this.numEnlacesInicioTwitter = numEnlacesInicioTwitter;
    }

    /**
     *
     * @return numero de enlaces a red social
     */
    public HtmlPage getPaginaContacto() {
        return paginaContacto;
    }

    /**
     *
     * @return numero de enlaces a red social
     */
    public HtmlPage getPaginaInicio() {
        return paginaInicio;
    }

    /**
     *
     * @param paginaContacto numero de enlaces a red social
     */
    public void setPaginaContacto(HtmlPage paginaContacto) {
        this.paginaContacto = paginaContacto;
    }

    /**
     *
     * @param paginaInicio numero de enlaces a red social
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

    /**
     *
     * @param franquicia parametro
     * @return Igual o no
     */
    @Override
    public boolean equals(Object franquicia) {
        if (this == franquicia) return true;
        if (franquicia == null || getClass() != franquicia.getClass()) return false;
        Franquicia that = (Franquicia) franquicia;
        return nombre.equals(that.nombre) && Objects.equals(enlaceInicio, that.enlaceInicio) && Objects.equals(enlaceContacto, that.enlaceContacto);
    }

    /**
     *
     * @return hashCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(nombre, enlaceInicio, enlaceContacto);
    }
}

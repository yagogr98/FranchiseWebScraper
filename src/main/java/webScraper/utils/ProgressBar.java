package webScraper.utils;

public class ProgressBar {
    private final char VACIO = '-';
    private final String LLENO = "X";
    private final int maximo = 10;
    private String mensaje;
    private int parcial;
    private int total;
    private int anterior;

    /**
     * Constructor
     *
     * @param mensaje a mostrar
     * @param total   de valores
     */
    public ProgressBar(String mensaje, int total) {
        this.mensaje = mensaje;
        this.total = total;
        this.anterior = 0;
    }

    /**
     * Constructor
     *
     * @param mensaje a mostrar
     */
    public ProgressBar(String mensaje) {
        this.mensaje = mensaje;
        this.total = 120;
        this.anterior = 0;
    }

    /**
     * Devuelve por consola (si es necesario) la ultima actualizacion
     * de la barra de progreso
     */
    public void getSumaTotal() {
        if (parcial < total) {
            parcial++;
        }

        int progreso = Math.round(((100f * parcial) / total) / maximo);
        if (anterior <= progreso || anterior == 0) {
            anterior++;

            int faltante = maximo - progreso;

            StringBuilder sb = new StringBuilder();
            sb.append("[");

            for (int i = 0; i < progreso; i++) {
                sb.append(LLENO);
            }
            for (int i = 0; i < faltante; i++) {
                sb.append(VACIO);
            }
            sb.append("]");

            System.out.println(mensaje + "  " + sb);
        }

    }

    /**
     * @return parcial
     */
    public int getParcial() {
        return parcial;
    }

    /**
     * @param parcial momentaneo
     */
    public void setParcial(int parcial) {
        this.parcial = parcial;
    }

    /**
     * @return total
     */
    public int getTotal() {
        return total;
    }

    /**
     * @param total valor total maximo
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * @return devuelve el mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje a mostrar
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return devuelve ultimo parcial valido
     */
    public int getAnterior() {
        return anterior;
    }

    /**
     * @param anterior ultimo parcial valido
     */
    public void setAnterior(int anterior) {
        this.anterior = anterior;
    }
}

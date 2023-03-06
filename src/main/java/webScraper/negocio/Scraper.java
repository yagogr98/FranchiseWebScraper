package webScraper.negocio;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Scraper {
    public static final String SEPARATOR=";";

    WebClient cliente;
    List<HtmlPage> paginas;

    /**
     *
     * @throws IOException
     */
    public void operar() throws IOException {
        inicializarCliente();
        cargaPaginas();
        obtenerEnlaces();
    }

    /**
     *
     */
    public void obtenerEnlaces() {
        inicializarCliente();

        try {
            for(HtmlPage page : paginas) {
                List<String> linksout = new ArrayList<>();
                List<HtmlAnchor> links = page.getAnchors();
                for (HtmlAnchor link : links) {
                    if (link.getHrefAttribute().contains("twitter") || link.getHrefAttribute().contains("instagram") || link.getHrefAttribute().contains("facebook")) {
                        System.out.println("Encontrado");
                        linksout.add(link.getHrefAttribute());
                    }
                    String href = link.getHrefAttribute();
                    System.out.println("Link: " + href);
                }
            }

        } catch (Exception e) {
            System.out.println("An error occurred: " + e);
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
     *
     * @throws IOException
     */
    private void cargaPaginas() throws IOException {


        BufferedReader br = null;

        try {

            br =new BufferedReader(new FileReader(""));
            String line = br.readLine();
            while (null!=line) {
                String [] fields = line.split(SEPARATOR);
                System.out.println(Arrays.toString(fields));

                System.out.println(Arrays.toString(fields));

                line = br.readLine();
            }

        } catch (Exception e) {

        } finally {
            if (null!=br) {
                br.close();
            }
        }

    }


    /**
     *
     */
    private void cierraCliente(){
        cliente.getCurrentWindow().getJobManager().removeAllJobs();
        cliente.close();
    }

}

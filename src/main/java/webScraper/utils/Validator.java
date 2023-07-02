package webScraper.utils;

import java.io.IOException;
import java.net.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public  class Validator {
    public static boolean validateURL(String url) {

        if(isNotNull(url)) {
            Pattern patt = Pattern.compile(Constants.REGEX_URL);
            Matcher matcher = patt.matcher(url);
            if(matcher.matches()) {
                try {
                   URI uri = new URL(url).toURI();
                    if(uri.getHost()==null){
                        return false;
                    }
                    URLConnection con = new URL(url).openConnection();
                    con.setConnectTimeout(2000);
                    con.setReadTimeout(2000);
                    con.getInputStream();
                    return true;
                } catch (MalformedURLException e) {
                    return false;
                } catch (URISyntaxException e) {
                    return false;
                } catch (IOException e) {
                    if( e.getMessage().contains("403")){
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;
    }

    public static boolean isNotNull(Object object){
        return object != null;
    }
}

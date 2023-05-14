package webScraper.utils;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public  class Validator {
    private final static String REGEX_URL = "^(https?|ftp|file)://www.[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
    public static boolean validateURL(String url) {
        if(isNotNull(url)) {
            Pattern patt = Pattern.compile(REGEX_URL);
            Matcher matcher = patt.matcher(url);
            return matcher.matches();
        }
        return false;
    }

    public static boolean isNotNull(Object object){
        if(object!=null){
            return true;
        }
        return false;
    }
}

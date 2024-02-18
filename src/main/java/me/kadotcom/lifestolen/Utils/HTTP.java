package me.kadotcom.lifestolen.Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class HTTP {
    public static String get(String url) {
        String stuff = "";
        try {
            URL Url = new URL(url);
            BufferedReader in = new BufferedReader(new InputStreamReader(Url.openStream()));
            String str = in.readLine();
            in.close();
            if (str != null) {
                stuff = str;
            }
        }
        catch (java.io.IOException e1) {
            stuff = e1.getMessage();
        }
        return stuff;
    }
    
}

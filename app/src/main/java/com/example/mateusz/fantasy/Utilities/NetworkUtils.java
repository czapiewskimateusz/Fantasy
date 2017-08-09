package com.example.mateusz.fantasy.Utilities;

import android.content.pm.PackageManager;
import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

/**
 * Created by Mateusz on 09.08.2017.
 */

public class NetworkUtils {

    final static String FANTASY_BASE_URL =
            "http://fantasypl.c0.pl/getuser.php";

    final static String PARAM_QUERY = "?";


    public static URL buildUrl(String getUserQuery) {

        String url = null;
        try {
            url = FANTASY_BASE_URL + PARAM_QUERY + "email=" + "\""+getUserQuery+"\"";

            return new URL(url);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try{

            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput){
                return scanner.next();
            } else {
                return null;
            }

        }finally {
            urlConnection.disconnect();
        }
    }
}

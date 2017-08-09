//package com.example.mateusz.fantasy.Utilities;
//
//
//import android.content.AsyncTaskLoader;
//import android.content.Context;
//
//import org.apache.http.HttpResponse;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.DefaultHttpClient;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.MalformedURLException;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.net.URL;
//
///**
// * Created by Mateusz on 07.08.2017.
// */
//
//public class GetUserAccount extends AsyncTaskLoader<String> {
//    private String email;
//
//    public GetUserAccount(Context context, String email) {
//        super(context);
//
//        this.email = email;
//    }
//
//    @Override
//    protected void onStartLoading() {
//        forceLoad();
//    }
//
//    @Override
//    public String loadInBackground() {
//        try {
//
//            String link = "http://fantasypl.c0.pl/getuser.php?email=" + email;
//
//            URL url = new URL(link);
//
//            HttpClient client = new DefaultHttpClient();
//            HttpGet request = new HttpGet();
//
//            request.setURI(new URI(link));
//
//            HttpResponse response = client.execute(request);
//
//            BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//            StringBuffer sb = new StringBuffer("");
//            String line = "";
//
//            while ((line = in.readLine()) != null){
//                sb.append(line);
//                break;
//            }
//
//            in.close();
//            return sb.toString();
//
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    @Override
//    public void deliverResult(String data) {
//
//    }
//}

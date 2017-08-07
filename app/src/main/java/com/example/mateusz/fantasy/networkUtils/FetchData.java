package com.example.mateusz.fantasy.networkUtils;


import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by Mateusz on 07.08.2017.
 */

public class FetchData extends AsyncTask<String, String, String> {
    private TextView mTv_Result;
    private Context context;

    public FetchData(Context context, TextView result) {
        this.context = context;
        mTv_Result = result;
    }

    @Override
    protected String doInBackground(String... arg0) {


        try {
            String id = (String) arg0[0];

            String link = "http://fantasypl.c0.pl/getuser.php?userid=" + id;

            URL url = new URL(link);

            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();

            request.setURI(new URI(link));

            HttpResponse response = client.execute(request);

            BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer sb = new StringBuffer("");
            String line = "";

            while ((line = in.readLine()) != null){
                sb.append(line);
                break;
            }

            in.close();
            return sb.toString();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        this.mTv_Result.setText(s);
    }
}

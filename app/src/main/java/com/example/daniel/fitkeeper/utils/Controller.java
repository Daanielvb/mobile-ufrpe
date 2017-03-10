package com.example.daniel.fitkeeper.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import model.Person;

/**
 * Created by DANIEL on 08/03/2017.
 */

public class Controller {

    public static Person currentUser;

    public static Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    public static boolean putRequestWithJson(String... params) {
        String path = params[0];
        String jsonString = params[1];
        InputStream inputStream = null;
        String result = "";
        try {
            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            // 2. make POST request to the given URL
            HttpPut httpPUT = new
                    HttpPut(path);
            StringEntity se = new StringEntity(jsonString);
            // 6. set httpPost Entity
            httpPUT.setEntity(se);
            // 7. Set some headers to inform server about the type of the content
            httpPUT.setHeader("Accept", "application/json");
            httpPUT.setHeader("Content-type", "application/json");
            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPUT);
            if (httpResponse.getStatusLine().getStatusCode() != 200)
                return false;
            else
                return true;
        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
        return false;
    }

    public static JSONArray getJSONObjectFromURL(String urlString, String requestMethod) throws IOException, JSONException {
        HttpURLConnection urlConnection = null;
        URL url = new URL(urlString);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod(requestMethod);
        urlConnection.setReadTimeout(10000);
        urlConnection.setConnectTimeout(15000);
        urlConnection.setDoOutput(true);
        urlConnection.connect();

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        char[] buffer = new char[1024];
        String jsonString = new String();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();
        jsonString = sb.toString();
        JSONArray jObj = new JSONArray(jsonString);
        return jObj;
    }


    public static boolean checkCredentials(String jsonStringPerson) {
        try {
            return putRequestWithJson(
                    RequestHelper.composeUrlPath(Constants.PERSON_ENTITY, String.valueOf(Controller.currentUser.getId())),
                    jsonStringPerson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}

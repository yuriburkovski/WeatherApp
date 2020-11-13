package com.example.weatherapp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class WeatherChecker {

    private String miasto;

    private HttpsURLConnection connection;

    public WeatherChecker(String miasto) throws IOException {
        this.miasto = miasto;
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + miasto + ",pl&APPID=1d4cbd5eae1fbb37f92be746f37e4615&units=metric";
    }

    public static JSONObject getJSONObjectFromURL(String urlString) throws IOException, JSONException {
        HttpURLConnection urlConnection = null;
        URL url = new URL(urlString);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setReadTimeout(10000 /* milliseconds */ );
        urlConnection.setConnectTimeout(15000 /* milliseconds */ );
        urlConnection.setDoOutput(true);
        urlConnection.connect();

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();

        String jsonString = sb.toString();
        System.out.println("JSON: " + jsonString);

        return new JSONObject(jsonString);
    }


    public String run(String miasto) throws IOException, JSONException {
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + miasto + ",pl&APPID=1d4cbd5eae1fbb37f92be746f37e4615&units=metric";

        return String.valueOf(getJSONObjectFromURL(url));
    }
}

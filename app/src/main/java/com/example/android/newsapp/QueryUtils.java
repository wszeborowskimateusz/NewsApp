package com.example.android.newsapp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz on 17/09/2017.
 * Class to download data from a server and return List<News> with downloaded news
 */

public class QueryUtils {

    public static final String LOG_TAG = MainActivity.class.getName();
    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * @return an {@link List<News>} object to represent a single news.
     * @param requestUrl is a String url to perform a HTTP Request
     */
    public static List<News> fetchBooksData(String requestUrl) {

        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        // Extract relevant fields from the JSON response and create an {@link List<News>} object
        List<News> news = extractFeatureFromJson(jsonResponse);

        // Return the {@link News}
        return news;
    }

    /**
     * @return  a list of {@link News} objects that has been built up from
     * parsing the given JSON response.
     * @param newsJSON is a JSON String from witch we will create a list of news
     */
    private static List<News> extractFeatureFromJson(String newsJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(newsJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding news to
        List<News> news = new ArrayList<>();

        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(newsJSON);

            JSONObject response = baseJsonResponse.getJSONObject("response");

            JSONArray newsArray = response.getJSONArray("results");

            for(int i = 0; i<newsArray.length(); i++){

                // Get a single book at position i within the list of books
                JSONObject currentNewsInfo = newsArray.getJSONObject(i);

                String sectionName = "";
                if(currentNewsInfo.has("sectionName")) {
                     sectionName = currentNewsInfo.getString("sectionName");
                }

                String title = "";
                if(currentNewsInfo.has("webTitle")) {
                    title = currentNewsInfo.getString("webTitle");
                }

                String url = "";
                if(currentNewsInfo.has("webUrl")) {
                    url = currentNewsInfo.getString("webUrl");
                }

                news.add(new News(title,sectionName,url));
            }


        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of news
        return news;
    }

    /**
     * @return new URL object from the given string URL.
     * @param stringUrl is an String url to be converted into {@link URL} Object
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            Log.e(LOG_TAG, "Error with creating URL", exception);
            return null;
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     * @return JSON String file to extract information about News
     * @param url is an URL to perform an HTTP Request
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        //If a url is null do not send request
        if(url == null)return jsonResponse;

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();
            if(urlConnection.getResponseCode()>=200 && urlConnection.getResponseCode()<400) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
            else throw new IOException();
        } catch (IOException e) {
            Log.e(LOG_TAG,"IOException",e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // function must handle java.io.IOException here
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     * @return The JSON String built from InputStream
     * @param inputStream the ImputStream from witch we will create a String object
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

}

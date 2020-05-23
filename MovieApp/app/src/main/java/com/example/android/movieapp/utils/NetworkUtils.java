package com.example.android.movieapp.utils;

import android.net.Uri;
import android.util.Log;

import com.example.android.movieapp.model.Movie;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    private final static String API_KEY = ""; // TODO: remove before commit
    private final static String API_BASE_URL = "https://api.themoviedb.org/";
    private final static String VERSION = "3";

    private static URL buildUrl(String path) {

        Uri builtUri = Uri.parse(API_BASE_URL).buildUpon()
                .encodedPath(VERSION + path)
                .appendQueryParameter("api_key", API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    private static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static List<Movie> makeRequest(String path) {

        URL tmdbRequestUrl = NetworkUtils.buildUrl(path);

        try {
            String jsonMoviesResponse = NetworkUtils
                    .getResponseFromHttpUrl(tmdbRequestUrl);
            return JsonUtils.parseMoviesJson(jsonMoviesResponse);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "Make request " + e.getMessage(), e);
            return null;
        }
    }
}

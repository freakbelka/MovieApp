package com.example.android.movieapp.utils;

import android.net.Uri;

import com.example.android.movieapp.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private final static String IMAGE_BASE_URL = "http://image.tmdb.org/t/p";

    private final static String IMAGE_SIZE = "/w780";
    private final static String DEFAULT_SIZE_IMAGE_URL = IMAGE_BASE_URL + IMAGE_SIZE;

    public static List<Movie> parseMoviesJson(String json) {
        List<Movie> parsedMovies = new ArrayList<>();
        try {
            JSONObject moviesJson = new JSONObject(json);
            JSONArray movies = moviesJson.getJSONArray("results");
            for (int i = 0; i < movies.length(); i++) {
                parsedMovies.add(parseMovieJson(movies.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return parsedMovies;
    }

    private static Movie parseMovieJson(JSONObject movie) throws JSONException {

        JSONArray jsonGenreIds = movie.getJSONArray("genre_ids");
        List<Integer> parsedGenres = new ArrayList<>();
        for (int i = 0; i < jsonGenreIds.length(); i++) {
            parsedGenres.add((Integer) jsonGenreIds.get(i));
        }

        return new Movie(movie.getInt("id"),
                movie.getString("title"),
                movie.getString("overview"),
                movie.getString("release_date"),
                DEFAULT_SIZE_IMAGE_URL + movie.getString("poster_path"),
                movie.getString("backdrop_path"),
                movie.getString("original_language"),
                movie.getString("original_title"),
                movie.getDouble("popularity"),
                parsedGenres,
                movie.getInt("vote_count"),
                movie.getInt("vote_average"),
                movie.getBoolean("adult"),
                movie.getBoolean("video")
        );
    }
}

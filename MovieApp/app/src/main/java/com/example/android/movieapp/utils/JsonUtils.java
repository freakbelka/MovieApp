package com.example.android.movieapp.utils;

import com.example.android.movieapp.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

class JsonUtils {

    private static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p";
    private static final String IMAGE_SIZE = "/w780";
    private static final String DEFAULT_SIZE_IMAGE_URL = IMAGE_BASE_URL + IMAGE_SIZE;
    private static final String RESULTS = "results";

    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String OVERVIEW = "overview";
    private static final String GENRE_IDS = "genre_ids";
    private static final String RELEASE_DATE = "release_date";
    private static final String POSTER_PATH = "poster_path";
    private static final String BACKDROP_PATH = "backdrop_path";
    private static final String ORIGINAL_LANGUAGE = "original_language";
    private static final String ORIGINAL_TITLE = "original_title";
    private static final String POPULARITY = "popularity";
    private static final String VOTE_COUNT = "vote_count";
    private static final String VOTE_AVERAGE = "vote_average";
    private static final String ADULT = "adult";
    private static final String VIDEO = "video";

    static List<Movie> parseMoviesJson(String json) {
        List<Movie> parsedMovies = new ArrayList<>();
        try {
            JSONObject moviesJson = new JSONObject(json);
            JSONArray movies = moviesJson.getJSONArray(RESULTS);
            for (int i = 0; i < movies.length(); i++) {
                parsedMovies.add(parseMovieJson(movies.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return parsedMovies;
    }

    private static Movie parseMovieJson(JSONObject movie) throws JSONException {

        JSONArray jsonGenreIds = movie.getJSONArray(GENRE_IDS);
        List<Integer> parsedGenres = new ArrayList<>();
        for (int i = 0; i < jsonGenreIds.length(); i++) {
            parsedGenres.add((Integer) jsonGenreIds.get(i));
        }

        return new Movie(movie.getInt(ID),
                movie.optString(TITLE),
                movie.optString(OVERVIEW),
                movie.optString(RELEASE_DATE),
                DEFAULT_SIZE_IMAGE_URL + movie.optString(POSTER_PATH),
                movie.optString(BACKDROP_PATH),
                movie.optString(ORIGINAL_LANGUAGE),
                movie.optString(ORIGINAL_TITLE),
                movie.optDouble(POPULARITY),
                parsedGenres,
                movie.optInt(VOTE_COUNT),
                movie.optInt(VOTE_AVERAGE),
                movie.optBoolean(ADULT),
                movie.optBoolean(VIDEO)
        );
    }
}

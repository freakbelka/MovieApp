package com.example.android.movieapp.service;

import com.example.android.movieapp.model.Movie;
import com.example.android.movieapp.utils.UrlManager;

import java.util.List;

import static com.example.android.movieapp.utils.NetworkUtils.makeRequest;

public class TMDBAPI {

    public static List<Movie> getPopularMovies() {
        return makeRequest(UrlManager.POPULAR_MOVIES.getPath());
    }

    public static List<Movie> getTopRatedMovies() {
        return makeRequest(UrlManager.TOP_RATED.getPath());
    }

}

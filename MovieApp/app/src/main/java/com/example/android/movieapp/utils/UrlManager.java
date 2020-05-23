package com.example.android.movieapp.utils;

public enum UrlManager {
    MOVIE("/movie"),
    POPULAR_MOVIES(MOVIE.path + "/popular"),
    TOP_RATED(MOVIE.path + "/top_rated");

    public String getPath() {
        return path;
    }

    private String path;

    UrlManager(String path) {
        this.path = path;
    }
}

package com.example.android.movieapp.utils;

public enum UrlManager {
    MOVIE("/movie"),
    POPULAR_MOVIES(MOVIE.path + "/popular"),
    TOP_RATED(MOVIE.path + "/top_rated"),
    VIDEOS(MOVIE.path + "{movieId}/videos"),
    REVIEWS(MOVIE.path + "{movieId}/reviews");

    public String getPath() {
        return path;
    }

    private String path;

    UrlManager(String path) {
        this.path = path;
    }
}

package com.example.android.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.movieapp.model.Movie;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    TextView mMovieTitleTextView;
    TextView mMovieReviewTextView;
    TextView mReleaseDateTextView;
    ImageView mMoviePosterImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        Movie movie = intent.getParcelableExtra("movie");
        mMovieTitleTextView = (TextView) findViewById(R.id.tv_movie_title);
        mMovieTitleTextView.setText(movie.getTitle());
        mMovieReviewTextView = (TextView) findViewById(R.id.tv_movie_overview);
        mMovieReviewTextView.setText(movie.getOverview());
        mReleaseDateTextView = (TextView) findViewById(R.id.tv_release_date);
        mReleaseDateTextView.setText(movie.getReleaseDate());
        mMoviePosterImageView = (ImageView) findViewById(R.id.iv_poster);
        Picasso.with(this).load(movie.getPosterPath()).into(mMoviePosterImageView);


    }
}

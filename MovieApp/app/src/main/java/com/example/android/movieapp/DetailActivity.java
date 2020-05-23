package com.example.android.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.movieapp.model.Movie;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        Movie movie = intent.getParcelableExtra("movie");
        TextView mMovieTitleTextView = (TextView) findViewById(R.id.tv_movie_title);
        mMovieTitleTextView.setText(movie.getTitle());
        TextView mMovieReviewTextView = (TextView) findViewById(R.id.tv_movie_overview);
        mMovieReviewTextView.setText(movie.getOverview());
        TextView mReleaseDateTextView = (TextView) findViewById(R.id.tv_release_date);
        mReleaseDateTextView.setText(movie.getReleaseDate());
        TextView mVoteAverageTextView = (TextView) findViewById(R.id.tv_user_rating);
        mVoteAverageTextView.setText(Integer.toString(movie.getVoteAverage()));
        ImageView mMoviePosterImageView = (ImageView) findViewById(R.id.iv_poster);
        Picasso.with(this)
                .load(movie.getPosterPath())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.noimage)
                .into(mMoviePosterImageView);
    }
}

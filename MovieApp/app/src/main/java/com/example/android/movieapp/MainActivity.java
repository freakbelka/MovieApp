package com.example.android.movieapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.movieapp.model.Movie;
import com.example.android.movieapp.service.TMDBAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieAdapter.ListItemClickListener {

    private RecyclerView mMoviesRecyclerView;
    private MovieAdapter mMovieAdapter;
    private int mNumberOfColumns = 2;
    private List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMoviesRecyclerView = (RecyclerView) findViewById(R.id.rv_movies);


        if(savedInstanceState == null || !savedInstanceState.containsKey("movies")) {
            loadPopularMoviesData();;
        }
        else {
            movies = savedInstanceState.getParcelableArrayList("movies");
            mMovieAdapter = new MovieAdapter(movies, this);
            mMoviesRecyclerView.setAdapter(mMovieAdapter);
        }
        mMoviesRecyclerView.setLayoutManager(new GridLayoutManager(this, mNumberOfColumns));
        mMoviesRecyclerView.setHasFixedSize(true);
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(this, R.dimen.item_offset);
        mMoviesRecyclerView.addItemDecoration(itemDecoration);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();

        switch (itemId) {
            /*
             * When you click the reset menu item, we want to start all over
             * and display the pretty gradient again. There are a few similar
             * ways of doing this, with this one being the simplest of those
             * ways. (in our humble opinion)
             */
            case R.id.favourite_action:
                // COMPLETED (14) Pass in this as the ListItemClickListener to the GreenAdapter constructor

                return true;
            case R.id.popular_action:
                return true;
            case R.id.top_rated_action:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void loadPopularMoviesData() {
        new FetchPopularMoviesTask().execute();
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("movie", movies.get(clickedItemIndex));
        startActivity(intent);


    }


    public class FetchPopularMoviesTask extends AsyncTask<String, Void, List<Movie>> {

        @Override
        protected List<Movie> doInBackground(String... params) {
            movies = TMDBAPI.getPopularMovies();
            return movies;
        }

        @Override
        protected void onPostExecute(List<Movie> movies) {
            mMovieAdapter = new MovieAdapter(movies, MainActivity.this);
            mMoviesRecyclerView.setAdapter(mMovieAdapter);

        }
    }

}

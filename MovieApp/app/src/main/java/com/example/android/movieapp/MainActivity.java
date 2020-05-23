package com.example.android.movieapp;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.movieapp.model.Movie;
import com.example.android.movieapp.utils.UrlManager;

import java.util.List;

import static com.example.android.movieapp.utils.NetworkUtils.makeRequest;

public class MainActivity extends AppCompatActivity implements MovieAdapter.ListItemClickListener {

    private RecyclerView mMoviesRecyclerView;

    private MovieAdapter mMovieAdapter;

    private ProgressBar mLoadingIndicator;

    private TextView mErrorMessageDisplay;

    private ImageView mNoInternetImageView;

    private List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMoviesRecyclerView = (RecyclerView) findViewById(R.id.rv_movies);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);
        mNoInternetImageView = (ImageView) findViewById(R.id.iv_no_internet_icon);

        if (savedInstanceState == null || !savedInstanceState.containsKey("movies")) {
            loadPopularMovies();
        } else {
            movies = savedInstanceState.getParcelableArrayList("movies");
            mMovieAdapter = new MovieAdapter(movies, this);
            mMoviesRecyclerView.setAdapter(mMovieAdapter);
        }
        mMoviesRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mMoviesRecyclerView.setHasFixedSize(true);
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
            case R.id.popular_action:
                loadPopularMovies();
                return true;
            case R.id.top_rated_action:
                loadTopRatedMovies();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadPopularMovies() {
        new FetchMoviesTask().execute(UrlManager.POPULAR_MOVIES.getPath());
    }

    private void loadTopRatedMovies() {
        new FetchMoviesTask().execute(UrlManager.TOP_RATED.getPath());
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("movie", movies.get(clickedItemIndex));
        startActivity(intent);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        if (connectivityManager == null) return false;
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    private class FetchMoviesTask extends AsyncTask<String, Void, List<Movie>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showLoading();
        }

        @Override
        protected List<Movie> doInBackground(String... params) {
            String apiPath = params[0];
            movies = makeRequest(apiPath);
            return movies;
        }

        @Override
        protected void onPostExecute(List<Movie> movies) {
            if (isNetworkAvailable() && movies != null && movies.size() != 0) {
                showData();
                mMovieAdapter = new MovieAdapter(movies, MainActivity.this);
                mMoviesRecyclerView.setAdapter(mMovieAdapter);
            } else {
                showErrorMessage();
            }
        }
    }

    private void showLoading() {
        mMoviesRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mLoadingIndicator.setVisibility(View.VISIBLE);
        mNoInternetImageView.setVisibility(View.INVISIBLE);
    }

    private void showErrorMessage() {
        mMoviesRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        mNoInternetImageView.setVisibility(View.VISIBLE);
    }

    private void showData() {
        mMoviesRecyclerView.setVisibility(View.VISIBLE);
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        mNoInternetImageView.setVisibility(View.INVISIBLE);
    }
}

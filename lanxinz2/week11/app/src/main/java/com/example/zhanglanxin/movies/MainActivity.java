package com.example.zhanglanxin.movies;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String urlString =
            "https://api.themoviedb.org/3/movie/popular?api_key=" + MovieApi.API_KEY;
    public static final int pageNum = 11;
    public static final int totalMovies = 200;
    // mMovieAdapter connects mMovie to mRecyclerView
    private RecyclerView mRecyclerView;
    private ArrayList<Movie> mMovie = new ArrayList<>();
    private MovieAdapter mMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // Initially, mArticles is an empty ArrayList.  We populate it with NewsAsyncTask.
        mMovieAdapter = new MovieAdapter(mMovie);
        mRecyclerView.setAdapter(mMovieAdapter);

        // Construct URL and request data...
        try {
            URL[] urls = new URL[pageNum];
            //page starts from 1
            for (int i = 1; i < pageNum; i++) {
                urls[i] = (new URL(urlString + "&page=" + i));
            }
            // Fetch the news on a background thread; it will populate mMovie.
            new MoviesAsyncTask(this).execute(urls);

        } catch (MalformedURLException e) {
            // OK to dump stack trace here.  Should never fire once we've debugged app.
            e.printStackTrace();
        }
    }

    /**
     * This class (1) takes a URL, (2) makes an HTTP request, (3) parses the resulting JSON
     * into a MovieCollection, and (4) returns the array of movies
     * This class requires a Context in its constructor so that we can make Toasts.
     */
    public class MoviesAsyncTask extends AsyncTask<URL, Void, Movie[]> {

        Context context;

        public MoviesAsyncTask(Context context) {
            this.context = context;
        }

        // This function is done on the background thread.
        @Override
        protected Movie[] doInBackground(URL... params) {
            ArrayList<MovieCollection> movieCollections = new ArrayList<>();
            try {
                for (int i = 1; i < pageNum; i++) {
                    URL url = params[i];
                    URLConnection connection = url.openConnection();
                    connection.connect();

                    InputStream inStream = connection.getInputStream();
                    InputStreamReader inStreamReader = new InputStreamReader(inStream, Charset.forName("UTF-8"));

                    Gson gson = new Gson();
                    movieCollections.add(gson.fromJson(inStreamReader, MovieCollection.class));
                }
                Movie[] result = new Movie[totalMovies];
                int i = 0;
                for (MovieCollection movieCollection : movieCollections) {
                    for (Movie movie : movieCollection.getResult()) {
                        result[i++] = movie;
                    }
                }
                return result;

            } catch (IOException e) {
                Log.d("MoviesAsyncTask", "failed to get data from network", e);
                return null;
            }
        }

        // This code is run on the UI thread
        @Override
        protected void onPostExecute(Movie[] movies) {

            // Pop up a Toast if we failed to get data.
            if (movies == null) {
                Toast.makeText(context, "Failed to get network data", Toast.LENGTH_LONG).show();
                return;
            }

            // Empty the ArrayList of movies (mMovies) and fill it with the ones we loaded.

            mMovie.clear();
            for (Movie movie : movies) {
                Log.d("NEWS", movie.getTitle());

                mMovie.add(movie);
            }

            // Poke mMovieAdapter to let it know that its data changed.
            mMovieAdapter.notifyDataSetChanged();
        }
    }
}
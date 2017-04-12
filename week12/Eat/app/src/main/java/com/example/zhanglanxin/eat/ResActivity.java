package com.example.zhanglanxin.eat;

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

public class ResActivity extends AppCompatActivity {
    public static final String urlString =
            "https://maps.googleapis.com/maps/api/place/textsearch/json?query=restaurants+in+Champaign&key="
                    + GoogleApi.API_KEY;
    private RecyclerView mRecyclerView;
    private ArrayList<Restaurant> mRestaurant = new ArrayList<>();
    private RestaurantAdapter mRestaurantAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // Initially, mArticles is an empty ArrayList.  We populate it with NewsAsyncTask.
        mRestaurantAdapter = new RestaurantAdapter(mRestaurant);
        mRecyclerView.setAdapter(mRestaurantAdapter);

        // Construct URL and request data...
        try {
            URL url = new URL(urlString);

            // Fetch the news on a background thread; it will populate mArticles.
            new RestaurantsAsyncTask(this).execute(url);

        } catch (MalformedURLException e) {
            // OK to dump stack trace here.  Should never fire once we've debugged app.
            e.printStackTrace();
        }
    }


    /**
     * This class (1) takes a URL, (2) makes an HTTP request, (3) parses the resulting JSON
     * into a RestaurantCollection, and (4) returns the array of Restaurants
     * This class requires a Context in its constructor so that we can make Toasts.
     */
    public class RestaurantsAsyncTask extends AsyncTask<URL, Void, Restaurant[]> {

        Context context;

        public RestaurantsAsyncTask(Context context) {
            this.context = context;
        }

        // This function is done on the background thread.
        @Override
        protected Restaurant[] doInBackground(URL... params) {

            try {
                URL url = params[0];
                URLConnection connection = url.openConnection();
                connection.connect();

                InputStream inStream = connection.getInputStream();
                InputStreamReader inStreamReader = new InputStreamReader(inStream, Charset.forName("UTF-8"));

                Gson gson = new Gson();
                RestaurantCollection restaurantCollection = gson.fromJson(inStreamReader, RestaurantCollection.class);
                return restaurantCollection.getResults();

            } catch (IOException e) {
                Log.d("NewsAsyncTask", "failed to get data from network", e);
                return null;
            }
        }


        // This code is run on the UI thread
        @Override
        protected void onPostExecute(Restaurant[] Restaurants) {

            // Pop up a Toast if we failed to get data.
            if (Restaurants == null) {
                Toast.makeText(context, "Failed to get network data", Toast.LENGTH_LONG).show();
                return;
            }

            // Empty the ArrayList of Restaurants (mRestaurants) and fill it with the ones we loaded.

            mRestaurant.clear();
            for (Restaurant Restaurant : Restaurants) {
                Log.d("RESTAURANT", Restaurant.getName());

                mRestaurant.add(Restaurant);
            }

            // Poke mRestaurantAdapter to let it know that its data changed.
            mRestaurantAdapter.notifyDataSetChanged();
        }
    }
}

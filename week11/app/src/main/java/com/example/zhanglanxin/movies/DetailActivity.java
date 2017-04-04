package com.example.zhanglanxin.movies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;


/**
 * Created by zhanglanxin on 4/3/17.
 */

public class DetailActivity extends AppCompatActivity {
    private TextView mTitleTextView;
    private TextView mLanguageTextView;
    private TextView mBodyTextView;
    private ImageView mImageView;
    public static final String urlToImage = "http://image.tmdb.org/t/p/w500//";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // get references to the views.
        mTitleTextView = (TextView) findViewById(R.id.titleTextView);
        mLanguageTextView = (TextView) findViewById(R.id.languageTextView);
        mBodyTextView = (TextView) findViewById(R.id.bodyTextView);
        mImageView = (ImageView) findViewById(R.id.imageView);

        Intent intent = getIntent();

        /* Code if we were passing this as individual extras (don't do this) */
//        String title = intent.getStringExtra(NewsAdapter.TITLE);
//        String author = intent.getStringExtra(NewsAdapter.AUTHOR);
//        String body = intent.getStringExtra(NewsAdapter.BODY);
//        String imgURL = intent.getStringExtra(NewsAdapter.IMG_URL);
//        mTitleTextView.setText(title);
//        mAuthorTextView.setText(author);
//        mBodyTextView.setText(body);
//        Picasso.with(mImageView.getContext()).load(imgURL).into(mImageView);

        /* Code if we were pass a movie as JSON (don't do this) */
//        String json = intent.getStringExtra(NewsAdapter.JSON);
//        Gson gson = new Gson();
//        Movie movie = gson.fromJson(json, Movie.class);

        /* Code for receiving the Parcelable movie */
        Movie movie = intent.getParcelableExtra(MovieAdapter.MOVIE);

        mTitleTextView.setText(movie.getTitle());
        mLanguageTextView.setText(movie.getOriginal_language());
        mBodyTextView.setText(movie.getOverview());
        Picasso.with(mImageView.getContext()).load(urlToImage + movie.getBackdrop_path()).into(mImageView);
    }

}

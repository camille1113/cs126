package com.example.zhanglanxin.movies;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by zhanglanxin on 3/27/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    ArrayList<Movie> movies;

    public MovieAdapter(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    //the base url fot poster path
    public static final String urlToImage = "http://image.tmdb.org/t/p/w500//";

    /**
     * This function is called only enough times to cover the screen with views.  After
     * that point, it recycles the views when scrolling is done.
     *
     * @param parent   the intended parent object (our RecyclerView)
     * @param viewType unused in our function (enables having different kinds of views in the same RecyclerView)
     * @return the new ViewHolder we allocate
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // a LayoutInflater turns a layout XML resource into a View object.
        final View moviesListItem = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.movies_list_item, parent, false);
        return new ViewHolder(moviesListItem);
    }


    /**
     * This function gets called each time a ViewHolder needs to hold data for a different
     * position in the list.  We don't need to create any views (because we're recycling), but
     * we do need to update the contents in the views.
     *
     * @param holder   the ViewHolder that knows about the Views we need to update
     * @param position the index into the array of Movies
     */

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = movies.get(position);

        holder.titleView.setText(movie.getTitle());
        holder.authorView.setText(movie.getRelease_date());
        Picasso.with(holder.imageView.getContext()).load(urlToImage + movie.getPoster_path()).into(holder.imageView);
    }

    /**
     * RecyclerView wants to know how many list items there are, so it knows when it gets to the
     * end of the list and should stop scrolling.
     *
     * @return the number of Movies in the array.
     */
    @Override
    public int getItemCount() {
        return movies.size();
    }

    /**
     * A ViewHolder class for our adapter that 'caches' the references to the
     * subviews, so we don't have to look them up each time.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleView;
        public TextView authorView;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            titleView = (TextView) itemView.findViewById(R.id.titleTextView);
            authorView = (TextView) itemView.findViewById(R.id.release_date);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}

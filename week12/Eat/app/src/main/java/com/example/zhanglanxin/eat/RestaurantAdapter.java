package com.example.zhanglanxin.eat;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by zhanglanxin on 4/11/17.
 */

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {
    ArrayList<Restaurant> restaurants;

    public RestaurantAdapter(ArrayList<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
    public static final String imgUrlString1 =
            "https://maps.googleapis.com/maps/api/place/photo?photoreference=";
    public static final String imgUrlString2 = "&sensor=false&maxheight=";
    public static final String imgUrlString3 = "&maxwidth=";
    public static final String imgUrlString4 = "MAX_WIDTH&key=" + GoogleApi.API_KEY;
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
        final View restaurantsListItem = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.restaurants_list_item, parent, false);
        return new ViewHolder(restaurantsListItem);
    }


    /**
     * This function gets called each time a ViewHolder needs to hold data for a different
     * position in the list.  We don't need to create any views (because we're recycling), but
     * we do need to update the contents in the views.
     *
     * @param holder   the ViewHolder that knows about the Views we need to update
     * @param position the index into the array of restaurants
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Restaurant restaurant = restaurants.get(position);

        holder.nameView.setText(restaurant.getName());
        holder.ratingView.setText("Rating: " + restaurant.getRating());
        holder.addressView.setText("Address: " + restaurant.getFormatted_address());
        holder.openView.setText(restaurant.getOpening_hours().getOpen());
        Picasso.with(holder.imageView.getContext())
                .load(imgUrlString1 + restaurant.getPhoto().getPhoto_reference() +
                imgUrlString2 + restaurant.getPhoto().getHeight() +
                imgUrlString3 + restaurant.getPhoto().getWidth() +
                imgUrlString4).into(holder.imageView);
        // Attach a click listener that launches a new Activity
//        holder.view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                // Code for launching an Explicit Intent to go to another Activity in
//                // the same App.
//                Intent intent = new Intent(v.getContext(), ResActivity.class);
//
//                intent.putExtra(RESTAURANT, restaurant);
//
//                v.getContext().startActivity(intent);
//            }
//        });
    }

    /**
     * RecyclerView wants to know how many list items there are, so it knows when it gets to the
     * end of the list and should stop scrolling.
     *
     * @return the number of restaurants in the array.
     */
    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    /**
     * A ViewHolder class for our adapter that 'caches' the references to the
     * subviews, so we don't have to look them up each time.
     * <p>
     * Added the View 'view' so we'd have something to attach a click listener to.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        //public View view;
        public TextView nameView;
        public TextView ratingView;
        public TextView addressView;
        public TextView openView;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            //view = itemView;
            nameView = (TextView) itemView.findViewById(R.id.nameTextView);
            ratingView = (TextView) itemView.findViewById(R.id.rateTextView);
            addressView = (TextView) itemView.findViewById(R.id.addressTextView);
            openView = (TextView) itemView.findViewById(R.id.open_hour);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);

        }
    }
}


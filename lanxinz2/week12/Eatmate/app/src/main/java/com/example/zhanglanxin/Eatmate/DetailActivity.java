package com.example.zhanglanxin.Eatmate;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailActivity extends AppCompatActivity {


    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mRestaurantReference;

    private RecyclerView mRecyclerView;

    private static final String TAG = "DataBase";
    private static final String REQUIRED = "Required";

    private TextView mTitleField;
    private EditText mBodyField;
    private FloatingActionButton mSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mTitleField = (TextView) findViewById(R.id.field_title);
        mBodyField = (EditText) findViewById(R.id.field_body);
        mSubmitButton = (FloatingActionButton) findViewById(R.id.fab_submit_post);

        // get references to the views.
        mTitleField = (TextView) findViewById(R.id.field_title);
        Intent intent = getIntent();
        final Restaurant restaurant = intent.getParcelableExtra(RestaurantAdapter.RESTAURANT);
        mTitleField.setText("Customers in "  + restaurant.getName() + ": ");

        mRestaurantReference = database.getReference(restaurant.getName());

        //adapter
        FirebaseRecyclerAdapter<String, UsernameViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<String, UsernameViewHolder>(String.class, R.layout.names,
                        UsernameViewHolder.class, mRestaurantReference.child("Customers")){
                    @Override
                    protected void populateViewHolder(UsernameViewHolder viewHolder, String model, int position) {
                        viewHolder.mTextView.setText(model);
                    }
                };
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);


        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mBodyField.getText().toString();
                mRestaurantReference.child("Customers").push().setValue(username);

                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, MapsActivity.class));
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public static class UsernameViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;

        public UsernameViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.nameTextView);
        }

    }

}

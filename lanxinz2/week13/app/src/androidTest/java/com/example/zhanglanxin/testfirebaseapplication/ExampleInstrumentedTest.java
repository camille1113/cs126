package com.example.zhanglanxin.testfirebaseapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference names = firebaseDatabase.getReference("name");
    int numberOfChildrenAdd;
    int numberOfChildrenRemoved;

    @Test
    public void writeValueTest() throws Exception {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        numberOfChildrenAdd = 3;
        numberOfChildrenRemoved = 3;
        names.push().setValue("test").addOnCompleteListener(new OnCompleteListener<Void>(){
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                countDownLatch.countDown();
            }
        });
        names.child("Camille").removeValue().addOnCompleteListener(new OnCompleteListener<Void>(){
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                countDownLatch.countDown();
            }
        });
        names.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("CHILD",dataSnapshot.getKey());
                numberOfChildrenAdd++;
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d("CHILD",dataSnapshot.getKey());
                numberOfChildrenRemoved--;
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        countDownLatch.await(1, TimeUnit.SECONDS);
        assertEquals(4,numberOfChildrenAdd);
        assertEquals(2,numberOfChildrenRemoved);
    }

    @Test
    public void readValueTest() throws Exception{
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        names.child("-Ki316gR0G2YeKr9sVjZ").addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                assertEquals("CAMILLE",
                        dataSnapshot.getValue(String.class));
                countDownLatch.countDown();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        countDownLatch.await(1, TimeUnit.SECONDS);

    }
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.zhanglanxin.testfirebaseapplication", appContext.getPackageName());
    }
}

package com.education.next;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main2Activity extends AppCompatActivity {
    AdView adView;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private DatabaseReference db = databaseReference.child("image");
    ImageView img;
    Button back;
    Button next;
    int i=0;
    int[] list = new int[]{

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        img= (ImageView) findViewById(R.id.img);
        back= (Button) findViewById(R.id.button2);
        next = (Button) findViewById(R.id.button);
        adView = (AdView) findViewById(R.id.adView);
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    list.add(new int[](dataSnapshot.child("data").getValue()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        MobileAds.initialize(this,"ca-app-pub-9090663640519392~2519966685");
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                nexttt(v);
            }

        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prevv(v);
            }
        });

    }
    public void nexttt(View view){
        if(i<30) {
            i++;
            img.setImageResource(list[i]);

        }else{
            Toast.makeText(Main2Activity.this, "Please go back :)", Toast.LENGTH_LONG).show();
        }

    }

    public void prevv(View view){
        if(i>0) {
            i--;
            img.setImageResource(list[i]);
        }else{
            Toast.makeText(this, "Please go forward :)", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        })
    }
}





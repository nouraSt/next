package com.education.next;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    AdView adView;
    InterstitialAd interstitialAd;
    ImageView img;
    Button back;
    Button next;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img= (ImageView) findViewById(R.id.img);
        back= (Button) findViewById(R.id.button2);
        next = (Button) findViewById(R.id.button);
        adView = (AdView) findViewById(R.id.adView);

        MobileAds.initialize(this,"ca-app-pub-9090663640519392~2519966685");
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        interstitialAd.loadAd(new Builder().build());
        interstitialAd.setAdListener(new AdListener() {
                                          @Override
                                          public void onAdLoaded() {
                                              // Code to be executed when an ad finishes loading.
                                          }

                                          @Override
                                          public void onAdFailedToLoad(int errorCode) {
                                              // Code to be executed when an ad request fails.
                                          }

                                          @Override
                                          public void onAdOpened() {
                                              // Code to be executed when the ad is displayed.
                                          }

                                          @Override
                                          public void onAdClicked() {
                                              // Code to be executed when the user clicks on an ad.
                                          }

                                          @Override
                                          public void onAdLeftApplication() {
                                              // Code to be executed when the user has left the app.
                                          }

                                          @Override
                                          public void onAdClosed() {

                                            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                                            startActivity(intent);

                                          }
                                      });

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                nextt(v);
            }

        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // prev(v);
            }
        });

    }

    public void nextt(View view){
        if(interstitialAd.isLoaded()){
            interstitialAd.show();
        }
    }

    //public void prev(View view){
       // if(i>0) {
           // i--;
           // img.setImageResource(list[i]);
       // }else{
          //  Toast.makeText(this, "Please go forward :)", Toast.LENGTH_LONG).show();

       // }

}

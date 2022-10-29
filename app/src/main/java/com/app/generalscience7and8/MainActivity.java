package com.app.generalscience7and8;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;

import android.view.ViewTreeObserver;
import android.view.WindowMetrics;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;


public class MainActivity extends AppCompatActivity {

    private static final String AD_UNIT_ID = "ca-app-pub-7070396777212212/3875789613";
    private AdView adView;
    private FrameLayout adContainerView;
    private boolean initialLayoutComplete = false;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView button = findViewById(R.id.imageButton);
        ImageView button1 = findViewById(R.id.imageButton2);
        adView = new AdView(this);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) { }
        });
        button.setOnClickListener(view -> {
            Intent intent= new Intent(getApplicationContext(),MainActivity2.class);
            intent.putExtra("key_position",0);
            startActivity(intent);
        });
        button1.setOnClickListener(view -> {
            Intent intent= new Intent(getApplicationContext(),MainActivity2.class);
            intent.putExtra("key_position",1);
            startActivity(intent);
        });

        adContainerView = findViewById(R.id.adContainer);
        adView = new AdView(this);
        adContainerView.addView(adView);
        adContainerView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        if (!initialLayoutComplete) {
                            initialLayoutComplete = true;
                            loadBanner();
                        }
                    }
                });
    }


    @RequiresApi(api = Build.VERSION_CODES.R)
    private void loadBanner() {
        adView.setAdUnitId(AD_UNIT_ID);

        AdSize adSize = getAdSize();
        adView.setAdSize(adSize);


        AdRequest adRequest =
                new AdRequest.Builder().build();

        // Start loading the ad in the background.
        adView.loadAd(adRequest);
    }


    // Determine the screen width (less decorations) to use for the ad width.

    private AdSize getAdSize() {
        // Determine the screen width (less decorations) to use for the ad width.
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float density = outMetrics.density;

        float adWidthPixels = adContainerView.getWidth();

        // If the ad hasn't been laid out, default to the full screen width.
        if (adWidthPixels == 0) {
            adWidthPixels = outMetrics.widthPixels;
        }


        int adWidth = (int) (adWidthPixels / density);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_share) {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);

            // type of the content to be shared
            sharingIntent.setType("text/plain");

            // Body of the content
            String shareBody = "https://play.google.com/store/apps/details?id=com.app.grade";

            // subject of the content. you can share anything
            String shareSubject = "Hey, I'm using Grade 7 and 8 General Science Textbook download it on Playstore";

            // passing body of the content
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);

            // passing subject of the content
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
            startActivity(Intent.createChooser(sharingIntent, "Share using"));
        }
        if(item.getItemId()==R.id.privacy) {


            Uri uri = Uri.parse("https://divvy-upp.web.app/privacypolicy_2");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);



        }
        else {// If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            return super.onOptionsItemSelected(item);
        }





        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }


    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }

    /** Called before the activity is destroyed */
    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }


}
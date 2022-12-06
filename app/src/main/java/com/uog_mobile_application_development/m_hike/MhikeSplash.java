package com.uog_mobile_application_development.m_hike;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MhikeSplash extends AppCompatActivity {

    private static int MHIKE_SPLASH_TIME = 5000;

    //Variables
    ImageView backgroundImage;
    TextView poweredByText;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.mhike_splashscreen);


        //Animation applied
        Animation leftAnim, floorAnim;

        //Splashscreen Hooks -elements
        backgroundImage = findViewById(R.id.background_image);
        poweredByText = findViewById(R.id.powered_by_text);

        //Animation
        leftAnim = AnimationUtils.loadAnimation(this,R.anim.from_side);
        floorAnim = AnimationUtils.loadAnimation(this,R.anim.from_bottom);

        //Set Animation on Elements
        backgroundImage.setAnimation(leftAnim);
        poweredByText.setAnimation(floorAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(MhikeSplash.this, HomeScreen.class);
                startActivity(intent);
                finish();

            }
        }, MHIKE_SPLASH_TIME);

    }
}

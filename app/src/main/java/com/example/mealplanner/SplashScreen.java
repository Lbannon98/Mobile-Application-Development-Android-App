package com.example.mealplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    Animation animation;
    ImageView image;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);
        title = (TextView) findViewById(R.id.splash_screen_text);
        image = (ImageView) findViewById(R.id.splash_screen_image);

        Thread myThread = new Thread() {
            @Override
            public void run() {
                try {

                    animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
                    title.startAnimation(animation);
                    image.startAnimation(animation);

                    sleep(3000);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };

        myThread.start();
    }
}
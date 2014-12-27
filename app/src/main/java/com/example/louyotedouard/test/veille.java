package com.example.louyotedouard.test;

import android.os.Bundle;
import android.app.Activity;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;
import android.widget.ViewSwitcher.ViewFactory;


public class veille extends ActionBarActivity {

    ImageSwitcher imageSwitcher;

    Animation slide_in_left, slide_out_right;

    int imageResources[] = {R.drawable.image1,R.drawable.image2,R.drawable.image3};

    int curIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veille);

        imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);

        slide_in_left = AnimationUtils.loadAnimation(this,
                android.R.anim.slide_in_left);
        slide_out_right = AnimationUtils.loadAnimation(this,
                android.R.anim.slide_out_right);

        imageSwitcher.setInAnimation(slide_in_left);
        imageSwitcher.setOutAnimation(slide_out_right);

        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {

            @Override
            public View makeView() {

                ImageView imageView = new ImageView(veille.this);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

                FrameLayout.LayoutParams params = new ImageSwitcher.LayoutParams( LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

                imageView.setLayoutParams(params);
                return imageView;

            }
        });

        curIndex = 0;
        imageSwitcher.setImageResource(imageResources[curIndex]);




        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {}

            @Override
            public void onFinish() {

                if (curIndex == imageResources.length - 1) {
                    curIndex = 0;
                    imageSwitcher.setImageResource(imageResources[curIndex]);
                } else {
                    imageSwitcher.setImageResource(imageResources[++curIndex]);
                }
            }
        }.start();
    }
}

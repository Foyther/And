package com.example.nurislam.animation;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    View target, target1,target2,target3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        target = (View) findViewById(R.id.btn_anim);
        target.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnimXml();
            }
        });

        target1 = (View) findViewById(R.id.btn1);
        target2 = (View) findViewById(R.id.btn2);
        target3 = (View) findViewById(R.id.btn3);
        target1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startObjectAnimator();
            }
        });
    }

    public void startViewAnimationProperty(){
        int i = 80;
        target.animate()
                .translationX(i)
                .translationY(i)
                .scaleYBy(2)
                .scaleXBy(2)
                .start();
        i+=i;
    }

    public void startAnimXml(){
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.animation);
        target.startAnimation(anim);
    }

    public void startObjectAnimator(){
        ObjectAnimator animTarget1 = ObjectAnimator.
                ofFloat(target1,"translationY", 50)
                .setDuration(300);
        ObjectAnimator animTarget2 = ObjectAnimator.
                ofFloat(target2,"translationY", 50)
                .setDuration(300);
        ObjectAnimator animTarget3 = ObjectAnimator.
                ofFloat(target3,"translationY", 50)
                .setDuration(300);

        ObjectAnimator animTarget4 = ObjectAnimator.
                ofFloat(target1,"ScaleX", 1.2f)
                .setDuration(300);

        AnimatorSet set = new AnimatorSet();
        set.play(animTarget1).with(animTarget2).after(animTarget3).after(animTarget4);
        set.setInterpolator(new LinearInterpolator());
        set.start();
    }

//    public void startObjectAnimatorXml(){
//        AnimatorSet as1 = (AnimatorSet) AnimatorInflater.
//                loadAnimator(this, R.anim.anim_obj);
//        as1.setTarget(target1);
//
//        AnimatorSet as2 = (AnimatorSet) AnimatorInflater.
//                loadAnimator(this, R.anim.anim_obj);
//        as1.setTarget(target2);
//
//        AnimatorSet as3 = (AnimatorSet) AnimatorInflater.
//                loadAnimator(this, R.anim.anim_obj);
//        as1.setTarget(target3);
//
//    }
}

package com.example.user.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class RatingBarActivity extends AppCompatActivity {

    RatingBar ratingBar;
    ImageView imageView;
    TextView textView;
    Button btnok;
    String Rating ="2.5";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_bar);

        ratingBar = findViewById(R.id.ratingBar);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        btnok = findViewById(R.id.btnok);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(getApplicationContext(),"평점 : " +  rating,Toast.LENGTH_SHORT).show();
                Rating = String.valueOf(rating);
            }
        });


        Animation animation =AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoomin);
        Animation Ranimation =AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
        Animation Tanimation =AnimationUtils.loadAnimation(getApplicationContext(),R.anim.righttoleft);
        Animation Banimation =AnimationUtils.loadAnimation(getApplicationContext(),R.anim.lefttoright);

        imageView.startAnimation(animation);
        ratingBar.startAnimation(Ranimation);
        textView.startAnimation(Tanimation);
        btnok.startAnimation(Banimation);


        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ad = new AlertDialog.Builder(RatingBarActivity.this,
                        R.style.Theme_AppCompat_Dialog_Alert);
                ad.setTitle("전송");
                ad.setMessage("평점 " + Rating +" 을 전송 하시겠습니까?");
                ad.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent mainit = new Intent();
                        mainit.putExtra("Rating",Rating);
                        setResult(RESULT_OK,mainit);
                        finish();
                        dialog.dismiss();

                    }
                });
                ad.show();
                return;
            }
        });
    }
}

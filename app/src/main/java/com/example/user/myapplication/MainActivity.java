package com.example.user.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.john.waveview.WaveView;

public class MainActivity extends AppCompatActivity {
    private ImageView imgmain, logout;
    TextView messege;
    TextView submessege;
    TextView maintext;
    //로그아웃을 눌렀을 때, 만들기


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode != 101 || resultCode != RESULT_OK)
        {
            return;
        }
        messege.setText("평점 : " + data.getStringExtra("Rating"));
        messege.setTextSize(25);
        Toast toast = Toast.makeText(getApplicationContext(), "전송이 완료되었습니다.", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //수분 섭취 버튼을 눌럿을 때, SelectDrinkActivity로 이동
        Button drinkAdd = findViewById(R.id.drinkAdd);
        drinkAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(),SelectDrinkActivity.class);
                startActivity(it);
            }
        });
        //로그아웃 버튼
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this,
                        R.style.Theme_AppCompat_Dialog_Alert);
                ad.setTitle("알림");
                ad.setMessage("로그아웃 하시겠습니까?");
                ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent logit = new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(logit);
                    }
                });
                ad.show();
            }
        });




        //SelectDrinkActivity에서 얻어 온 값
        submessege = findViewById(R.id.sub_message);
        Intent getit2 = getIntent();
        int istr2 = getit2.getIntExtra("pit2",2000);
        String sistr2 = String.valueOf(istr2);
        submessege.setText(sistr2 +"ml 남았습니다.");



        //SelectDrinkActivity에서 얻어온 섭취량 값 표시
        messege = findViewById(R.id.main_message);
        maintext = findViewById(R.id.textMain);

        final Intent getit = getIntent();
        int istr = getit.getIntExtra("pit",0); //getIntExtra로 값을 받아서


        Animation animation =AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);

        switch (istr/10)
        {

            case 1:
            case 2:
                maintext.setText("생활 속에서\n    물을 마시세요!");
                maintext.startAnimation(animation);
                break;
            case 3:
            case 4:
                maintext.setText("30% 돌파!");
                maintext.setTextSize(40);
                maintext.startAnimation(animation);
                break;
            case 5:
            case 6:
                maintext.setText("50% 돌파!");
                maintext.setTextSize(40);
                maintext.startAnimation(animation);
                break;
            case 7:
                case 8:
                    maintext.setText("거의 다 왔습니다!");
                    maintext.startAnimation(animation);
                    break;
        }

        if (istr >= 100)
        {
            maintext.setText("물 그만 마셔도 됩니다!");
            submessege.setText("미션 완료!");
            submessege.setTextSize(30);
            submessege.startAnimation(animation);
            istr = 0;
            AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this,
                    R.style.Theme_AppCompat_Dialog_Alert);
            ad.setTitle("알림");
            ad.setMessage("축하드립니다!\n오늘 수분 섭취량을 다 채웠습니다.");
            ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this,
                            R.style.Theme_AppCompat_Dialog_Alert);
                    ad.setTitle("알림");
                    ad.setMessage("앱을 평가 하시겠습니까?");
                    ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Intent ratingit = new Intent(getApplicationContext(),RatingBarActivity.class);
                            startActivityForResult(ratingit, 101);
                        }
                    });
                    ad.show();
                }
            });
            ad.show();

        }
        String sistr = String.valueOf(istr); //다시 string으로 형변환 후
        messege.setText(sistr + "%"); //메세지에 표시

        //웨이브 뷰 값에 따라 표시
        final WaveView waveView =(WaveView)findViewById(R.id.wave_view); //WaveView 효과 표시
        waveView.setProgress(istr); // 값에 따라 물이 차오름




        //네비게이션 버튼을 이용한 이벤트 처리
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView_main_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


                switch (menuItem.getItemId()) {

                    case R.id.menuitem_bottombar_Alarm:
                        Intent Alarmit = new Intent(getApplicationContext(),AlarmActivity.class);
                        startActivity(Alarmit);



                        return true;

                    case R.id.menuitem_bottombar_record:
                        Intent Recordit = new Intent(getApplicationContext(),RecordActivity.class);
                        startActivity(Recordit);



                        return true;

                    case R.id.menuitem_bottombar_content:
                        Intent Contents = new Intent(getApplicationContext(),WebViewActivity.class);
                        startActivity(Contents);



                        return true;
                }

                return false;


            }
        });



    }
}

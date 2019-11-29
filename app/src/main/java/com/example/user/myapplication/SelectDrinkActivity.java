package com.example.user.myapplication;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.text.SimpleDateFormat;
import java.util.Date;



public class SelectDrinkActivity extends AppCompatActivity {
    private SlidrInterface slidr;
    CheckBox check1;
    CheckBox check2;
    CheckBox check3;
    CheckBox check4;
    CheckBox check5;
    CheckBox check6;
    CheckBox check7;
    CheckBox check8;
    CheckBox check9;
    int num = 1;


    int max = 2000; //하루 섭취량의 최대치
    static int sum = 0; // 섭취량이 계속 더해짐



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_drink);


        check1 = findViewById(R.id.check1);
        check2 = findViewById(R.id.check2);
        check3 = findViewById(R.id.check3);
        check4 = findViewById(R.id.check4);
        check5 = findViewById(R.id.check5);
        check6 = findViewById(R.id.check6);
        check7 = findViewById(R.id.check7);
        check8 = findViewById(R.id.check8);
        check9 = findViewById(R.id.check9);




        //체크 박스이지만, 중복 체크 방지를 위한 하드 코딩
        check1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    check2.setChecked(false);
                    check3.setChecked(false);
                    check4.setChecked(false);
                    check5.setChecked(false);
                    check6.setChecked(false);
                    check7.setChecked(false);
                    check8.setChecked(false);
                    check9.setChecked(false);
                    Toast.makeText(getApplicationContext(),"중복 선택 불가능",Toast.LENGTH_SHORT).show();
                    return;

                }
            }
        });

        check2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    check1.setChecked(false);
                    check3.setChecked(false);
                    check4.setChecked(false);
                    check5.setChecked(false);
                    check6.setChecked(false);
                    check7.setChecked(false);
                    check8.setChecked(false);
                    check9.setChecked(false);
                    Toast.makeText(getApplicationContext(),"중복 선택 불가능",Toast.LENGTH_SHORT).show();
                    return;

                }
            }
        });

        check3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    check1.setChecked(false);
                    check2.setChecked(false);
                    check4.setChecked(false);
                    check5.setChecked(false);
                    check6.setChecked(false);
                    check7.setChecked(false);
                    check8.setChecked(false);
                    check9.setChecked(false);
                    Toast.makeText(getApplicationContext(),"중복 선택 불가능",Toast.LENGTH_SHORT).show();
                    return;

                }
            }
        });

        check4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    check1.setChecked(false);
                    check2.setChecked(false);
                    check3.setChecked(false);
                    check5.setChecked(false);
                    check6.setChecked(false);
                    check7.setChecked(false);
                    check8.setChecked(false);
                    check9.setChecked(false);
                    Toast.makeText(getApplicationContext(),"중복 선택 불가능",Toast.LENGTH_SHORT).show();
                    return;

                }
            }
        });

        check5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    check1.setChecked(false);
                    check2.setChecked(false);
                    check3.setChecked(false);
                    check4.setChecked(false);
                    check6.setChecked(false);
                    check7.setChecked(false);
                    check8.setChecked(false);
                    check9.setChecked(false);
                    Toast.makeText(getApplicationContext(),"중복 선택 불가능",Toast.LENGTH_SHORT).show();
                    return;

                }
            }
        });

        check6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    check1.setChecked(false);
                    check2.setChecked(false);
                    check3.setChecked(false);
                    check4.setChecked(false);
                    check5.setChecked(false);
                    check7.setChecked(false);
                    check8.setChecked(false);
                    check9.setChecked(false);
                    Toast.makeText(getApplicationContext(),"중복 선택 불가능",Toast.LENGTH_SHORT).show();
                    return;

                }
            }
        });

        check7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    check1.setChecked(false);
                    check2.setChecked(false);
                    check3.setChecked(false);
                    check4.setChecked(false);
                    check5.setChecked(false);
                    check6.setChecked(false);
                    check8.setChecked(false);
                    check9.setChecked(false);
                    Toast.makeText(getApplicationContext(),"중복 선택 불가능",Toast.LENGTH_SHORT).show();
                    return;

                }
            }
        });

        check8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    check1.setChecked(false);
                    check2.setChecked(false);
                    check3.setChecked(false);
                    check4.setChecked(false);
                    check5.setChecked(false);
                    check6.setChecked(false);
                    check7.setChecked(false);
                    check9.setChecked(false);
                    Toast.makeText(getApplicationContext(),"중복 선택 불가능",Toast.LENGTH_SHORT).show();
                    return;

                }
            }
        });

        check9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    check1.setChecked(false);
                    check2.setChecked(false);
                    check3.setChecked(false);
                    check4.setChecked(false);
                    check5.setChecked(false);
                    check6.setChecked(false);
                    check7.setChecked(false);
                    check8.setChecked(false);
                    Toast.makeText(getApplicationContext(),"중복 선택 불가능",Toast.LENGTH_SHORT).show();
                    return;

                }
            }
        });




        Button btnOK = findViewById(R.id.btnOK);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                check1 = findViewById(R.id.check1);
                check2 = findViewById(R.id.check2);
                check3 = findViewById(R.id.check3);
                check4 = findViewById(R.id.check4);
                check5 = findViewById(R.id.check5);
                check6 = findViewById(R.id.check6);
                check7 = findViewById(R.id.check7);
                check8 = findViewById(R.id.check8);
                check9 = findViewById(R.id.check9);

                String resultText = "";

                //선택 되었을 때, 텍스트를 담아서 메인에 보낼 값
                if (check1.isChecked()) {
                    resultText = check1.getText().toString();
                }
                    else if (check2.isChecked()) {
                        resultText = check2.getText().toString();
                    }

                    else if (check3.isChecked()) {
                        resultText = check3.getText().toString();
                    }

                    else if (check4.isChecked()) {
                        resultText = check4.getText().toString();
                    }

                    else if (check5.isChecked()) {
                        resultText = check5.getText().toString();
                    }

                    else if (check6.isChecked()) {
                        resultText = check6.getText().toString();
                    }

                    else if (check7.isChecked()) {
                        resultText = check7.getText().toString();
                    }

                    else if (check8.isChecked()) {
                        resultText = check8.getText().toString();
                    }

                    else if (check9.isChecked()) {
                        resultText = check9.getText().toString();
                    }

                    else {
                    Toast.makeText(getApplicationContext(), "음료를 선택해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                    }


                //섭취하기 버튼을 눌렀을 때, 그때의 시간과 섭취량이 적혀진 텍스트를 DB에 저장
                long now = System.currentTimeMillis(); //현재 시간 가져오기
                Date date = new Date(now); //현재 시간을 date에 담기
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //날짜 형식
                String getTime = sdf.format(date);

                num++;

                drinkDB drinkDB = new drinkDB(getApplicationContext(), "DRINK_TABLE", null, 1);
                SQLiteDatabase database = drinkDB.getWritableDatabase();

                    String water = resultText;
                    String time = getTime;
                    String sql = "insert into DRINK_TABLE values('" + num + "','" + water + "','" + time + "')";
                    Toast.makeText(getApplicationContext(),  water + "ml 섭취 성공.", Toast.LENGTH_LONG).show();
                    database.execSQL(sql);






                // 내가 음료를 선택한 값을 메인으로 수분 섭취한 값으로 보냄
                int istr = Integer.parseInt(resultText); //얻어온 String값을 int형으로 형변환
                if (sum > max)
                {
                    sum = 0;
                }
                sum += istr;

                //남은 섭취량 값
                int remain = max - sum;
                if (remain < 0)
                {
                    remain = 2000; // 남은 값이 0이 되면 다시 2000으로
                }
                int percent = (int)((double)sum /(double)max * 100.0); // 퍼센트로 바꿔줌
                //DB닫기
                drinkDB.close();
                database.close();

                Intent pit = new Intent(getApplicationContext(),MainActivity.class);
                pit.putExtra("pit",percent); // 계속 섭취되는 량
                pit.putExtra("pit2",remain);// 남은 량을 보냄
                startActivity(pit);




            }
        });
        //마우스로 끌어 당기면 이전 화면으로 이동
        slidr = Slidr.attach(this);



    }

}

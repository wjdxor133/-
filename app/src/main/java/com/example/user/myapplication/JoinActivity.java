package com.example.user.myapplication;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;


public class JoinActivity extends AppCompatActivity {
    private SlidrInterface slidr;
    EditText editID;
    EditText editMail;
    EditText editPWD;
    Button btnJoin;
    ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        btnJoin = findViewById(R.id.btnJoin);
        btnJoin.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                editID = findViewById(R.id.editID);
                editPWD = findViewById(R.id.editPWD);
                editMail = findViewById(R.id.editMail);

                String strID = editID.getText().toString().trim();
                String strMail = editMail.getText().toString().trim();
                String strPWD = editPWD.getText().toString().trim();

                // 항목이 하나라도 비어 있으면 알림창이 뜨게 설정
                if (strID.isEmpty() || strMail.isEmpty() || strPWD.isEmpty()) {
                    AlertDialog.Builder ad = new AlertDialog.Builder(JoinActivity.this,
                            R.style.Theme_AppCompat_Dialog_Alert);
                    ad.setTitle("안내");
                    ad.setMessage("항목을 채워주세요.");
                    ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    ad.show();
                    return;
                }

                else {
                    //DB에 넣어서 저장
                    MoomooDB MoomooDB = new MoomooDB(getApplicationContext(), "JOIN_TABLE", null, 1);
                    SQLiteDatabase database = MoomooDB.getReadableDatabase();
                    String sql = "select * from JOIN_TABLE where id=?";
                    Cursor cursor = database.rawQuery(sql,new String[] {strID});
                    if (cursor.getCount() != 0)
                    {
                        Toast.makeText(getApplicationContext(), "이미 가입되었습니다.", Toast.LENGTH_LONG).show();
                        return;
                    }
                    MoomooDB.getWritableDatabase();
                    String sql2 = "insert into JOIN_TABLE values('" + strID + "','" + strMail + "','" + strPWD + "')";
                    Toast.makeText(getApplicationContext(), "회원 가입 되었습니다.", Toast.LENGTH_LONG).show();
                    database.execSQL(sql2);

                    MoomooDB.close();
                    database.close();
                    }

                Intent Logit = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(Logit);
            }

        });
        imageView = findViewById(R.id.imageView);

        Animation animation =AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);
        imageView.startAnimation(animation);


        //마우스로 끌어 당기면 이전 화면으로 이동
        slidr = Slidr.attach(this);
    }
}
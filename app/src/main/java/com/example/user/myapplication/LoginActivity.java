package com.example.user.myapplication;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    TextView editID;
    TextView editPWD;
    TextView ForgetPWD;
    Button btnLogin;
    Button btnJoin;
    String id, email, pass;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editID = findViewById(R.id.editID);
                editPWD = findViewById(R.id.editPWD);


                String strID = editID.getText().toString().trim();
                String strPWD = editPWD.getText().toString();
                String strEMAIL = editID.getText().toString().trim();


                // 둘다 비어 있으면 알람 창이 뜨게 설정
                if (strID.isEmpty() && strPWD.isEmpty())
                {
                    AlertDialog.Builder ad = new AlertDialog.Builder(LoginActivity.this,
                            R.style.Theme_AppCompat_Dialog_Alert);
                    ad.setTitle("알림");
                    ad.setMessage("아이디와 비밀번호를 입력해주세요.");
                    ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    ad.show();
                    return;
                }

                //아이디만 비어 있으면
                if ((!(strID.isEmpty())) && strPWD.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"비밀번호를 입력하시오.",Toast.LENGTH_SHORT).show();
                    return;
                }

                //비밀번호만 비어 있으면
                if (strID.isEmpty() && (!(strPWD.isEmpty())))
                {
                    Toast.makeText(getApplicationContext(),"아이디를 입력하시오.",Toast.LENGTH_SHORT).show();
                    return;
                }

                // DB에 저장한 데이터들을 입력시 값이 같으면 main으로 이동, 틀리면 회원이 아니라고 토스트 띄우기
                MoomooDB mommooDB = new MoomooDB(getApplicationContext(),"JOIN_TABLE",null,1);
                SQLiteDatabase database = mommooDB.getReadableDatabase();
                String sql = "select * from JOIN_TABLE where id=? or email=?"; //아이디와 이메일을 얻어와서
                Cursor cursor = database.rawQuery(sql,new String[] {strID,strEMAIL}); // 두개에 해당하는 값이 없으면 return 있으면 로그인
                if (cursor.getCount() == 0)
                {
                    AlertDialog.Builder ad = new AlertDialog.Builder(LoginActivity.this,
                            R.style.Theme_AppCompat_Dialog_Alert);
                    ad.setTitle("알림");
                    ad.setMessage("아이디가 일치 하지 않거나  \n회원이 아닙니다.");
                    ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    ad.show();
                    return;
                }

                while(cursor.moveToNext()){
                    email = cursor.getString(1);
                    pass = cursor.getString(2);
                }



                if (!(pass.equals(strPWD)))
                {
                    Toast.makeText(getApplicationContext(),"비밀번호가 틀립니다.",Toast.LENGTH_SHORT).show();
                    return;
                }




                //만약 조건이 모두 만족하면, main으로 이동
                Intent putit = new Intent(getApplicationContext(),MainActivity.class);
                Toast.makeText(getApplicationContext(),strID+"님 환영합니다.",Toast.LENGTH_LONG).show();
                //DB를 쓰고 닫음
                mommooDB.close();
                database.close();
                cursor.close();

                startActivity(putit);



            }
        });

        ForgetPWD = findViewById(R.id.ForgetPWD);
        ForgetPWD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(),ForgetPWDActivity.class);
                startActivity(it);
            }
        });

        //가입 버튼을 눌렀을 때, JoinActivity로 이동
         btnJoin = findViewById(R.id.btnJoin);
         btnJoin.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 Intent it = new Intent(getApplicationContext(),JoinActivity.class);
                 startActivity(it);
             }
         });

         imageView = findViewById(R.id.imageView9);

        Animation animation =AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);
        imageView.startAnimation(animation);


    }
}

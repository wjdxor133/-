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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForgetPWDActivity extends AppCompatActivity {
    private SlidrInterface slidr;
    EditText editEmail;
    Button btnRestore;
    String email, pass;
    ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);




       btnRestore = findViewById(R.id.btnRestore);
       btnRestore.setOnClickListener(new View.OnClickListener() {


           @Override
           public void onClick(View v) {

               editEmail = findViewById(R.id.editEmail);
               String strEmail;
               strEmail = editEmail.getText().toString().trim();

               if (strEmail.isEmpty())
               {
                   Toast.makeText(getApplicationContext(),"이메일을 입력해주세요.",Toast.LENGTH_SHORT).show();
                   return;
               }
               else if(!(android.util.Patterns.EMAIL_ADDRESS.matcher(strEmail).matches()) ) //이메일 유효 형식 검사 알고리즘
               {
                   Toast.makeText(getApplicationContext(),"이메일 형식이 아닙니다",Toast.LENGTH_SHORT).show();
                   return;
               }


               //DB확인 후 복원 됬었다고, DB에 있는 비밀번호를 토스트로 띄우기
               MoomooDB mommooDB = new MoomooDB(getApplicationContext(),"JOIN_TABLE",null,1);
               SQLiteDatabase database = mommooDB.getReadableDatabase();
               String sql = "select * from JOIN_TABLE where email=?";
               Cursor cursor = database.rawQuery(sql,new String[] {strEmail});

               while(cursor.moveToNext()){
                   email = cursor.getString(1);
                   pass = cursor.getString(2);
               }

               if (!(strEmail.equals(email))) {
                   AlertDialog.Builder ad = new AlertDialog.Builder(ForgetPWDActivity.this,
                           R.style.Theme_AppCompat_Dialog_Alert);
                   ad.setTitle("알림");
                   ad.setMessage("이메일이 일치하지 않습니다..");
                   ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                           dialog.dismiss();
                       }
                   });
                   ad.show();
                   return;
               }
               AlertDialog.Builder ad = new AlertDialog.Builder(ForgetPWDActivity.this,
                       R.style.Theme_AppCompat_Dialog_Alert);
               ad.setTitle("알림");
               ad.setMessage("비밀번호는" + pass + "입니다.");
               ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       Intent Logit = new Intent(getApplicationContext(),LoginActivity.class);
                       startActivity(Logit);
                       dialog.dismiss();
                   }
               });
               ad.show();

           }
       });

        imageView = findViewById(R.id.imageView);

        Animation animation =AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);
        imageView.startAnimation(animation);

        //마우스로 끌어 당기면 이전 화면으로 이동
        slidr = Slidr.attach(this);
    }

}

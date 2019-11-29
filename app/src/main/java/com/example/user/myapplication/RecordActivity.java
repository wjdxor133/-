package com.example.user.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class RecordActivity extends AppCompatActivity {
    private SlidrInterface slidr;
    //DB에 저장 된 섭취 목록을 보여줌

    TextView textShow;
    ListView listShow;
    TextView textDate;
    Button btnShow, btnComsetup;
    SimpleAdapter listAdapter;
    ArrayList<HashMap<String,String>> listData = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);


        btnShow = findViewById(R.id.btnShow);
        textDate = findViewById(R.id.textdate);
        listShow = findViewById(R.id.listShow);


        long now = System.currentTimeMillis(); //현재 시간 가져오기
        final Date date = new Date(now); //현재 시간을 date에 담기
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //날짜 형식
        final String getTime = sdf.format(date);

        textDate.setText(getTime);

        listAdapter = new SimpleAdapter(this, listData, android.R.layout.simple_list_item_activated_2,
                new String[] {"water", "date"}, new int[] {android.R.id.text1, android.R.id.text2});
        listShow.setAdapter(listAdapter);


        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drinkDB drinkDB = new drinkDB(getApplicationContext(), "DRINK_TABLE", null, 1);
                SQLiteDatabase database = drinkDB.getReadableDatabase();
                String sql = "select * from DRINK_TABLE";
                Cursor cursor = database.rawQuery(sql, null);
                if (cursor.getCount() == 0)
                {
                    Toast.makeText(getApplicationContext(),"오늘 섭취량이 없습니다.",Toast.LENGTH_SHORT).show();
                    return;
                }


                while (cursor.moveToNext()) {
                    HashMap<String,String> item = new HashMap<>();
                    item.put("water",cursor.getString(1) + "ml 섭취");
                    item.put("date",cursor.getString(2));
                    listData.add(item);
                    listAdapter.notifyDataSetChanged();
                }

                //DB를 쓰고 닫음
                drinkDB.close();
                database.close();
                cursor.close();
            }
        });

        Button btnComsetup = findViewById(R.id.btnComsetup);
        btnComsetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        slidr = Slidr.attach(this);

    }
}


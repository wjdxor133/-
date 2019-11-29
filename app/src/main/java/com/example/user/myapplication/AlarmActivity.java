package com.example.user.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.IslamicCalendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.util.ArrayList;
import java.util.HashMap;

public class AlarmActivity extends AppCompatActivity {
    private SlidrInterface slidr;

    ListView listView;
    EditText editdate;
    EditText edittime;

    ArrayList<HashMap<String,String>> listData = new ArrayList<>();
    int ISelectedItem = -1; //아무것도 선택안된 초기값
    SimpleAdapter listAdapter;
    int num = 0;

    static final String TABLE_NAME="ALARM_TABLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        listView = findViewById(R.id.listView);
        editdate = findViewById(R.id.editdate);
        edittime = findViewById(R.id.edittime);

        listAdapter = new SimpleAdapter(this, listData, android.R.layout.simple_expandable_list_item_2,
                new String[] {"date","time"}, new int[] {android.R.id.text1, android.R.id.text2});
        listView.setAdapter(listAdapter);

        Button btnlist = findViewById(R.id.btnlist);
        btnlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmDB AlarmDB = new AlarmDB(getApplicationContext(), "ALARM_TABLE", null, 1);
                SQLiteDatabase database = AlarmDB.getReadableDatabase();
                String sql = "select * from ALARM_TABLE";
                Cursor cursor = database.rawQuery(sql, null);
                if (cursor.getCount() == 0)
                {
                    Toast.makeText(getApplicationContext(),"알람 설정하세요.",Toast.LENGTH_SHORT).show();
                    return;
                }


                while (cursor.moveToNext()) {
                    HashMap<String,String> item = new HashMap<>();
                    item.put("date",cursor.getString(1));
                    item.put("time",cursor.getString(2));
                    listData.add(item);
                    listAdapter.notifyDataSetChanged();
                }

                //DB를 쓰고 닫음
                AlarmDB.close();
                database.close();
                cursor.close();
            }
        });



        //알람 DB에 추가하기
        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num++;

                String strdate = editdate.getText().toString().trim();
                String strtime = edittime.getText().toString().trim();

                if (strdate.isEmpty() || strtime.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"빈칸을 채워주세요.",Toast.LENGTH_SHORT).show();
                    return;
                }

                HashMap<String,String> item = new HashMap<>();
                item.put("date",strdate);
                item.put("time",strtime);
                listData.add(item);
                listAdapter.notifyDataSetChanged();

                AlarmDB AlarmDB = new AlarmDB(getApplicationContext(), "ALARM_TABLE", null, 1);
                SQLiteDatabase database = AlarmDB.getWritableDatabase();
                String sql = "insert into ALARM_TABLE values('" + num + "','" + strdate + "','" + strtime + "')";
                Toast.makeText(getApplicationContext(),  "알람 설정 완료", Toast.LENGTH_SHORT).show();
                database.execSQL(sql);

                //DB닫기
                AlarmDB.close();
                database.close();

            }
        });

        //목록을 눌렀을 때, 토스트 창 띄우기
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ISelectedItem = position;
                HashMap<String,String> item = (HashMap<String,String>)listAdapter.getItem(position);
                editdate.setText(item.get("date"));
                edittime.setText(item.get("time"));
                Toast.makeText(getApplicationContext(),"날짜 :" + item.get("date") +"\n"+ "시간 :" + item.get("time"),Toast.LENGTH_SHORT).show();
            }
        });

        //수정하기 버튼을 눌렀을 때, DB에 수정된 저장
        Button btnRevise = findViewById(R.id.btnRevise);
        btnRevise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (ISelectedItem < 0)
                {
                    Toast.makeText(getApplicationContext(),"항목을 선택해주세요.",Toast.LENGTH_SHORT).show();
                    return;
                } // 현재 선택된 항목이 없으면

                String strdate = editdate.getText().toString().trim();
                String strtime = edittime.getText().toString().trim();


                    if (strdate.isEmpty() || strtime.isEmpty()) // 현재 입력창에 값이 비어있으면
                    {
                        Toast.makeText(getApplicationContext(), "정확히 입력해주세요.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                AlarmDB AlarmDB = new AlarmDB(getApplicationContext(), "ALARM_TABLE", null, 1);
                SQLiteDatabase database = AlarmDB.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("date", strdate);
                values.put("time",strtime);
                database.update(TABLE_NAME, values, "id=" + ISelectedItem, null);
                Toast.makeText(getApplicationContext(),"수정했습니다",Toast.LENGTH_SHORT).show();

                //DB닫기
                AlarmDB.close();
                database.close();



                HashMap<String,String> item = new HashMap<>();
                item.put("date",strdate);
                item.put("time",strtime);
                listData.set(ISelectedItem, item);
                listAdapter.notifyDataSetChanged(); // 알려주기 위해 선언



            }
        });

        //알람 DB에서 지우기
        Button btnDel = findViewById(R.id.btnDel);
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ISelectedItem < 0)
                {
                    Toast.makeText(getApplicationContext(),"항목을 선택해주세요.",Toast.LENGTH_SHORT).show();
                    return;
                } // 현재 선택된 항목이 없다면

                AlarmDB AlarmDB = new AlarmDB(getApplicationContext(), "ALARM_TABLE", null, 1);
                SQLiteDatabase database = AlarmDB.getReadableDatabase();
                String sql = "delete from ALARM_TABLE where id";
                Toast.makeText(getApplicationContext(),  "선택한 알람 삭제 완료", Toast.LENGTH_SHORT).show();
                database.execSQL(sql);

                //DB닫기
                AlarmDB.close();
                database.close();

                listData.remove(ISelectedItem);
                listAdapter.notifyDataSetChanged();
                ISelectedItem = -1; //다시 현재 선택된 아이템이 없다고 알려줌





            }
        });

        Button btncom = findViewById(R.id.btnComsetup);
        btncom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        slidr = Slidr.attach(this);

    }
}

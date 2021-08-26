package com.example.logoactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class StoreInfoActivity extends AppCompatActivity {


    TextView name_text;
    TextView position_text;
    TextView wait_text;
    TextView type_text;
    TextView grade_text;
    TextView visit_text; // Text View 선언

    String name, position, waiting, type;
    int grade , visitNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_info);

        name_text=(TextView) findViewById(R.id.name_text);
        position_text=(TextView) findViewById(R.id.position_text);
        wait_text=(TextView) findViewById(R.id.wait_text);
        type_text=(TextView) findViewById(R.id.type_text);
        grade_text=(TextView) findViewById(R.id.grade_text);
        visit_text=(TextView) findViewById(R.id.visit_text); // 텍스트뷰


        Intent intent=getIntent(); // 데이터 수신 intent
        name=intent.getExtras().getString("name");
        position=intent.getExtras().getString("position");
        waiting=intent.getExtras().getString("wait");
        type=intent.getExtras().getString("type");
        grade=intent.getExtras().getInt("grade");
        visitNum=intent.getExtras().getInt("visit"); // 데이터 수신

        name_text.setText(name);
        position_text.setText(position);
        wait_text.setText(waiting);
        type_text.setText(type);
        grade_text.setText(String.valueOf(grade));
        visit_text.setText(String.valueOf(visitNum));


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); // 툴바
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기버튼
        getSupportActionBar().setDisplayShowTitleEnabled(false);



    }


    // 툴바 뒤로가기 리스너
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                Intent intent = new Intent(getApplicationContext(), PrintStoreListActivity.class); // 이전화면으로
                startActivity(intent);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }




}
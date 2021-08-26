package com.example.logoactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class EditStoreListActivity extends AppCompatActivity {

    ArrayList<String> storename=new ArrayList<>(); // 가게명

    ListView listView; // 리스트뷰

    Store store; // 선택된 리스트의 정보를 받아오기 위한 Store 객체

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_store_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); // 툴바
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기버튼
        getSupportActionBar().setDisplayShowTitleEnabled(false); // 툴바 title삭제

        listView=(ListView) findViewById(R.id.listView); // 리스트뷰

        storename=FileHelper.readStorename(getApplicationContext());

        //adpater연결전에 storename을 다 받아와야 한다.
        ArrayAdapter adapter=new ArrayAdapter<String>(
                this,android.R.layout.simple_list_item_1,storename);


        listView.setAdapter(adapter);

        // 리스트 항목 누르면 가게 이름 얻도록 리스너 등록
        EditStoreListActivity.ResultListListener listListener=new EditStoreListActivity.ResultListListener();
        listView.setOnItemClickListener(listListener);

    } // onCreate end



    class ResultListListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            store=FileHelper.readStore(storename.get(i),getApplicationContext());

            Intent intent = new Intent(getApplicationContext(), EditStoreActivity.class);
            intent.putExtra("name",store.getStoreName()); // 가게이름 넘겨주도록

            startActivity(intent);
        }
    } // listlistenner end


    // 툴바 뒤로가기 리스너
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                Intent intent = new Intent(getApplicationContext(), MainActivity.class); // 이전화면으로
                startActivity(intent);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }





} // main end
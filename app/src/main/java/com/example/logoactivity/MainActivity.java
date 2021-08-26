package com.example.logoactivity;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<String> storeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // 가게 등록 버튼 클릭시 액티비티 전환
       Button registerStore_btn=(Button) findViewById(R.id.registerStore_btn);
        registerStore_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(),RegisterStoreActivity.class);
                startActivity(intent);
            }
        });


        // 가게 정보 수정 및 삭제 버튼 클릭시 액티비티 전환
        Button editStore_btn=(Button)findViewById(R.id.editStore_btn);
        editStore_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File file=new File("/data/data/com.example.logoactivity/files/store.txt");
                if(!file.exists() || FileHelper.checkEmpty(getApplicationContext())) {
                    Toast.makeText(getApplicationContext(), "등록된 가게 정보가 없습니다.",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                Intent intent=new Intent(getApplicationContext(),EditStoreListActivity.class);
                startActivity(intent);
            }
        });


        // 가게 리스트 버튼 클릭시 액티비티 전환
        Button storeList_btn=(Button)findViewById(R.id.storeList_btn);
        storeList_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                File file=new File("/data/data/com.example.logoactivity/files/store.txt");
                if(!file.exists() || FileHelper.checkEmpty(getApplicationContext())) {
                    Toast.makeText(getApplicationContext(), "등록된 가게 정보가 없습니다.",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                Intent intent=new Intent(getApplicationContext(),PrintStoreListActivity.class);
                startActivity(intent);
            }
        });



        // 사용자 정보 수정 버튼 클릭시 액티비티 전환
        Button editUser_btn=(Button)findViewById(R.id.editUser_btn);
        editUser_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), UserRegisterActivity.class);
                startActivity(intent);
            }
        });



        // 사용자 맞춤 정보 확인 버튼 클릭시 액티비티 전환
        Button search_btn=(Button)findViewById(R.id.search_btn);
        search_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), SearchAPI.class);
                startActivity(intent);
            }
        });

        // 가게 랜덤 출력 버튼 클릭
        Button random_btn=(Button)findViewById(R.id.random_btn);
        random_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                // store 파일이 존재하면
               // Store store=new Store("hi1","cute","sexy","pretty",3,5);
               // Store store=new Store("hi2","cute","sexy","pretty",3,5);
               //Store store=new Store("hi3","cute","sexy","pretty",3,5);

               // FileHelper.writeStore(store,getApplicationContext());

                String path="/data/data/com.example.logoactivity/files/store.txt";

                if(new File(path).exists()) {
                    //storeName=new ArrayList<String>();
                    storeName=FileHelper.readStorename(getApplicationContext());

                    int ran=(int)(Math.random()*(storeName.size()));
                   // Log.d("int ran=", String.valueOf(ran));
                    String temp=storeName.get(ran);
                   // Log.d("String temp=",temp);


                    Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_LONG).show();

                }
                // store 파일이 존재하지 않으면
                else{
                    Toast.makeText(getApplicationContext(), "등록된 가게 정보가 없습니다.",
                            Toast.LENGTH_LONG).show();
                }


            }
        });







    } // Create end



} // main end



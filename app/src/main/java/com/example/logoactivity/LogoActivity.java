package com.example.logoactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Spinner;

import java.io.File;


public class LogoActivity extends Activity {


    
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_activity); // xml, java 연결
        Handler handler = new Handler();

        //int a=0;
       // int a=1;
        String path="/data/data/com.example.logoactivity/files/user.txt";

        if(new File(path).exists()) { // 사용자 정보가 존재 한다면, menu 화면으로 전환
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 2000);
        }
        else{ // 사용자 정보 등록 화면으로 전환
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getApplicationContext(), UserRegisterActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 2000);
        }

    }

    /*protected void onPause(){
        super.onPause();
        finish();
    }*/
    
}

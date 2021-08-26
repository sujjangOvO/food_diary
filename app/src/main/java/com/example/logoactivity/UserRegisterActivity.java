package com.example.logoactivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;

import static com.example.logoactivity.FileHelper.writeUser;

public class UserRegisterActivity extends AppCompatActivity {

    Spinner spinner; // spinner에는 선호 식사
    String resultType;  // spinner 값 받을 문자열, 익명클래스 내부에서는 onCreate의 멤버변수에 접근 할 수 없으므로 class의 멤버변수로.

    EditText residence; // residence에는 거주지
    String resultResidence; // 거주지 값 받을 문자열

    User user; // User 객체


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);



        residence=findViewById(R.id.residence);
        spinner=findViewById(R.id.mealtype); // spinner 객체

        ArrayAdapter type=ArrayAdapter.createFromResource(this,R.array.type,
                android.R.layout.simple_spinner_dropdown_item);
        type.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        spinner.setAdapter(type); // adapter 연결


        // 밥집종류 스피너 이벤트리스너
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                resultType=spinner.getSelectedItem().toString();
            } //이 오버라이드 메소드에서 position은 몇번째 값이 클릭됬는지 알 수 있습니다.
            //getItemAtPosition(position)를 통해서 해당 값을 받아올수있습니다.

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });




        // 취소버튼클릭, 이 화면은 사용자 정보가 등록되어 있지 않을 때이므로 취소 버튼을 누르면 어플을 종료하도록 한다.
        Button back_btn=(Button) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                // 종료전 사용자에게 한번 더 종료 여부를 묻는다.
                AlertDialog.Builder builder=new AlertDialog.Builder(UserRegisterActivity.this);
                builder.setMessage("Application을 종료하시겠습니까? (취소는 뒤로가기)");
                builder.setTitle("Application 종료")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialogInterface, int i){
                                finish();
                                //dialogInterface.cancel();
                            }
                        });

                AlertDialog alert  = builder.create();
                alert.setTitle("Application 종료");
                alert.show();

            }
        });



        // 확인 버튼 클릭
        Button check_btn=(Button) findViewById(R.id.check_btn);
        check_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {

                // edittext에 값이 없을 때
                if((residence.getText().toString().equals("")|| residence.getText()==null)){

                    Toast.makeText(getApplicationContext(), "정보를 등록하세요.",
                            Toast.LENGTH_LONG).show();

                }

                else {
                    resultResidence = residence.getText().toString();
                    user = new User(resultResidence, resultType); // user 객체 생성

                    File file = new File("/data/data/com.example.logoactivity/files/user.txt");
                    if(file.exists()){ // 파일이 있다면
                        file.delete();
                    }
                    saveUserInfo(user);


                    Toast.makeText(getApplicationContext(), "사용자 정보가 성공적으로 등록되었습니다.",
                            Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });


    } // onCreate end


    public void saveUserInfo(User user){
        writeUser(user,getApplicationContext());
    }


} // main end
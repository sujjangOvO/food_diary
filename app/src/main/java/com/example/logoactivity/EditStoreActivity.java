package com.example.logoactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class EditStoreActivity extends AppCompatActivity {

    String name; // 수정할 정보의 가게명

    TextView storename_text;

    EditText modify_vist;
    RadioGroup grade;

    int Resultpoint, visitN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_store);


        Toolbar toolbar = findViewById(R.id.toolbar2); // 툴바
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기버튼
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        grade=(RadioGroup)findViewById(R.id.point); // 라디오 그룹
        modify_vist=(EditText)findViewById(R.id.modify_visit); // edittext


        Intent intent=getIntent(); // 데이터 수신 intent
        name=intent.getExtras().getString("name");
        storename_text=(TextView)findViewById(R.id.storename_text);
        storename_text.setText(name); // 수정할 가게이름


        // 삭제 버튼
        Button delete_btn=(Button)findViewById(R.id.delete_btn);
        delete_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                // 삭제함수호출
                FileHelper.deleteStore(name,getApplicationContext());


                Toast.makeText(getApplicationContext(), name+"의 정보가 삭제되었습니다.",
                        Toast.LENGTH_LONG).show();

                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

            }
        });


        // 수정 버튼
        Button modify_btn=(Button)findViewById(R.id.modify_btn);
        modify_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){


                if((modify_vist.getText().toString().equals("")|| modify_vist.getText()==null)
                    || grade==null)
                {

                    Toast.makeText(getApplicationContext(), "정보를 등록하세요.",
                            Toast.LENGTH_LONG).show();

                    //File file=new File("/data/data/com.example.logoactivity/files/store.txt");
                    //if(file.exists()) file.delete();
                }
                else {

                    int point = grade.getCheckedRadioButtonId();
                    RadioButton pointCheck = (RadioButton) findViewById(point);
                    Resultpoint = Integer.parseInt(pointCheck.getText().toString()); // 평점



                    String str = modify_vist.getText().toString().trim(); // 방문횟수
                    visitN = Integer.parseInt(str);
                    //Toast.makeText(getApplicationContext(),"visitNumber= "+visitN+"\nGrade= "+Resultpoint,Toast.LENGTH_LONG).show();

                    //수정함수호출
                    // Resultpoint
                    // visitN;
                    //  name
                    FileHelper.modifyStore(name,Resultpoint,visitN,getApplicationContext());  // 파일 수정



                    Toast.makeText(getApplicationContext(), "가게 정보가 수정되었습니다.",
                            Toast.LENGTH_LONG).show();

                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);

                }







            }
        });




    } // onCreate end


    // 툴바 뒤로가기 리스너
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                Intent intent = new Intent(getApplicationContext(), EditStoreListActivity.class); // 이전화면으로
                startActivity(intent);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }




} // main end
package com.example.logoactivity;

public class User {

    private String userResidence;
    private String user_typeMeal;


    // 생성자
    public User(String userResidence, String user_typeMeal){
        this.userResidence=userResidence;
        this.user_typeMeal=user_typeMeal;
    }

    // 파일에 write하기위해 객체 내용을 getUser() 함수사용하여 얻는다.
    public String getUser(){
        String content=this.userResidence+"\n"+this.user_typeMeal+"\n";
        return content;
    }




}

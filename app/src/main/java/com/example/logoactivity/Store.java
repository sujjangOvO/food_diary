package com.example.logoactivity;

public class Store {


    private String storeName; // 가게 이름
    private String position; // 가게 위치
    private String waitCheck; // 웨이팅 유, 무
    private String typeMeal; // 식사 종류
    private int grade;  // 평점
    private int visitNum; // 방문횟수


    public Store(){}

    // 생성자
    public Store(String storeName,String position, String waitCheck, String typeMeal,
                 int grade, int visitNum){

        this.storeName=storeName;
        this.position=position;
        this.waitCheck=waitCheck;
        this.typeMeal=typeMeal;
        this.grade=grade;
        this.visitNum=visitNum;

    }

    public String get(){
        String content=this.storeName+"\n"+this.position+"\n"+this.waitCheck+"\n"+this.typeMeal+
                "\n"+this.grade+"\n"+this.visitNum+"\n";

        return content;
    }
    
    // 가게이름
    public String getStoreName(){ 
        return this.storeName;
    }

    // 가게위치
    public String getposition(){
        return this.position;
    }
    
    // 웨이팅 유,무
    public String getwaitCheck(){
        return this.waitCheck;
    }

    // 식사타입
    public String gettypeMeal(){
        return this.typeMeal;
    }
    
    // 평점
    public int getgrade(){
        return Integer.valueOf(this.grade);
    }
    
    // 방문횟수
    public int getvisitNum(){
        return Integer.valueOf(this.visitNum);
    }
    

}

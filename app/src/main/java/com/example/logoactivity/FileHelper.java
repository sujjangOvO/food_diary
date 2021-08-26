package com.example.logoactivity;

import android.content.Context;
import android.os.Environment;
import android.renderscript.ScriptGroup;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileHelper { // 내장 메모리 파일 처리


    // User
    public static void writeUser(User user, Context context){ // 사용자 정보 write

        try {
            FileOutputStream fos = context.openFileOutput("user.txt", Context.MODE_APPEND);
            PrintWriter writer=new PrintWriter(fos);
            writer.println(user.getUser());
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readUser(Context context){ // 사용자 정보 읽기

        String content=null;

        try {
            FileInputStream fis = context.openFileInput("user.txt");
            InputStreamReader inputStreamReader=new InputStreamReader(fis);
            BufferedReader reader=new BufferedReader(inputStreamReader);

            StringBuffer buffer=new StringBuffer();

            String line1=reader.readLine();
            String line2=reader.readLine();
            content=line1+" "+line2;


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }


    // Store
    public static void writeStore(Store store, Context context){ // 가게 정보 write

        try {

            FileOutputStream fos = context.openFileOutput("store.txt", Context.MODE_APPEND);
            PrintWriter writer=new PrintWriter(fos);
            writer.println(store.get());
            writer.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList readStorename(Context context){  // 가게 이름 읽기

        ArrayList<String> arrayList=new ArrayList<String>();

        try {

            FileInputStream fis = context.openFileInput("store.txt");
            InputStreamReader inputStreamReader=new InputStreamReader(fis);
            BufferedReader reader=new BufferedReader(inputStreamReader);

            StringBuffer buffer=new StringBuffer();

            for (int i = 0; ; i++) {

                String line = reader.readLine();
                if(line==null) break;

                if(i%7==0) {
                    // 0,7,14,... 가게명만 저장
                    buffer.append(line+"\n");
                    arrayList.add(line);

                }

            }




        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

       // for(int i=0; i<arrayList.size(); i++) Log.d("tag3",arrayList.get(i));
       return arrayList;
    }


    public static boolean checkEmpty(Context context) {  // 가게 파일 내용 비었는지 확인

        try {
            FileInputStream fis = context.openFileInput("store.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String line = reader.readLine();
            if (line == null) return true;

        } catch (FileNotFoundException e) {
            Log.d("tag1", e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("tag2", e.toString());
            e.printStackTrace();
        }

        return false;
    }




    public static Store readStore(String name, Context context){  // 특정 가게 정보 읽기

        Store store=null;

        try {
            FileInputStream fis = context.openFileInput("store.txt");
            InputStreamReader inputStreamReader=new InputStreamReader(fis);
            BufferedReader reader=new BufferedReader(inputStreamReader);

            StringBuffer buffer=new StringBuffer();

            for (int i = 0; ; i++) {

                String line = reader.readLine();
                if(line==null) break;

                if(line.equals(name)){ // 문자열 비교는 equal^^..
                    store = new Store(line,reader.readLine(),reader.readLine(),reader.readLine(),
                            Integer.valueOf(reader.readLine()),Integer.valueOf(reader.readLine()));

                    //return store;
                }
                else{
                    continue;
                }



            }


        } catch (FileNotFoundException e) {
            Log.d("tag1",e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("tag2",e.toString());
            e.printStackTrace();
        }


        return store;
    }


    public static void modifyStore(String name, int grade, int visit, Context context){  // 특정 가게 정보 수정하기 평점, 방문횟수

        Log.d("grade",String.valueOf(grade));
        Log.d("visit",String.valueOf(visit));
        Log.d("name",String.valueOf(name));

        Store store=null;
        ArrayList<String> info = new ArrayList<String>();

        try {
            FileInputStream fis = context.openFileInput("store.txt");
            InputStreamReader inputStreamReader=new InputStreamReader(fis);
            BufferedReader reader=new BufferedReader(inputStreamReader);

            for (int i = 0; ; i++) {

                String line = reader.readLine();
                if(line==null) break;

                info.add(line + "\n");
            }
        } catch (FileNotFoundException e) {
            Log.d("tag1",e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("tag2",e.toString());
            e.printStackTrace();
        }


        // 평점, 방문횟수 수정
        String Modigrade= String.valueOf(grade)+"\n";
        String Modivisit= String.valueOf(visit)+"\n";
        String str=name+"\n"; // 개행문자 달린 name이랑 비교해야함..!!

        for(int i=0;i<info.size();i++){
            if(str.equals(info.get(i))){
                info.set(i+4,Modigrade);
                info.set(i+5,Modivisit);
            }
        }

        /*
        for(int i=0; i<info.size(); i++){
            Log.d("i=",String.valueOf(i));
            Log.d("info",info.get(i));
        }*/


        // 수정한 값으로 파일 다시 쓰기
        try {

            FileOutputStream fos = context.openFileOutput("store.txt", Context.MODE_PRIVATE); // 새로쓰기
            PrintWriter writer=new PrintWriter(fos);


            for(int i=0;i<info.size();i++){
                writer.print(info.get(i));
            }

            writer.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    } // modify end


    public static void deleteStore(String name, Context context){  // 특정 가게 정보 삭제

        Log.d("name",String.valueOf(name));

        Store store=null;
        ArrayList<String> info = new ArrayList<String>();

        try {
            FileInputStream fis = context.openFileInput("store.txt");
            InputStreamReader inputStreamReader=new InputStreamReader(fis);
            BufferedReader reader=new BufferedReader(inputStreamReader);

            for (int i = 0; ; i++) {

                String line = reader.readLine();
                if(line==null) break;

                info.add(line + "\n");
            }
        } catch (FileNotFoundException e) {
            Log.d("tag1",e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("tag2",e.toString());
            e.printStackTrace();
        }


        // 평점, 방문횟수 수정
        String str=name+"\n";
        for(int i=0;i<info.size();i++){
            if(str.equals(info.get(i))){
                Log.d("equal i =",String.valueOf(i));
                for(int j=0; j<7; j++) info.remove(i);
                /*
                info.remove(i+1); info.remove(i+2); info.remove(i+3);
                info.remove(i+4); info.remove(i+5); info.remove(i+6);*/


            }
        }


        for(int i=0; i<info.size(); i++){
            Log.d("i=",String.valueOf(i));
            Log.d("info",info.get(i));
        }


        // 삭제할 가게 없앤 데이터로 파일 다시 쓰기
        try {

            FileOutputStream fos = context.openFileOutput("store.txt", Context.MODE_PRIVATE); // 새로쓰기
            PrintWriter writer=new PrintWriter(fos);


            for(int i=0;i<info.size();i++){
                writer.print(info.get(i));
            }

            writer.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    } // delete end

} // FileHelper end
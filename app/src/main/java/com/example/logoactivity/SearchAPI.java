package com.example.logoactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class SearchAPI extends AppCompatActivity {


    // 검색 결과를 담는 변수 ArrayList
    ArrayList<String> result_title_list; // 결과값저장
    ArrayList<String> result_link_list; // 링크저장
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_a_p_i);

        // 결과값저장
        result_link_list=new ArrayList<>();
        result_title_list=new ArrayList<>();

        // 리스트뷰
        listView=(ListView) findViewById(R.id.listView);




        // 네트워크 스레드 실행, 네이버API는 인터넷 통하여 정보 얻어오므로 스레드 필수
        NetworkThread networkThread=new NetworkThread();
        networkThread.start();

        ArrayAdapter adapter=new ArrayAdapter<String>(
                this,android.R.layout.simple_list_item_1,result_title_list);


        listView.setAdapter(adapter);

        // 리스트 누르면 해당 링크로 이동하는 리스너 등록
        ResultListListener listListener=new ResultListListener();
        listView.setOnItemClickListener(listListener);



    } // onCreate end


    class ResultListListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            // 검색 결과의 position 번째 링크 주소를 가져온다.
            String site=result_link_list.get(i);
            // 사이트를 띄운다
            Uri uri= Uri.parse(site);
            Intent intent=new Intent(Intent.ACTION_VIEW,uri);
            startActivity(intent);
        }
    } // listlistenner end




    /* 데이터를 받아오는 스레드 */
    class NetworkThread extends Thread{
        // keyword에 사용자 정보 받아오기.
        String keyword=FileHelper.readUser(getApplicationContext())+" 맛집";


        // 네이버 오픈 API 사용을 위한 client ID 와 secret 값
        String client_id="_JeZymC4NGCw0yRMFAfA";
        String client_secret="0rWMOkl9kr";



        @Override
        public void run() {
            try {
                result_title_list.clear();
                result_link_list.clear();

                //검색어을 인코딩한다.
                keyword= URLEncoder.encode(keyword,"UTF-8");

                // 접속 주소
                String site="https://openapi.naver.com/v1/search/" +
                        "blog.xml?query="+keyword;

                // 접속
                URL url=new URL(site);
                HttpURLConnection conn=(HttpURLConnection)url.openConnection();

                //요청 방식과 client_id , client_secret 값을 설정한다.
                conn.setRequestMethod("GET");
                conn.setRequestProperty("X-Naver-Client-Id",client_id);
                conn.setRequestProperty("X-Naver-Client-Secret",client_secret);

                // 데이터를 읽어온다.
                InputStream is =conn.getInputStream();

                // DOM  파서 생성
                DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
                DocumentBuilder builder=factory.newDocumentBuilder();
                Document document=builder.parse(is);

                // 최상위 루트태그를 가져온다.
                Element root=document.getDocumentElement();

                // item 태그 객체들을 가져온다.
                NodeList item_list=root.getElementsByTagName("item");

                // 태그 개수만큼 반복한다.
                for(int i=0 ; i<item_list.getLength(); i++){

                    // i 번째 태그 객체를 가져온다.
                    Element item_tag=(Element)item_list.item(i);

                    // item 태그 내의 title 과 link 를 가져온다.
                    NodeList title_list=item_tag.getElementsByTagName("title");
                    NodeList link_list=item_tag.getElementsByTagName("link");

                    Element title_tag = (Element)title_list.item(0);
                    Element link_tag=(Element)link_list.item(0);

                    String title=title_tag.getTextContent();
                    String link=link_tag.getTextContent();

                    // HTMP 태그 제거
                    title=removeTag(title);

                   // String[] titles=title.split("</b>");

                    /*
                    for(int j=0; j<titles.length; j++) {
                        result_title_list.add(titles[j]);
                    } */
                    result_title_list.add(title);
                    result_link_list.add(link);
                }

                //리스트 뷰를 구성한다.
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ArrayAdapter<String> adapter=(ArrayAdapter<String>)listView.getAdapter();
                        adapter.notifyDataSetChanged();
                    }
                });
            }catch (Exception e){e.printStackTrace();}
        }
    }


    // HTML 태그 제거 함수
    public String removeTag(String html) throws Exception {
        return html.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
    }


} // main end
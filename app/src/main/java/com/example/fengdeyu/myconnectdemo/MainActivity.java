package com.example.fengdeyu.myconnectdemo;

import android.database.DataSetObserver;
import android.os.AsyncTask;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private static String URL ="http://www.23wx.com/html/55/55519/";
    private String[] a=new String[]{"1","2","3","4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView= (ListView) findViewById(R.id.lv_main);

        ArrayAdapter adapter=new ArrayAdapter(MainActivity.this,R.layout.simple_item,a);



        //new FictionAsyncTask().execute(URL);


    }

    class FictionAsyncTask extends AsyncTask<String,Void,List<FictionsBean>>{

        //Document doc;
        //List<String> titleList=new ArrayList<>();


        @Override
        protected List<FictionsBean> doInBackground(String... params) {


            Document doc;

            doc=Jsoup.parse(params[0]);

            List<FictionsBean> fictionsBeanList=new ArrayList<>();



            Elements links = doc.select("a[href]"); //带有href属性的a元素

            for (Element link : links) {
                FictionsBean fictionsBean=new FictionsBean();


                String linkHref = link.attr("href");
                String linkText = link.text();

                fictionsBean.fictionTitle=linkText;
                fictionsBean.fictionUrl=linkHref;


                Log.i("info",linkText);

                fictionsBeanList.add(fictionsBean);
            }

            return fictionsBeanList;
        }

        @Override
        protected void onPostExecute(List<FictionsBean> fictionsBeen) {
            super.onPostExecute(fictionsBeen);
            FictionAdapter adapter=new FictionAdapter(MainActivity.this,fictionsBeen);
            mListView.setAdapter(adapter);
        }
  }

//    private List<String> getJsonData(String url){
//        List<String> nameList=new ArrayList<>();
//
//        try {
//            String jsonString = readStream(new URL(url).openStream());
//
//            JSONObject jsonObject1;
//            JSONObject jsonObject2;
//            String name;
//
//            try {
//                jsonObject1=new JSONObject(jsonString);
//                JSONArray jsonArray1=jsonObject1.getJSONArray("tbody");
//
//                for(int i=0;i<jsonArray1.length();i++){
//                    jsonObject2=new JSONObject()
//
//                }
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            Log.i("info",jsonString);
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        return nameList;
//    }
//
//
//
//
//
//    private String readStream(InputStream is){
//        InputStreamReader isr;
//        String result="";
//
//        String line="";
//        try {
//            isr=new InputStreamReader(is,"utf-8");
//            BufferedReader br=new BufferedReader(isr);
//
//            try {
//                while ((line=br.readLine())!=null){
//                    result+=line;
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//
//        return result;
//    }














}

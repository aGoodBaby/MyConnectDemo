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
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView= (ListView) findViewById(R.id.lv_main);


        mListView.setAdapter(arrayAdapter);


        new FictionAsyncTask().execute(URL);


    }

    class FictionAsyncTask extends AsyncTask<String,Void,List<String>>{




        @Override
        protected List<String> doInBackground(String... params) {

            List<String> titleList=new ArrayList<>();
            Document doc;


            try {
                doc=Jsoup.connect(params[0]).get();
                Elements links=doc.select("td.L");


                for (Element link : links) {


                    //String linkHref = link.getElementsByTag("a").attr("href");
                    String linkText = link.text();


                    //Log.i("info",linkHref);


                    titleList.add(linkText);

                }


            } catch (IOException e) {
                e.printStackTrace();
            }



            return titleList;
        }

        @Override
        protected void onPostExecute(List<String> titleList) {
            super.onPostExecute(titleList);
            //FictionAdapter adapter=new FictionAdapter(MainActivity.this,fictionsBeen);
            arrayAdapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,titleList);
            mListView.setAdapter(arrayAdapter);
        }
  }

}

package com.example.fengdeyu.myconnectdemo;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.AsyncTask;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


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
    private ArrayAdapter<String> arrayAdapter1;//章节目录适配器
    private ArrayAdapter<String> arrayAdapter2;//页数适配器
    public Bundle titlesUrl=new Bundle();

    private Spinner spinner;
    List<String> titleList=new ArrayList<>();//总章节名
    List<String> cutTitleList=new ArrayList<>();//页章节名
    List<String> pageList=new ArrayList<>();//页名

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView= (ListView) findViewById(R.id.lv_main);

        spinner= (Spinner) findViewById(R.id.spinner3);

        //mListView.setAdapter(arrayAdapter);


        new FictionAsyncTask().execute(URL); //执行FictionAsyncTask()







        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tvItem=(TextView)view.findViewById(android.R.id.text1);

                String itemName=tvItem.getText().toString();
                String titleUrl=titlesUrl.getString(itemName);
                Log.i("info",titleUrl);

                Intent intent=new Intent(MainActivity.this,ContentsActivity.class);
                intent.putExtra("titleUrl",URL+titleUrl);
                startActivity(intent);

            }
        });




    }



    class FictionAsyncTask extends AsyncTask<String,Void,List<String>>{




        @Override
        protected List<String> doInBackground(String... params) {


            Document doc;


            try {
                doc=Jsoup.connect(params[0]).get();
                Elements links=doc.select("td.L");


                for (Element link : links) {


                    String linkHref = link.getElementsByTag("a").attr("href");
                    String linkText = link.text();


                    //Log.i("info",linkHref);
                    titlesUrl.putString(linkText,linkHref);


                    titleList.add(linkText);

                }


            } catch (IOException e) {
                e.printStackTrace();
            }



            return titleList;
        }

        @Override
        protected void onPostExecute(final List<String> titleList) {
            super.onPostExecute(titleList);

            cutTitleList=cutList(titleList,1);
            arrayAdapter1=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,cutTitleList);
            mListView.setAdapter(arrayAdapter1);



            for (int i=1;i<(titleList.size()/40)+2;i++){
                pageList.add("第"+i+"页");
            }
            arrayAdapter2=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,pageList);
            spinner.setAdapter(arrayAdapter2);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    //cutTitleList.clear();
                    cutTitleList=cutList(titleList,position+1);

                    arrayAdapter1=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,cutTitleList);

                    mListView.setAdapter(arrayAdapter1);

                    //arrayAdapter1.notifyDataSetChanged();

                    Log.i("info",position+1+"");
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
  }
    public List<String> cutList(List<String> titlelist,int currentPage){
        Page page=new Page(titlelist.size()/40,currentPage,40);
        if(page.getEndIndex()>titlelist.size()){
            return titlelist.subList(page.getStartIndex(),titlelist.size());
        }
        return titlelist.subList(page.getStartIndex(),page.getEndIndex());
    }

}

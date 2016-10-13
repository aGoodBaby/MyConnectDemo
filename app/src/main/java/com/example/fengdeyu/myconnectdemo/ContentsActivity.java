package com.example.fengdeyu.myconnectdemo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ContentsActivity extends AppCompatActivity {

    private TextView tvContents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contents);


        String URL=this.getIntent().getExtras().getString("titleUrl");

        tvContents= (TextView) findViewById(R.id.tv_contents);

        new ContentsAsyncTask().execute(URL);


    }

    class ContentsAsyncTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... params) {
            String contents="";

            try {
                Document doc= Jsoup.connect(params[0]).get();
                contents=doc.getElementById("contents").text();





            } catch (IOException e) {
                e.printStackTrace();
            }



            return contents;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            StringBuilder sb =new StringBuilder(s);
//            sb.insert(sb.indexOf(" "),"\n");

            int i=0;

//            i=sb.indexOf(" ",i);
//            Log.i("info",i+"");
//            sb.insert(5,"\n");




            while (sb.indexOf(" ",i+3)!=-1){
                i=sb.indexOf(" ",i+3);
                sb.insert(i,"\n");
            }



            tvContents.setText(sb);
        }
    }
}

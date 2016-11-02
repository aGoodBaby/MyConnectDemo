package com.example.fengdeyu.myconnectdemo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookItemActivity extends AppCompatActivity {
    private ListView mListView;
    private String URL="http://so.23wx.com/cse/search?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_layout);

        mListView= (ListView) findViewById(R.id.lv_book);

        Intent intent=getIntent();
        URL=URL+intent.getStringExtra("search_url");

        Log.i("info",URL);

        new BookItemAnsycTask().execute(URL);


    }


    class BookItemAnsycTask extends AsyncTask<String,Void,List<BookBean>>{

        @Override
        protected List<BookBean> doInBackground(String... params) {

            Document doc;
            List<BookBean> bookBeanList=new ArrayList<>();


            try {
                doc= Jsoup.connect(params[0]).get();



                Elements links=doc.select("div.result-game-item-detail");


                Elements linkHrefs=doc.select("img.result-game-item-pic-link-img");
//                Elements linkHrefs=doc.select("a.result-game-item-pic-link");

                Log.i("info",""+linkHrefs.size());





                for (Element linkHref:linkHrefs){
                    //String titleUrl=linkHref.getElementsByTag("a").first().attr("class");
                    //Elements hrefs=link.select(".result-item-title.result-game-item-title");
                    //Log.i("info",""+hrefs.size());
                    //Log.i("info",linkHref.attr("src")+"123");
                    Log.i("info",linkHref.parent().attr("href")+"123");

                }


                BookBean bookBean;

                for (Element link : links) {
                    String title=link.select("h3.result-item-title.result-game-item-title").first().text();
//                    String titleUrl=link.select("h3.result-item-title.result-game-item-title").first().attr("title");
////
//                    Log.i("info",titleUrl+"123");
//                    Log.i("info",link.select(".result-game-item-pic-link-img").attr("src")+"123");
//                    Log.i("info",link.select("a.result-game-item-pic-link").attr("href")+"123");

                    String s=link.select("p.result-game-item-desc").text();



                    String content=s.substring(0,s.indexOf(".."))+"\n"+s.substring(s.indexOf("..")+2);


                    bookBean=new BookBean();
                    bookBean.bookTitle=title;
                    bookBean.bookContent=content;

                    bookBeanList.add(bookBean);


                }


            } catch (IOException e) {


                e.printStackTrace();
            }

            return bookBeanList;
        }

        @Override
        protected void onPostExecute(List<BookBean> bookBeen) {
            super.onPostExecute(bookBeen);

            BookAdapter adapter=new BookAdapter(BookItemActivity.this,bookBeen);
            mListView.setAdapter(adapter);
        }
    }
}

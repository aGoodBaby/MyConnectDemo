package com.example.fengdeyu.myconnectdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity {

    private EditText et_search;
    private Button bt_search;
    public String search_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        et_search= (EditText) findViewById(R.id.et_search);
        bt_search= (Button) findViewById(R.id.bt_search);





        bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!et_search.getText().toString().equals("")) {
                    search_url="s=15772447660171623812&entry=1&q="+et_search.getText();
                    Intent intent = new Intent(SearchActivity.this, BookItemActivity.class);
                    intent.putExtra("search_url", search_url);
                    startActivity(intent);
                }
            }
        });
    }
}

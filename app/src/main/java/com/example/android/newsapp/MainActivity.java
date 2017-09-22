package com.example.android.newsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Adapter for the News
    private  NewsAdapter mNewsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<News> News = new ArrayList<>();

        //Find the ListView to display the news
        ListView view = (ListView) findViewById(R.id.list_view);
        mNewsAdapter = new NewsAdapter(this,News);

        view.setAdapter(mNewsAdapter);
    }
}

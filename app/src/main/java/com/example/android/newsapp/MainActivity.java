package com.example.android.newsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Adapter for the News
    private  NewsAdapter mNewsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<News> News = new ArrayList<>();
        News.add(new News("Labour attacks Tories over 'smear' campaign before abuse debate","Education","www.google.com"));
        News.add(new News("'Markets donâ€™t like chaos' â€“ experts debate Brexit watch data","Politics","www.google.com"));
        News.add(new News("Paul Nuttall 'hadn't read Ukip manifesto before TV debate'","UK","www.google.com"));
        News.add(new News("Holyrood suspends referendum debate after Westminster attacks","UK news","www.google.com"));

        //Find the ListView to display the news
        ListView view = (ListView) findViewById(R.id.list_view);
        mNewsAdapter = new NewsAdapter(this,News);

        view.setAdapter(mNewsAdapter);
    }
}

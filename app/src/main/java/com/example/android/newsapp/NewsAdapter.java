package com.example.android.newsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Mateusz on 22/09/2017.
 * Custom adapter to display news
 */

public class NewsAdapter extends ArrayAdapter <News> {

    public NewsAdapter(@NonNull Context context, @NonNull List<News> objects) {
        super(context, 0 , objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View newsView = convertView;

        //If there is no view to recycle inflate new one
        if(convertView == null){
            newsView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_view, parent, false);
        }

        //Get the current News to set the specific information like title and section name
        News currentNews = getItem(position);

        //Find TextView to set the section name
        TextView sectionName = (TextView) newsView.findViewById(R.id.section_name);
        sectionName.setText(currentNews.getmSectionName());

        //Find TextView to set the title
        TextView title = (TextView) newsView.findViewById(R.id.title);
        title.setText(currentNews.getmTitle());



        return newsView;
    }
}

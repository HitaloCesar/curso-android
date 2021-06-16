package com.example.android.news;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.news.R;

import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<News> {
    private static final String LOG_TAG = NewsAdapter.class.getSimpleName();

    private Context context;

    public NewsAdapter(Activity context, ArrayList<News> news) {
        super(context, 0, news);
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }

        News currentNews = getItem(position);

        String title = currentNews.getTitle();

        TextView locationTextView1 = (TextView) listItemView.findViewById(R.id.titleNew);
        locationTextView1.setText(title);


        String description = currentNews.getDescription();
        if(!description.equals("null")) {
            TextView locationTextView2 = (TextView) listItemView.findViewById(R.id.descriptionNew);
            locationTextView2.setText(description);
        }


        return listItemView;
    }
}

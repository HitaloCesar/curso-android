package com.example.android.news;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.news.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

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

        //setting the description to its the listview place
        String title = currentNews.getTitle();
        TextView locationTextView1 = (TextView) listItemView.findViewById(R.id.titleNew);
        locationTextView1.setText(title);

        //setting the description to its the listview place
        String description = currentNews.getDescription();
        if(!description.equals("null")) {
            TextView locationTextView2 = (TextView) listItemView.findViewById(R.id.descriptionNew);
            locationTextView2.setText(description);
        }

        //getting image from link in news object and setting it to the imageview
        String imageUri = currentNews.getUrlImage();
        ImageView ivBasicImage = (ImageView) listItemView.findViewById(R.id.new_image);
        RequestCreator ob = Picasso.with(context).load(imageUri);
        ob.into(ivBasicImage);


        return listItemView;
    }
}

/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.news;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.news.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewsActivity extends AppCompatActivity {

    public static final String LOG_TAG = NewsActivity.class.getName();

    private static final String COMMON_URL = "https://newsapi.org/v2/top-headlines?";

    private static final String API_KEY = "4376ef348b124daab6a2e6946539a79f";

    private String currentURL;

    private String currentCountry;

    private String currentCategory;

    public NewsAdapter mAdapter;


    public ArrayList<News> array = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity);

        //creating and setting the adapter to the listview
        final ListView listView = (ListView) findViewById(R.id.list);
        mAdapter = new NewsAdapter(this, array);
        listView.setAdapter(mAdapter);

        //making the news in listview items open the news link
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public final void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News currentNews = mAdapter.getItem(position);
                Uri NewsUri = Uri.parse(currentNews.getUrl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, NewsUri);
                startActivity(websiteIntent);
            }
        });

        //Handling the country's spinner event
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.spinner_array1, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //method to run a new async task to request from API
            private void runAsyncTask(){
                currentURL = COMMON_URL + "country="+currentCountry+"&category="+currentCategory+"&apiKey="+API_KEY;
                NewsAsyncTask task = new NewsAsyncTask();
                task.execute(currentURL);
            }

            //handling the selected item to atualize the globals variables related to the URL to make request
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selected = parentView.getItemAtPosition(position).toString();
                selected =  selected.substring(selected.indexOf('(')+1,selected.indexOf(')'));
                selected.toLowerCase();
                currentCountry = selected;

                runAsyncTask();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                currentCategory="general";
                currentCountry="br";
                runAsyncTask();
            }

        });


        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.spinner_array2, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //method to run a new async task to request from API
            private void runAssyncTask(){
                currentURL = COMMON_URL + "country="+currentCountry+"&category="+currentCategory+"&apiKey="+API_KEY;
                NewsAsyncTask task = new NewsAsyncTask();
                task.execute(currentURL);
            }

            //handling the selected item to atualize the globals variables related to the URL to make request
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                currentCategory="general";
                String selected = parentView.getItemAtPosition(position).toString();
                ArrayList<String> listAux = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.spinner_array2)));
                if(selected.equals(listAux.get(0)))
                    currentCategory = "science";
                if(selected.equals(listAux.get(1)))
                    currentCategory = "entertainment";
                if(selected.equals(listAux.get(2)))
                    currentCategory = "sports";
                if(selected.equals(listAux.get(3)))
                    currentCategory = "general";
                if(selected.equals(listAux.get(4)))
                    currentCategory = "business";
                if(selected.equals(listAux.get(5)))
                    currentCategory = "health";
                if(selected.equals(listAux.get(6)))
                    currentCategory = "technology";

                runAssyncTask();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                currentCategory="general";
                currentCountry="br";
                runAssyncTask();
            }

        });

    }


    //some methods that can possibly be overriden
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected  void onStop(){
        super.onStop();
    }



    //Async task overriding the methods doInBackground and onPostExcecute.
    //This asynctask is important to keep the app running fine while making a new request
    private class NewsAsyncTask extends AsyncTask<String, Void, List<News>> {
        @Override
        protected List<News> doInBackground(String... urls) {
            if(urls.length < 1 || urls[0] == null){
                return null;
            }
            List<News> result;
            result = QueryUtils.fetchNewsData(urls[0]);
            return result;
        }

        @Override
        protected void onPostExecute(List<News> data) {
            super.onPostExecute(data);
            mAdapter.clear();
            mAdapter.addAll(data);
            mAdapter.notifyDataSetChanged();
        }
    }
}

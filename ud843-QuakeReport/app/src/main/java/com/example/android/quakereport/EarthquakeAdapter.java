package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    private static final String LOG_TAG = EarthquakeAdapter.class.getSimpleName();

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private int getMagnitudeColor(double mag){
        if(mag <= 1){
            return ContextCompat.getColor(getContext(), R.color.magnitude1);
        }
        else if(mag <= 1){
            return ContextCompat.getColor(getContext(), R.color.magnitude2);
        }
        else if(mag <= 2){
            return ContextCompat.getColor(getContext(), R.color.magnitude3);
        }
        else if(mag <= 3){
            return ContextCompat.getColor(getContext(), R.color.magnitude4);
        }
        else if(mag <= 4){
            return ContextCompat.getColor(getContext(), R.color.magnitude5);
        }
        else if(mag <= 5){
            return ContextCompat.getColor(getContext(), R.color.magnitude6);
        }
        else if(mag <= 6){
            return ContextCompat.getColor(getContext(), R.color.magnitude7);
        }
        else if(mag <= 7){
            return ContextCompat.getColor(getContext(), R.color.magnitude8);
        }
        else if(mag <= 8){
            return ContextCompat.getColor(getContext(), R.color.magnitude9);
        }
        else if(mag <= 9){
            return ContextCompat.getColor(getContext(), R.color.magnitude10plus);
        }
        else{
            return ContextCompat.getColor(getContext(), R.color.magnitude1);
        }
    }

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquacke_list_item, parent, false);
        }

        Earthquake currentEarthquake = getItem(position);

        String mag = formatMagnitude(currentEarthquake.getMagnitude());
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude);
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);


        magnitudeTextView.setText(mag);

        String[] location = currentEarthquake.getLocation().split(",");

        TextView locationTextView1 = (TextView) listItemView.findViewById(R.id.location1);
        locationTextView1.setText(location[0]);
        try {
            TextView locationTextView2 = (TextView) listItemView.findViewById(R.id.location2);
            locationTextView2.setText(location[1]);
        }catch(Exception e){
            Log.e("EarthquakeAdapter", "ERROR: failed to displaying second part of the location", e);
        }

        Date time = new Date(currentEarthquake.getTime());

        String dateString = formatDate(time);
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        dateTextView.setText(dateString);

        String timeString = formatTime(time);
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time);
        timeTextView.setText(timeString);


        return listItemView;
    }
}

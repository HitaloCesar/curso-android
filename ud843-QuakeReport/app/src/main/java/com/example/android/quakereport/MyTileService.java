package com.example.android.quakereport;


import android.arch.lifecycle.Lifecycle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Icon;
import android.preference.PreferenceManager;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import android.util.Log;
import android.widget.Toast;

public class MyTileService extends TileService {

    public static final String LOG_TAG = MyTileService.class.getName();

    @Override
    public void onClick() {
        super.onClick();

        Tile tile = getQsTile();
        Intent intent = new Intent(this, EarthquakeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);


        boolean isActive = (tile.getState() == Tile.STATE_ACTIVE);
        if (isActive) {
            tile.setState(Tile.STATE_INACTIVE);
            tile.setLabel("Earthquake News");
            tile.setIcon(Icon.createWithResource(this, android.R.drawable.ic_media_play));


        }
        else {
            tile.setState(Tile.STATE_ACTIVE);
            tile.setIcon(Icon.createWithResource(this, android.R.drawable.ic_media_pause));
            tile.setLabel("Earthquake News");

        }
        tile.updateTile();
    }

    @Override
    public void onTileAdded() {
        super.onTileAdded();


        Tile tile = getQsTile();
        tile.setState(Tile.STATE_ACTIVE);


        tile.setLabel("Earthquake News");

        tile.setIcon(Icon.createWithResource(this, android.R.drawable.ic_media_play));
        tile.updateTile();

        Toast.makeText(getApplicationContext(), "Earthquake tile added", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTileRemoved() {
        super.onTileRemoved();
    }

    @Override
    public void onStartListening() {
        super.onStartListening();

        Tile tile = getQsTile();

        if (tile.getState() == Tile.STATE_ACTIVE) {
            tile.setLabel("Earthquake News");
        }
        else{
            tile.setLabel("Earthquake News");
        }

        tile.updateTile();
    }


    @Override
    public void onStopListening() {
        super.onStopListening();
    }
}

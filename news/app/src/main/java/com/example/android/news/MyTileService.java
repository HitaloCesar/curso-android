package com.example.android.news;


import android.content.Intent;
import android.graphics.drawable.Icon;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import android.widget.Toast;

import com.example.android.news.NewsActivity;

public class MyTileService extends TileService {

    public static final String LOG_TAG = MyTileService.class.getName();

    //actions when the tile is clicked
    //as the tile will just open the window, it will be kept in active modee
    @Override
    public void onClick() {
        super.onClick();

        //open the app when tile is clicked.
        Tile tile = getQsTile();
        Intent intent = new Intent(this, NewsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        boolean isActive = (tile.getState() == Tile.STATE_ACTIVE);
        //the if else below are here just to simplify for future modifications
        if (isActive) {
            tile.setState(Tile.STATE_ACTIVE);
            tile.setLabel("News");
            tile.setIcon(Icon.createWithResource(this, android.R.drawable.ic_menu_agenda));

        }
        else {
            tile.setState(Tile.STATE_ACTIVE);
            tile.setIcon(Icon.createWithResource(this, android.R.drawable.ic_menu_agenda));
            tile.setLabel("News");

        }
        tile.updateTile();
    }

    //actions after the tile is added
    //as it will just open a window, it will start it in the active mode and set the agenda icon to it
    @Override
    public void onTileAdded() {
        super.onTileAdded();


        Tile tile = getQsTile();
        tile.setState(Tile.STATE_ACTIVE);


        tile.setLabel("News");

        tile.setIcon(Icon.createWithResource(this, android.R.drawable.ic_menu_agenda));
        tile.updateTile();

        Toast.makeText(getApplicationContext(), "News tile added", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTileRemoved() {
        super.onTileRemoved();
    }

    //actions when tile is visible
    @Override
    public void onStartListening() {
        super.onStartListening();

        Tile tile = getQsTile();

        if (tile.getState() == Tile.STATE_ACTIVE) {
            tile.setLabel("News");
        }
        else{
            tile.setLabel("News");
        }

        tile.updateTile();
    }


    @Override
    public void onStopListening() {
        super.onStopListening();
    }
}

package com.projects.gerhardschoeman.yatzy;

import android.view.View;
import android.widget.TextView;

/**
 * Created by Gerhard on 26/11/2015.
 */
public class GameListViewHolder {

    public TextView id;
    public TextView description;
    public TextView startTime;
    public TextView gameType;

    public GameListViewHolder(View v){
        id = (TextView)v.findViewById(R.id.txtGameID);
        description = (TextView)v.findViewById(R.id.txtGameDescription);
        startTime = (TextView)v.findViewById(R.id.txtStartTime);
        gameType = (TextView)v.findViewById(R.id.txtGameType);
    }
}

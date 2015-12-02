package com.projects.gerhardschoeman.yatzy;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Gerhard on 27/11/2015.
 */
public class ScoreboardListViewHolder {

    public ImageView moreless;
    public TextView position;
    public TextView name;
    public TextView score;

    public ScoreboardListViewHolder(View v){
        name = (TextView)v.findViewById(R.id.txtSBPlayerName);
        score = (TextView)v.findViewById(R.id.txtSBPlayerScore);
        position = (TextView)v.findViewById(R.id.txtSBPosition);
        moreless = (ImageView)v.findViewById(R.id.imgSBPlayerGameDetail);
    }
}

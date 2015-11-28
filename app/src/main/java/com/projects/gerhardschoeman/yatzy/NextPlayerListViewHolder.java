package com.projects.gerhardschoeman.yatzy;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Gerhard on 28/11/2015.
 */
public class NextPlayerListViewHolder {
    public ImageView photo;
    public TextView name;
    public TextView moves;

    public NextPlayerListViewHolder(View v){
        photo = (ImageView)v.findViewById(R.id.imgNextPlayer);
        name = (TextView)v.findViewById(R.id.txtNextPlayerName);
        moves = (TextView)v.findViewById(R.id.txtNextPlayerRemainingMoves);
    }
}

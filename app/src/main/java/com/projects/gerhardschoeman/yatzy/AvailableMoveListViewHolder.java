package com.projects.gerhardschoeman.yatzy;

import android.view.View;
import android.widget.TextView;

/**
 * Created by Gerhard on 28/11/2015.
 */
public class AvailableMoveListViewHolder {
    public TextView description;
    public TextView score;

    public AvailableMoveListViewHolder(View v){
        description = (TextView)v.findViewById(R.id.txtAvailableMoveDescription);
        score = (TextView)v.findViewById(R.id.txtAvailableMoveScore);
    }
}

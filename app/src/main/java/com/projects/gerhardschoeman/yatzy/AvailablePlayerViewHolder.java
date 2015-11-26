package com.projects.gerhardschoeman.yatzy;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Gerhard on 26/11/2015.
 */
public class AvailablePlayerViewHolder {
    public ImageView photo;
    public TextView name;
    public CheckBox chosen;

    public interface CheckBoxCallback{
        void onClick(boolean checked,String player);
    }

    private CheckBoxCallback cbCallback;


    public AvailablePlayerViewHolder(View v,CheckBoxCallback cb){
        cbCallback = cb;
        photo = (ImageView)v.findViewById(R.id.imgAvailablePlayer);
        name = (TextView)v.findViewById(R.id.txtAvailablePlayerName);
        chosen = (CheckBox)v.findViewById(R.id.chkChosen);
        chosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbCallback.onClick(chosen.isChecked(), name.getText().toString());
            }
        });
    }
}

package com.projects.gerhardschoeman.yatzy;

import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import com.projects.gerhardschoeman.yatzy.data.DataProjections;

import java.util.HashSet;

/**
 * Created by Gerhard on 26/11/2015.
 */
public class AvailablePlayerAdapter extends CursorAdapter implements AvailablePlayerViewHolder.CheckBoxCallback {

    private static final String LOGTAG = AvailablePlayerAdapter.class.getSimpleName();

    public HashSet<String> chosenPlayers = new HashSet<>();

    public AvailablePlayerAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.dialog_add_game_list_item,parent,false);
        v.setTag(new AvailablePlayerViewHolder(v,this));
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        AvailablePlayerViewHolder vh = (AvailablePlayerViewHolder)view.getTag();
        String n = cursor.getString(DataProjections.Player_ALL.COL_NAME);
        vh.name.setText(n);
        byte[] imgData = cursor.getBlob(DataProjections.Player_ALL.COL_PHOTO);
        if(imgData!=null) {
            vh.photo.setImageBitmap(BitmapFactory.decodeByteArray(imgData, 0, imgData.length));
        }else{
            vh.photo.setImageDrawable(context.getDrawable(R.drawable.unknown));
        }
        vh.chosen.setChecked(chosenPlayers.contains(n));
    }

    @Override
    public void onClick(boolean checked, String player) {
        if(checked){
            Log.d(LOGTAG, "Player " + player + " selected");
            chosenPlayers.add(player);
        }else{
            Log.d(LOGTAG,player + " is no longer selected");
            chosenPlayers.remove(player);
        }
    }
}

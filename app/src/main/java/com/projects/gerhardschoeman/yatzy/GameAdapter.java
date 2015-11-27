package com.projects.gerhardschoeman.yatzy;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import com.projects.gerhardschoeman.yatzy.data.DataProjections;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Gerhard on 26/11/2015.
 */
public class GameAdapter extends CursorAdapter {
    public GameAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.game_list_item,parent,false);
        v.setTag(new GameListViewHolder(v));
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        GameListViewHolder vh  = (GameListViewHolder)view.getTag();
        vh.id.setText(cursor.getString(DataProjections.Game_ALL.COL_ID));
        String d = cursor.getString(DataProjections.Game_ALL.COL_DESCRIPTION);
        vh.description.setText(d==null || d.length()<1 ? "n/a" : d);
        long st = cursor.getLong(DataProjections.Game_ALL.COL_STARTED);
        Date startDate = new Date(st);
        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        vh.startTime.setText(format.format(startDate));
        vh.gameType.setText(cursor.getString(DataProjections.Game_ALL.COL_TYPE));
    }
}

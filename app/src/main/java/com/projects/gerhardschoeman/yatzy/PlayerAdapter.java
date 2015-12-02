package com.projects.gerhardschoeman.yatzy;

import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.support.v4.view.LayoutInflaterFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;

import com.projects.gerhardschoeman.yatzy.data.DataProjections;

/**
 * Created by Gerhard on 24/11/2015.
 */
public class PlayerAdapter extends CursorAdapter {

    private static final String LOGTAG = PlayerAdapter.class.getSimpleName();

    public PlayerListViewHolder.TouchCallback viewholderCallback;

    public ListView listview;

    public PlayerAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.player_list_item,parent,false);
        v.setTag(new PlayerListViewHolder(v,viewholderCallback,listview));
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        PlayerListViewHolder vh = (PlayerListViewHolder)view.getTag();
        byte[] imgData = cursor.getBlob(DataProjections.Player_ALL.COL_PHOTO);
        if(imgData!=null) {
            vh.imgPlayer.setImageBitmap(BitmapFactory.decodeByteArray(imgData, 0, imgData.length));
        }else{
            vh.imgPlayer.setImageDrawable(context.getDrawable(R.drawable.unknown));
        }
        vh.txtPlayerName.setText(cursor.getString(DataProjections.Player_ALL.COL_NAME));
    }
}

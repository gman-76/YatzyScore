package com.projects.gerhardschoeman.yatzy;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;

import com.projects.gerhardschoeman.yatzy.game.Player;

import java.util.ArrayList;

/**
 * Created by Gerhard on 27/11/2015.
 */
public class ScoreboardAdapter extends BaseAdapter {

    private static final String LOGTAG = ScoreboardAdapter.class.getSimpleName();

    private ArrayList<Player> players;
    private Context context;

    public ScoreboardAdapter(Context c,ArrayList<Player> p){
        context = c;
        players = p;
    }

    @Override
    public int getCount() {
        return players==null ? 0 : players.size();
    }

    @Override
    public Object getItem(int position) {
        return players.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ScoreboardListViewHolder vh;
        if(convertView==null){
            convertView =  LayoutInflater.from(context).inflate(R.layout.scoreboard_list_item, parent, false);
            vh = new ScoreboardListViewHolder(convertView);
            convertView.setTag(vh);
        }else{
            vh = (ScoreboardListViewHolder)convertView.getTag();
        }

        Player p = players.get(position);

        vh.name.setText(p.getName());
        vh.score.setText(Integer.toString(p.getTotalScore()));

        return convertView;
    }
}

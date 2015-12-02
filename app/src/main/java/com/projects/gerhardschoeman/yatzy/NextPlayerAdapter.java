package com.projects.gerhardschoeman.yatzy;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.projects.gerhardschoeman.yatzy.game.Player;

import java.util.ArrayList;

/**
 * Created by Gerhard on 28/11/2015.
 */
public class NextPlayerAdapter extends BaseAdapter {

    ArrayList<Player> players;
    Context mContext;

    NextPlayerAdapter(Context context,ArrayList<Player> p){
        mContext = context;
        players = p;
    }

    @Override
    public int getCount() {
        return players!=null ? players.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        if(players==null) return null;
        return players.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NextPlayerListViewHolder vh;
        if(convertView==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.next_player_list_item,parent,false);
            vh = new NextPlayerListViewHolder(convertView);
            convertView.setTag(vh);
        }else{
            vh = (NextPlayerListViewHolder)convertView.getTag();
        }

        Player p = players.get(position);

        vh.name.setText(p.getName());
        vh.moves.setText(String.format(mContext.getString(R.string.current_game_player_list_moves_remaining),p.getMovesRemaining()));
        byte[] photo = p.getPhoto();
        if(photo!=null){
            vh.photo.setImageBitmap(BitmapFactory.decodeByteArray(photo,0,photo.length));
        }else{
            vh.photo.setImageDrawable(mContext.getDrawable(R.drawable.unknown));
        }

        return convertView;
    }
}

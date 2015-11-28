package com.projects.gerhardschoeman.yatzy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.projects.gerhardschoeman.yatzy.game.ScoreGroup;

import java.util.ArrayList;

/**
 * Created by Gerhard on 28/11/2015.
 */
public class AvailableMoveAdapter extends BaseAdapter {
    private ArrayList<ScoreGroup> moves;

    private Context context;

    public AvailableMoveAdapter(Context c){
        context = c;
    }

    public void swapData(ArrayList<ScoreGroup> m){
        moves = m;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return moves!=null ? moves.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        if(moves==null) return null;
        return moves.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AvailableMoveListViewHolder vh;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.available_move_list_item,parent,false);
            vh = new AvailableMoveListViewHolder(convertView);
            convertView.setTag(vh);
        }else{
            vh = (AvailableMoveListViewHolder)convertView.getTag();
        }

        ScoreGroup sg = moves.get(position);

        vh.description.setText(sg.getDescription());
        vh.score.setText(Integer.toString(sg.getPredictedScore()));

        return convertView;
    }
}

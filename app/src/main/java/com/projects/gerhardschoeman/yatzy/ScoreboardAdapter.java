package com.projects.gerhardschoeman.yatzy;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.CursorAdapter;

import com.projects.gerhardschoeman.yatzy.game.Player;
import com.projects.gerhardschoeman.yatzy.game.ScoreGroup;

import java.util.ArrayList;

/**
 * Created by Gerhard on 27/11/2015.
 */
public class ScoreboardAdapter extends BaseExpandableListAdapter {

    private static final String LOGTAG = ScoreboardAdapter.class.getSimpleName();

    private ArrayList<Player> players;
    private Context context;

    public ScoreboardAdapter(Context c,ArrayList<Player> p){
        context = c;
        players = p;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return players!=null ? players.get(groupPosition) : null;
    }

    @Override
    public int getGroupCount() {
        return players!=null ? players.size() : 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return players!=null ? players.get(groupPosition) : null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ScoreboardListViewHolder vh;
        if(convertView==null){
            convertView =  LayoutInflater.from(context).inflate(R.layout.scoreboard_list_item, parent, false);
            vh = new ScoreboardListViewHolder(convertView);
            convertView.setTag(vh);
        }else{
            vh = (ScoreboardListViewHolder)convertView.getTag();
        }
        Player p = players.get(groupPosition);

        if(isExpanded){
            vh.moreless.setImageDrawable(context.getDrawable(R.drawable.minus_circle));
        }else{
            vh.moreless.setImageDrawable(context.getDrawable(R.drawable.plus_circle));
        }

        vh.name.setText(p.getName());
        vh.score.setText(Integer.toString(p.getTotalScore()));
        vh.position.setText(Integer.toString(groupPosition+1) + ".");

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ScoreboardListViewDetailHolder vh;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.scoreboard_list_item_detail,parent,false);
            vh = new ScoreboardListViewDetailHolder(convertView);
            convertView.setTag(vh);
        }else{
            vh = (ScoreboardListViewDetailHolder)convertView.getTag();
        }
        Player p = (Player)getChild(groupPosition,childPosition);

        vh.total.setText(Integer.toString(p.getTotalScore()));

        vh.views.get(6).setText("-"); //pair not valid for all games
        vh.views.get(7).setText("-"); //two pair not valid for all games

        for(ScoreGroup sg:p.getMoveHistory()){
            if(sg.hasPlayed()) {
                vh.views.get(sg.getID()).setText(Integer.toString(sg.getMarkedScore()));
            }else{
                vh.views.get(sg.getID()).setText("0");
            }
        }

        vh.uppertotal.setText(Integer.toString(p.getUpperTotal()));
        vh.bonus.setText(Integer.toString(p.getBonusScore()));
        vh.lowertotal.setText(Integer.toString(p.getLowerTotal()));

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

}

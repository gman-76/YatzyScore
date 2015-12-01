package com.projects.gerhardschoeman.yatzy;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.projects.gerhardschoeman.yatzy.data.DataContract;
import com.projects.gerhardschoeman.yatzy.data.DataProjections;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Gerhard on 26/11/2015.
 */
public class GameAdapter extends BaseExpandableListAdapter {

    private Context mContext;

    public class GameData{
        public int id;
        public String description;
        public long startTime;
        public long endTime;
        public int gameType;
    }

    HashMap<String,ArrayList<GameData>> data;
    ArrayList<String> keySet;

    public GameAdapter(Context context){
        mContext = context;

        Cursor c = context.getContentResolver().query(DataContract.GameEntry.CONTENT_URI,
                DataProjections.Game_ALL.COLUMNS,
                null,
                null,
                DataContract.GameEntry.COL_STARTED + " DESC");

        keySet = new ArrayList<>();
        keySet.add(context.getString(R.string.gamelist_unfinished_group));
        keySet.add(context.getString(R.string.gamelist_finished_group));

        data = new HashMap<>();

        ArrayList<GameData> unfinished = new ArrayList<>();
        ArrayList<GameData> finished = new ArrayList<>();

        if(c!=null && c.moveToFirst()){
            do{
                GameData gd = new GameData();
                gd.id = c.getInt(DataProjections.Game_ALL.COL_ID);
                String desc = c.getString(DataProjections.Game_ALL.COL_DESCRIPTION);
                if(desc==null || desc.length()<1) desc = context.getString(R.string.gamelist_game_no_description);
                gd.description = desc;
                gd.gameType = c.getInt(DataProjections.Game_ALL.COL_TYPE);
                gd.startTime = c.getLong(DataProjections.Game_ALL.COL_STARTED);
                gd.endTime = c.getLong(DataProjections.Game_ALL.COL_FINISHED);

                if(gd.endTime<0){
                    unfinished.add(gd);
                }else{
                    finished.add(gd);
                }
            }while(c.moveToNext());
        }

        data.put(keySet.get(0),unfinished);
        data.put(keySet.get(1),finished);
    }

    @Override
    public int getGroupCount() {
        return keySet.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return data.get(keySet.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return keySet.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return data.get(keySet.get(groupPosition)).get(childPosition);
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

        if(convertView==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.game_list_item_separator,parent,false);
        }
        TextView tv = (TextView)convertView.findViewById(R.id.txtSeparatorTitle);
        tv.setText(keySet.get(groupPosition));

        ImageView img = (ImageView)convertView.findViewById(R.id.btShowMoreLess);
        if(isExpanded){
            img.setImageDrawable(mContext.getDrawable(R.drawable.less));
        }else{
            img.setImageDrawable(mContext.getDrawable(R.drawable.more));
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        GameListViewHolder vh;
        if(convertView==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.game_list_item,parent,false);
            vh = new GameListViewHolder(convertView);
            convertView.setTag(vh);
        }else{
            vh = (GameListViewHolder)convertView.getTag();
        }

        GameData gd = (GameData)getChild(groupPosition,childPosition);

        vh.id.setText(Integer.toString(gd.id));
        vh.description.setText(gd.description);
        Date startDate = new Date(gd.startTime);
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yy HH:mm:ss");
        vh.startTime.setText(format.format(startDate));

        String[] types = mContext.getResources().getStringArray(R.array.GameTypes);
        vh.gameType.setText(types[gd.gameType]);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

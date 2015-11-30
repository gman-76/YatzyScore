package com.projects.gerhardschoeman.yatzy;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.projects.gerhardschoeman.yatzy.data.DataProjections;

/**
 * Created by Gerhard on 24/11/2015.
 */
public class PlayerListViewHolder implements View.OnTouchListener {

    private static final String LOGTAG = PlayerListViewHolder.class.getSimpleName();

    public ImageView imgPlayer;
    public TextView txtPlayerName;

    public interface TouchCallback{
        void clickedPlayer(String name);
        void deletePlayer(String name);
    }

    private RelativeLayout containerView;
    private LinearLayout tileView;
    private float downX;
    private int lastDeltaX;

    private TouchCallback touchCallback;

    public PlayerListViewHolder(View v,TouchCallback cb){
        touchCallback = cb;
        containerView = (RelativeLayout)v.findViewById(R.id.list_item_container_view);
        tileView = (LinearLayout)v.findViewById(R.id.list_item_main_tile);
        imgPlayer = (ImageView)v.findViewById(R.id.imgPlayer);
        txtPlayerName = (TextView)v.findViewById(R.id.txtPlayerName);
        v.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d("VH", "action down");
                downX = event.getX();
                lastDeltaX=0;
                liftTile();
                return true;
            case MotionEvent.ACTION_MOVE: {
                float x = event.getX() + v.getTranslationX();
                float deltaX = x - downX;
                if(Math.abs(deltaX)>20) {
                    Log.d("VH", "moving " + Float.toString(deltaX) + " in view " + Integer.toString(v.getWidth()) + " tileview width " + Integer.toString(tileView.getWidth()));
                    lastDeltaX = (int) deltaX;
                    if (deltaX > 0) {
                        setMargin(lastDeltaX);
                    }
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                Log.d(LOGTAG, "action up");
                dropTile();
                setMargin(0);
                if(lastDeltaX==0){
                    //in fact - we clicked
                    Log.d(LOGTAG,"clicked on " + txtPlayerName.getText().toString());
                    if(touchCallback!=null) touchCallback.clickedPlayer(txtPlayerName.getText().toString());
                }else if((float)lastDeltaX/(float)tileView.getWidth()>0.75){
                    //we have slid the tile more that 75% out so delete
                    if(touchCallback!=null) touchCallback.deletePlayer(txtPlayerName.getText().toString());
                    break;
                }
                break;
            }
            case MotionEvent.ACTION_CANCEL:
                Log.d(LOGTAG, "action cancelled");
                dropTile();
                setMargin(0);
                break;
        }
        return false;
    }

    private void setMargin(int d){
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) tileView.getLayoutParams();
        params.rightMargin = -d;
        params.leftMargin = d;
        tileView.setLayoutParams(params);
        tileView.setAlpha((float)1 - (float)d/(float)tileView.getWidth());
    }

    private void liftTile(){
        containerView.animate().z(20).setDuration(300).start();
    }

    private void dropTile(){
        containerView.animate().z(0).setDuration(300).start();
    }
}

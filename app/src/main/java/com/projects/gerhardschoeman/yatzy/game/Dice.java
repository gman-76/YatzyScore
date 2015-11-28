package com.projects.gerhardschoeman.yatzy.game;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.projects.gerhardschoeman.yatzy.R;

/**
 * Created by Gerhard on 28/11/2015.
 */
public class Dice {
    private int face = -1;

    private Context context;

    private ImageView one;
    private ImageView two;
    private ImageView three;
    private ImageView four;
    private ImageView five;
    private ImageView six;

    public interface Callback{
        void diceClicked();
    }

    private Callback callback;

    public Dice(Context c,Callback cb,View d1,View d2,View d3,View d4,View d5,View d6){
        context = c;
        callback = cb;
        one = (ImageView)d1;
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFaceValue(1);
                if(callback!=null) callback.diceClicked();
            }
        });
        two = (ImageView)d2;
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFaceValue(2);
                if(callback!=null) callback.diceClicked();
            }
        });
        three = (ImageView)d3;
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFaceValue(3);
                if(callback!=null) callback.diceClicked();
            }
        });
        four = (ImageView)d4;
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFaceValue(4);
                if(callback!=null) callback.diceClicked();
            }
        });
        five = (ImageView)d5;
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFaceValue(5);
                if(callback!=null) callback.diceClicked();
            }
        });
        six = (ImageView)d6;
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFaceValue(6);
                if(callback!=null) callback.diceClicked();
            }
        });
    }

    public void setFaceValue(int f){
        if(f<1 || f>6) return;
        selectNothing();
        face=f;
        switch(f){
            case 1: one.setImageDrawable(context.getDrawable(R.drawable.dice_one_selected)); break;
            case 2: two.setImageDrawable(context.getDrawable(R.drawable.dice_two_selected)); break;
            case 3: three.setImageDrawable(context.getDrawable(R.drawable.dice_three_selected)); break;
            case 4: four.setImageDrawable(context.getDrawable(R.drawable.dice_four_selected)); break;
            case 5: five.setImageDrawable(context.getDrawable(R.drawable.dice_five_selected)); break;
            case 6: six.setImageDrawable(context.getDrawable(R.drawable.dice_six_selected)); break;
        }
    }
    public int getFaceValue(){return face;}

    private void selectNothing(){
        face = -1;
        one.setImageDrawable(context.getDrawable(R.drawable.dice_one_unselected));
        two.setImageDrawable(context.getDrawable(R.drawable.dice_two_unselected));
        three.setImageDrawable(context.getDrawable(R.drawable.dice_three_unselected));
        four.setImageDrawable(context.getDrawable(R.drawable.dice_four_unselected));
        five.setImageDrawable(context.getDrawable(R.drawable.dice_five_unselected));
        six.setImageDrawable(context.getDrawable(R.drawable.dice_six_unselected));
    }
}

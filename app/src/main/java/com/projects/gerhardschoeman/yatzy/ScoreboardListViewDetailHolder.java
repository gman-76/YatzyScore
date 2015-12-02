package com.projects.gerhardschoeman.yatzy;

import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Gerhard on 02/12/2015.
 */
public class ScoreboardListViewDetailHolder {

    public ArrayList<TextView> views = new ArrayList<>();
    public TextView uppertotal;
    public TextView bonus;
    public TextView lowertotal;
    public TextView total;

    public ScoreboardListViewDetailHolder(View v){
        views.add((TextView)v.findViewById(R.id.txtSBDetailOnes));
        views.add((TextView)v.findViewById(R.id.txtSBDetailTwos));
        views.add((TextView)v.findViewById(R.id.txtSBDetailThrees));
        views.add((TextView)v.findViewById(R.id.txtSBDetailFours));
        views.add((TextView)v.findViewById(R.id.txtSBDetailFives));
        views.add((TextView)v.findViewById(R.id.txtSBDetailSixes));
        uppertotal = (TextView)v.findViewById(R.id.txtSBDetailUpperTotal);
        bonus = (TextView)v.findViewById(R.id.txtSBDetailBonus);
        views.add((TextView)v.findViewById(R.id.txtSBDetailPair));
        views.add((TextView)v.findViewById(R.id.txtSBDetailTwoPair));
        views.add((TextView)v.findViewById(R.id.txtSBDetailThreeOfKind));
        views.add((TextView)v.findViewById(R.id.txtSBDetailFourOfKind));
        views.add((TextView)v.findViewById(R.id.txtSBDetailSmallStraight));
        views.add((TextView)v.findViewById(R.id.txtSBDetailLargeStraight));
        views.add((TextView)v.findViewById(R.id.txtSBDetailFullHouse));
        views.add((TextView)v.findViewById(R.id.txtSBDetailChance));
        views.add((TextView)v.findViewById(R.id.txtSBDetailYatzy));
        lowertotal = (TextView)v.findViewById(R.id.txtSBDetailLowerTotal);
        total = (TextView)v.findViewById(R.id.txtSBDetailTotal);
    }
    /*
    public TextView ones;
    public TextView twos;
    public TextView threes;
    public TextView fours;
    public TextView fives;
    public TextView sixes;
    public TextView uppertotal;
    public TextView bonus;
    public TextView pair;
    public TextView twopair;
    public TextView threeofkind;
    public TextView fourofkind;
    public TextView smallstraight;
    public TextView largestraight;
    public TextView fullhouse;
    public TextView chance;
    public TextView yatzy;
    public TextView lowertotal;
    public TextView total;

    public ScoreboardListViewDetailHolder(View v){
        ones = (TextView)v.findViewById(R.id.txtSBDetailOnes);
        twos = (TextView)v.findViewById(R.id.txtSBDetailTwos);
        threes = (TextView)v.findViewById(R.id.txtSBDetailThrees);
        fours = (TextView)v.findViewById(R.id.txtSBDetailFours);
        fives = (TextView)v.findViewById(R.id.txtSBDetailFives);
        sixes = (TextView)v.findViewById(R.id.txtSBDetailSixes);
        uppertotal = (TextView)v.findViewById(R.id.txtSBDetailUpperTotal);
        bonus = (TextView)v.findViewById(R.id.txtSBDetailBonus);
        pair = (TextView)v.findViewById(R.id.txtSBDetailPair);
        twopair = (TextView)v.findViewById(R.id.txtSBDetailTwoPair);
        threeofkind = (TextView)v.findViewById(R.id.txtSBDetailThreeOfKind);
        fourofkind = (TextView)v.findViewById(R.id.txtSBDetailFourOfKind);
        smallstraight = (TextView)v.findViewById(R.id.txtSBDetailSmallStraight);
        largestraight = (TextView)v.findViewById(R.id.txtSBDetailLargeStraight);
        fullhouse = (TextView)v.findViewById(R.id.txtSBDetailFullHouse);
        chance = (TextView)v.findViewById(R.id.txtSBDetailChance);
        yatzy = (TextView)v.findViewById(R.id.txtSBDetailYatzy);
        lowertotal = (TextView)v.findViewById(R.id.txtSBDetailLowerTotal);
        total = (TextView)v.findViewById(R.id.txtSBDetailTotal);
    }
    */
}

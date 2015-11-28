package com.projects.gerhardschoeman.yatzy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.projects.gerhardschoeman.yatzy.game.Dice;
import com.projects.gerhardschoeman.yatzy.game.Game;
import com.projects.gerhardschoeman.yatzy.game.Player;
import com.projects.gerhardschoeman.yatzy.game.ScoreGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Gerhard on 28/11/2015.
 */
public class CurrentPlayerFragment extends Fragment implements Dice.Callback{

    private static final String LOGTAG = CurrentPlayerFragment.class.getSimpleName();

    private Game mGame;
    private Player mPlayer;

    private Dice d1;
    private Dice d2;
    private Dice d3;
    private Dice d4;
    private Dice d5;

    private AvailableMoveAdapter ad;

    public void setGame(Game game){
        mGame = game;
    }

    public void setPlayer(Player p){
        mPlayer = p;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.current_play_fragment,container,false);

        d1 = new Dice(getActivity(),this,
                rootView.findViewById(R.id.d1_1),
                rootView.findViewById(R.id.d1_2),
                rootView.findViewById(R.id.d1_3),
                rootView.findViewById(R.id.d1_4),
                rootView.findViewById(R.id.d1_5),
                rootView.findViewById(R.id.d1_6));

        d2 = new Dice(getActivity(),this,
                rootView.findViewById(R.id.d2_1),
                rootView.findViewById(R.id.d2_2),
                rootView.findViewById(R.id.d2_3),
                rootView.findViewById(R.id.d2_4),
                rootView.findViewById(R.id.d2_5),
                rootView.findViewById(R.id.d2_6));

        d3 = new Dice(getActivity(),this,
                rootView.findViewById(R.id.d3_1),
                rootView.findViewById(R.id.d3_2),
                rootView.findViewById(R.id.d3_3),
                rootView.findViewById(R.id.d3_4),
                rootView.findViewById(R.id.d3_5),
                rootView.findViewById(R.id.d3_6));

        d4 = new Dice(getActivity(),this,
                rootView.findViewById(R.id.d4_1),
                rootView.findViewById(R.id.d4_2),
                rootView.findViewById(R.id.d4_3),
                rootView.findViewById(R.id.d4_4),
                rootView.findViewById(R.id.d4_5),
                rootView.findViewById(R.id.d4_6));

        d5 = new Dice(getActivity(),this,
                rootView.findViewById(R.id.d5_1),
                rootView.findViewById(R.id.d5_2),
                rootView.findViewById(R.id.d5_3),
                rootView.findViewById(R.id.d5_4),
                rootView.findViewById(R.id.d5_5),
                rootView.findViewById(R.id.d5_6));

        ad = new AvailableMoveAdapter(getActivity());

        ListView lv = (ListView)rootView.findViewById(R.id.lstAvailableMoves);
        lv.setAdapter(ad);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ScoreGroup sg = (ScoreGroup)parent.getItemAtPosition(position);
                PlayMoveConfirmationDialog dg = new PlayMoveConfirmationDialog();
                dg.setScoreGroup(sg);
                dg.setCallbacks((MainActivity) getActivity());
                dg.setGame(mGame);
                dg.setPlayer(mPlayer);
                dg.show(getFragmentManager(),"CONFIRMMOVE");
            }
        });
        return rootView;
    }

    private boolean allDiceSelected(){
        if(d1.getFaceValue()<0) return false;
        if(d2.getFaceValue()<0) return false;
        if(d3.getFaceValue()<0) return false;
        if(d4.getFaceValue()<0) return false;
        if(d5.getFaceValue()<0) return false;
        return true;
    }

    @Override
    public void diceClicked() {
        if(allDiceSelected()){
            final int d1Value = d1.getFaceValue();
            final int d2Value = d2.getFaceValue();
            final int d3Value = d3.getFaceValue();
            final int d4Value = d4.getFaceValue();
            final int d5Value = d5.getFaceValue();
            ArrayList<ScoreGroup> availableMoves = mPlayer.getAvailableMoves();
            for(ScoreGroup sg:availableMoves){
                sg.getScore(d1Value, d2Value, d3Value, d4Value, d5Value, availableMoves);
            }
            Collections.sort(availableMoves, new Comparator<ScoreGroup>() {
                @Override
                public int compare(ScoreGroup lhs, ScoreGroup rhs) {
                    Integer lhsScore = lhs.getPredictedScore();
                    Integer rhsScore = rhs.getPredictedScore();
                    return rhsScore.compareTo(lhsScore);
                }
            });
            ad.swapData(availableMoves);
            Log.d(LOGTAG, "Set the new list of available moves");
        }
    }
}

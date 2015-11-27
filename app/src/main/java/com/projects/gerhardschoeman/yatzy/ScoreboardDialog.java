package com.projects.gerhardschoeman.yatzy;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.projects.gerhardschoeman.yatzy.game.Game;
import com.projects.gerhardschoeman.yatzy.game.Player;

import java.util.ArrayList;

/**
 * Created by Gerhard on 27/11/2015.
 */
public class ScoreboardDialog extends DialogFragment{

    private static final String LOGTAG = ScoreboardDialog.class.getSimpleName();

    private ScoreboardAdapter ad;

    private Game mGame;

    public void setGame(Game game){
        Log.d(LOGTAG,"Setting game");
        mGame = game;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOGTAG, "onCreate");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.d(LOGTAG, "onCreateDialog");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        ad = new ScoreboardAdapter(getActivity(),mGame.getSortedPlayers());

        builder.setCustomTitle(LayoutInflater.from(getActivity()).inflate(R.layout.scoreboard_title,null));

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.scorecard_dialog,null);
        ListView lv = (ListView)v.findViewById(R.id.lstLeaderboard);
        lv.setAdapter(ad);

        builder.setView(v);

        builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder.create();
    }

}

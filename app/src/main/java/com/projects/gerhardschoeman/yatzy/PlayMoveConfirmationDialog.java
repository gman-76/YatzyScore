package com.projects.gerhardschoeman.yatzy;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.projects.gerhardschoeman.yatzy.game.Game;
import com.projects.gerhardschoeman.yatzy.game.Player;
import com.projects.gerhardschoeman.yatzy.game.ScoreGroup;

/**
 * Created by Gerhard on 28/11/2015.
 */
public class PlayMoveConfirmationDialog extends DialogFragment {

    private Game mGame;
    private Player mPlayer;
    private ScoreGroup scoreGroup;

    public interface Callbacks{
        void playMove(Game game, Player player, ScoreGroup sg);
    }

    public void setScoreGroup(ScoreGroup sg){
        scoreGroup = sg;
    }

    public void setGame(Game game){
        mGame = game;
    }

    public void setPlayer(Player player){
        mPlayer = player;
    }

    private Callbacks callbacks;

    public void setCallbacks(Callbacks cb){
        callbacks = cb;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_confirm_move,null);

        TextView msg = (TextView)v.findViewById(R.id.txtConfirmMove);

        msg.setText("Are you sure that you want to play \"" + scoreGroup.getDescription() + "\" for " + Integer.toString(scoreGroup.getPredictedScore()) + " points?");

        builder.setView(v);

        builder.setPositiveButton("Play", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(callbacks!=null) callbacks.playMove(mGame,mPlayer,scoreGroup);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder.create();
    }
}

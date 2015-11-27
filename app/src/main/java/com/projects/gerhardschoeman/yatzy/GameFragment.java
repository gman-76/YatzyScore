package com.projects.gerhardschoeman.yatzy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projects.gerhardschoeman.yatzy.game.Game;

/**
 * Created by Gerhard on 27/11/2015.
 */
public class GameFragment extends Fragment {

    private static final String LOGTAG = GameFragment.class.getSimpleName();

    private Game mGame;

    public void setGame(Game g){
        Log.d(LOGTAG,"Setting game");
        mGame = g;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(LOGTAG,"Creating view");
        View rootView = inflater.inflate(R.layout.game_fragment, container, false);

        return rootView;
    }
}

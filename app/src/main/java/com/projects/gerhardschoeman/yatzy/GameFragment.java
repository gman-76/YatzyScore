package com.projects.gerhardschoeman.yatzy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.projects.gerhardschoeman.yatzy.game.Game;
import com.projects.gerhardschoeman.yatzy.game.Player;

/**
 * Created by Gerhard on 27/11/2015.
 */
public class GameFragment extends Fragment {

    private static final String LOGTAG = GameFragment.class.getSimpleName();

    private Game mGame;

    private NextPlayerAdapter ad;

    public interface Callbacks{
        void nextPlayer(Game game, Player player);
    }

    private Callbacks callbacks;

    public void setGame(Game g){
        Log.d(LOGTAG,"Setting game");
        mGame = g;
    }

    public void setCallbacks(Callbacks cb){
        callbacks = cb;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(LOGTAG, "Creating view");
        View rootView = inflater.inflate(R.layout.game_fragment, container, false);

        ad = new NextPlayerAdapter(getActivity(),mGame.getSortedByMovesRemainingPlayers());

        ListView lv = (ListView)rootView.findViewById(R.id.lstNextPlayer);
        lv.setAdapter(ad);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Player p = (Player) ad.getItem(position);
                if (p.getMovesRemaining() > 0) {
                    if (callbacks != null) callbacks.nextPlayer(mGame, p);
                }
            }
        });

        FloatingActionButton fab = (FloatingActionButton)rootView.findViewById(R.id.btViewScoreboard);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScoreboardDialog dg = new ScoreboardDialog();
                dg.setGame(mGame);
                dg.show(getFragmentManager(), "SCOREBRDDLG");
            }
        });

        Toolbar tb = (Toolbar)rootView.findViewById(R.id.tbCurrentGame);
        String desc = mGame.getDescription();
        if(desc==null || desc.length()<1){
            tb.setTitle(getActivity().getString(R.string.current_game_toolbar_default_title) + Integer.toString(mGame.getID()));
        }else{
            tb.setTitle(desc);
        }
        tb.setNavigationIcon(R.drawable.menu);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToolbarNavMenuCallbacks cb = (MainActivity) getActivity();
                cb.onClicked();
            }
        });

        return rootView;
    }
}

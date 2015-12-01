package com.projects.gerhardschoeman.yatzy;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;

import com.projects.gerhardschoeman.yatzy.data.DataContract;
import com.projects.gerhardschoeman.yatzy.data.DataProjections;
import com.projects.gerhardschoeman.yatzy.game.Game;

/**
 * Created by Gerhard on 26/11/2015.
 */
public class GamesFragment extends Fragment
{
    private static final String LOGTAG = GamesFragment.class.getSimpleName();

    private GameAdapter ad;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(LOGTAG, "onCreate()");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        Log.d(LOGTAG, "onResume()");
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(LOGTAG, "onCreateView()");
        View rootView = inflater.inflate(R.layout.gamelist_fragment,container,false);

        ad = new GameAdapter(getActivity());

        ExpandableListView lv = (ExpandableListView)rootView.findViewById(R.id.lstGames);
        lv.setAdapter(ad);
        lv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                GameAdapter.GameData gd = (GameAdapter.GameData)ad.getChild(groupPosition, childPosition);
                Game game = new Game(getActivity(), gd.id);
                Log.d(LOGTAG, "Create game ready to show scoreboard");
                ScoreboardDialog dg = new ScoreboardDialog();
                dg.setGame(game);
                dg.setCallbacks((MainActivity) getActivity());
                dg.show(getFragmentManager(), "SCOREBOARDDLG");
                return true;
            }
        });

        FloatingActionButton fab = (FloatingActionButton)rootView.findViewById(R.id.btAddGame);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddGameDialog dg = new AddGameDialog();
                dg.setCallbacks((MainActivity) getActivity());
                dg.show(getFragmentManager(), "ADDGAMEDLG");
            }
        });

        Toolbar tb = (Toolbar)rootView.findViewById(R.id.tbGameList);
        tb.setNavigationIcon(R.drawable.menu);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToolbarNavMenuCallbacks cb = (MainActivity)getActivity();
                cb.onClicked();
            }
        });

        return rootView;
    }

    @Override
    public void onPause() {
        Log.d(LOGTAG,"onPause()");
        super.onPause();
    }

    @Override
    public void onStart() {
        Log.d(LOGTAG,"onStart()");
        super.onStart();
    }

    @Override
    public void onStop() {
        Log.d(LOGTAG,"onStop()");
        super.onStop();
    }

    @Override
    public void onAttach(Context context) {
        Log.d(LOGTAG,"onAttach()");
        super.onAttach(context);
    }

    @Override
    public void onDestroy() {
        Log.d(LOGTAG,"onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d(LOGTAG,"onDetach()");
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        Log.d(LOGTAG,"onDestroyView()");
        super.onDestroyView();
    }
}

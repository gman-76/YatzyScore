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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.projects.gerhardschoeman.yatzy.data.DataContract;
import com.projects.gerhardschoeman.yatzy.data.DataProjections;
import com.projects.gerhardschoeman.yatzy.game.Game;

/**
 * Created by Gerhard on 26/11/2015.
 */
public class GamesFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>
{
    private static final String LOGTAG = GamesFragment.class.getSimpleName();

    private final int LOADER_ID = 0;

    private GameAdapter ad;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(LOGTAG,"onCreate()");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        Log.d(LOGTAG,"onResume()");
        super.onResume();
        getLoaderManager().restartLoader(LOADER_ID, null, this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(LOGTAG,"onCreateView()");
        View rootView = inflater.inflate(R.layout.gamelist_fragment,container,false);

        ad = new GameAdapter(getActivity(),null,0);

        ListView lv = (ListView)rootView.findViewById(R.id.lstGames);
        lv.setAdapter(ad);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor c = (Cursor)parent.getItemAtPosition(position);
                int gameID = c.getInt(DataProjections.Game_ALL.COL_ID);
                Game game = new Game(getActivity(),gameID);
                Log.d(LOGTAG, "Create game ready to show scoreboard");
                ScoreboardDialog dg = new ScoreboardDialog();
                dg.setGame(game);
                dg.setCallbacks((MainActivity)getActivity());
                dg.show(getFragmentManager(),"SCOREBOARDDLG");
            }
        });

        FloatingActionButton fab = (FloatingActionButton)rootView.findViewById(R.id.btAddGame);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddGameDialog dg = new AddGameDialog();
                dg.setCallbacks((MainActivity)getActivity());
                dg.show(getFragmentManager(), "ADDGAMEDLG");
            }
        });

        return rootView;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String so = DataContract.GameEntry.COL_STARTED + " DESC";
        return new CursorLoader(getActivity(),
                DataContract.GameEntry.CONTENT_URI,
                DataProjections.Game_ALL.COLUMNS,
                null,null,so);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        ad.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        ad.swapCursor(null);
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

package com.projects.gerhardschoeman.yatzy;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.projects.gerhardschoeman.yatzy.data.DataContract;
import com.projects.gerhardschoeman.yatzy.game.Game;
import com.projects.gerhardschoeman.yatzy.game.Player;
import com.projects.gerhardschoeman.yatzy.game.ScoreGroup;

public class MainActivity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,
        AddGameDialog.AddGameDlgCallbacks,
        GameFragment.Callbacks,
        PlayMoveConfirmationDialog.Callbacks
{
    private static final String LOGTAG = MainActivity.class.getSimpleName();

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = null;
        switch(position){
            case 0: fragment = new GamesFragment(); break;
            case 1: fragment = new PlayersFragment(); break;
            case 2: fragment = new AboutFragment(); break;
        }
        if(fragment==null) return;
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();

        DrawerLayout dl = (DrawerLayout) findViewById(R.id.drawer_layout);
        dl.closeDrawer(findViewById(R.id.navigation_drawer));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void newGameReady(Game game) {
        GameFragment currentGameFragment = new GameFragment();
        currentGameFragment.setGame(game);
        currentGameFragment.setCallbacks(this);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, currentGameFragment)
                .commit();
    }

    @Override
    public void nextPlayer(Game game, Player player) {
        CurrentPlayerFragment f = new CurrentPlayerFragment();
        f.setGame(game);
        f.setPlayer(player);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, f)
                .commit();
    }

    @Override
    public void playMove(Game game, Player player, ScoreGroup sg) {
        //make sure we play the scoregroup actually belonging to the player
        //not a temp group created for pair and two pair scenarios
        player.setMoveScore(sg.getID(),sg.getPredictedScore());
        sg.play(this,game.getID(),player.getID());
        newGameReady(game);
    }
}

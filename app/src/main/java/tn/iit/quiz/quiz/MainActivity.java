package tn.iit.quiz.quiz;

import android.app.FragmentManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import tn.iit.quiz.quiz.fragment.MainFragment;
import tn.iit.quiz.quiz.fragment.PlayFragment;

public class MainActivity extends AppCompatActivity  {
    MediaPlayer backround_sound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(tn.iit.quiz.quiz.R.layout.activity_main);
        backround_sound = MediaPlayer.create(this, R.raw.mistery);
        backround_sound.start();
        Toolbar toolbar = (Toolbar) findViewById(tn.iit.quiz.quiz.R.id.toolbar);
        setSupportActionBar(toolbar);
        //FrameLayout fm=(FrameLayout)findViewById(tn.iit.quiz.quiz.R.id.fragment);


            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment, new MainFragment())
                    .commit();

    }
/*
    public void onBackPressed()
    {
        if ( getFragmentManager().getBackStackEntryCount() > 0)
        {
            getFragmentManager().popBackStack();
            return;
        }
        super.onBackPressed();
    }
*/

}


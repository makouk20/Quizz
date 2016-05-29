package tn.iit.quiz.quiz;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import tn.iit.quiz.quiz.fragment.MainFragment;

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



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(tn.iit.quiz.quiz.R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == tn.iit.quiz.quiz.R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

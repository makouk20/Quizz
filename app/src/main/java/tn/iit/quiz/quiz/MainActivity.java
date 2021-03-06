package tn.iit.quiz.quiz;

import android.media.MediaPlayer;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import tn.iit.quiz.quiz.fragment.MainFragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MediaPlayer mp;
        mp = MediaPlayer.create(this,R.raw.mistery);
        mp.start();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        toolbar.setLogo(R.mipmap.ic_logo_quiz);
        setSupportActionBar(toolbar);

         getFragmentManager().beginTransaction()
                .add(R.id.fragment, new MainFragment())
                .commit();


    }
}


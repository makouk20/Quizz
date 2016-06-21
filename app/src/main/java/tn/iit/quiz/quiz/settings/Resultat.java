package tn.iit.quiz.quiz.settings;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import tn.iit.quiz.quiz.R;

public class Resultat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultat);
        MediaPlayer mp;
        mp = MediaPlayer.create(this,R.raw.cena);
        mp.start();
    }
}

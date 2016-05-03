package tn.iit.quiz.quiz;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_random;
    private Button btn_history;
    private Button btn_animals;
    private Button btn_food;
    private Button btn_science;
    private Button btn_technologie;
    MediaPlayer backround_sound;
    MediaPlayer click_btn_sound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

            backround_sound = MediaPlayer.create(this,R.raw.mistery);
            backround_sound.start();





        btn_random = (Button) findViewById(R.id.btn_cat1);
        btn_random.setOnClickListener(this);

        btn_history = (Button) findViewById(R.id.btn_cat2);
        btn_history.setOnClickListener(this);

        btn_animals = (Button) findViewById(R.id.btn_cat3);
        btn_animals.setOnClickListener(this);

        btn_food = (Button) findViewById(R.id.btn_cat4);
        btn_food.setOnClickListener(this);

        btn_science = (Button) findViewById(R.id. btn_cat5);
        btn_science.setOnClickListener(this);

        btn_technologie = (Button) findViewById(R.id.btn_cat6);
        btn_technologie.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_cat1:
                onclick_btn_random();
                break;
            case R.id.btn_cat2:
                onclick_btn_history();
                break;
            case R.id.btn_cat3:
                onclick_btn_animals();
                break;
            case R.id.btn_cat4:
                onclick_btn_food();
                break;
            case R.id.btn_cat5:
                onclick_btn_science();
                break;
            case R.id.btn_cat6:
                onclick_btn_technologie();
                break;

        }

    }
    public void onclick_btn_sound(){

        click_btn_sound = MediaPlayer.create(this,R.raw.fail);
        click_btn_sound.start();
    }
    public void choisir_type(){
    QuizType qt = new QuizType();
        qt.show(getSupportFragmentManager(),"qt");
    }
    private void onclick_btn_technologie() {

        choisir_type();
        onclick_btn_sound();
    }

    private void onclick_btn_science() {
        choisir_type();
        onclick_btn_sound();
    }

    private void onclick_btn_food() {
        choisir_type();
        onclick_btn_sound();
    }

    private void onclick_btn_animals() {
        choisir_type();
        onclick_btn_sound();
    }

    private void onclick_btn_history() {
        choisir_type();
        onclick_btn_sound();
    }

    private void onclick_btn_random() {
        choisir_type();
        onclick_btn_sound();
    }

}

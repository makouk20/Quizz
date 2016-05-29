package tn.iit.quiz.quiz.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import tn.iit.quiz.quiz.R;

public class MenuFragment extends Fragment implements View.OnClickListener {

    private Button btn_random;
    private Button btn_history;
    private Button btn_animals;
    private Button btn_food;
    private Button btn_science;
    private Button btn_technologie;
    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(tn.iit.quiz.quiz.R.layout.fragment_menu, container, false);
        btn_random = (Button) myView.findViewById(R.id.btn_cat1);
        btn_random.setOnClickListener(this);

        btn_history = (Button) myView.findViewById(R.id.btn_cat2);
        btn_history.setOnClickListener(this);

        btn_animals = (Button) myView.findViewById(R.id.btn_cat3);
        btn_animals.setOnClickListener(this);

        btn_food = (Button) myView.findViewById(R.id.btn_cat4);
        btn_food.setOnClickListener(this);

        btn_science = (Button) myView.findViewById(R.id.btn_cat5);
        btn_science.setOnClickListener(this);

        btn_technologie = (Button) myView.findViewById(R.id.btn_cat6);
        btn_technologie.setOnClickListener(this);


        return myView;
    }


    public MenuFragment() {
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


    }
    public void choisir_type(){

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

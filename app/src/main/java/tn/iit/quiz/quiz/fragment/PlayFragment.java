package tn.iit.quiz.quiz.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import tn.iit.quiz.quiz.R;

public class PlayFragment extends Fragment implements View.OnClickListener {
    Button btn_play, btn_apropos,btn_info,btn_setting;
    View myView;



    public PlayFragment() {
        super();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_play, container, false);
        btn_play = (Button) myView.findViewById(R.id.play);
        btn_play.setOnClickListener(this);

        btn_apropos = (Button) myView.findViewById(R.id.apropos);
        btn_apropos.setOnClickListener(this);

        btn_info = (Button) myView.findViewById(R.id.info);
        btn_info.setOnClickListener(this);

        btn_setting = (Button) myView.findViewById(R.id.setting);
        btn_setting.setOnClickListener(this);

        return myView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play:
                onclick_btn_play();
                break;
            case R.id.apropos:
                onclick_btn_apropos();
                break;
            case R.id.info:
                onclick_btn_info();
                break;
            case R.id.setting:
                onclick_btn_setting();
                break;


        }




    }



    private void onclick_btn_play() {
        this.getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment, new MenuFragment())
                .commit();


    }

    private void onclick_btn_apropos() {
    }

    private void onclick_btn_info() {
    }

    private void onclick_btn_setting() {
    }


}




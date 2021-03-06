package tn.iit.quiz.quiz;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;
import android.app.DialogFragment;

import tn.iit.quiz.quiz.fragment.ReconnaissanceFragment;
import tn.iit.quiz.quiz.fragment.ReconnaissanceSqliteFragment;

/**
 * Created by CTC on 01/05/2016.
 */
public class QuizType extends DialogFragment {
    final CharSequence[] items = {"Question/Reponse","Reconnaissance d'images"};
    String selected;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setIcon(R.drawable.icon_dialog);
        dialog.setTitle("Choisir le type du Quiz").setSingleChoiceItems(items,-1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        selected = (String) items[which];
                        break;
                    case 1:
                        selected = (String) items[which];
                        break;
                }
            }
        }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(),"vous avez choisi le Quiz type : "+selected,Toast.LENGTH_SHORT).show();
                 getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.fragment, new ReconnaissanceFragment())
                       // .replace(R.id.fragment, new ReconnaissanceSqliteFragment())
                        .addToBackStack(null).commit();
            }
        });
        return dialog.create();
    }
}

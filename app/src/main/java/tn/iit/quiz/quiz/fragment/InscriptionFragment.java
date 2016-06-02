package tn.iit.quiz.quiz.fragment;
/**
 * Created by MOHAMED ALI DRISS on 28/05/2016.
 */
import android.app.Fragment;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import tn.iit.quiz.quiz.R;
import tn.iit.quiz.quiz.database.QuizContentProvider;
import tn.iit.quiz.quiz.database.tables.UtilisateurTable;






public class InscriptionFragment extends Fragment implements View.OnClickListener {


    View myView;
    private EditText nom;
    private EditText prenom;
    private EditText login;
    private EditText email;
    private EditText password;
    private EditText cpassword;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_inscription, container, false);
        nom=(EditText)myView.findViewById(R.id.nom);
        prenom=(EditText)myView.findViewById(R.id.prenom);
        login=(EditText)myView.findViewById(R.id.login);
        email=(EditText)myView.findViewById(R.id.email);
        password=(EditText)myView.findViewById(R.id.password);
        cpassword=(EditText)myView.findViewById(R.id.cpassword);

        Button inscription = (Button) myView.findViewById(R.id.inscription);
        inscription.setOnClickListener(this);

        return myView;
    }

    public InscriptionFragment() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.inscription:
                onclickInscription();
                break;
        }

    }
    public void onclickInscription() {
        if (!password.getText().toString().equals(cpassword.getText().toString())) {
            Toast.makeText(this.getActivity().getApplicationContext(), "The password confirmation is not identical", Toast.LENGTH_LONG).show();
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put(UtilisateurTable.lOGIN, login.getText().toString());
            contentValues.put(UtilisateurTable.NOM, nom.getText().toString());
            contentValues.put(UtilisateurTable.PRENOM, prenom.getText().toString());
            contentValues.put(UtilisateurTable.EMAIL, email.getText().toString());
            contentValues.put(UtilisateurTable.PASSWORD, password.getText().toString());
            Uri uri = this.getActivity().getContentResolver().insert(QuizContentProvider.UTILISATEUR_CONTENT_URI, contentValues);
            Toast.makeText(this.getActivity().getApplicationContext(), getString(R.string.registercompleted), Toast.LENGTH_LONG).show();


          /*  this.getActivity().getFragmentManager().beginTransaction()
                    .hide(this)
                    .show(new PlayFragment())
                    .commit();*/









        }


    }
    }



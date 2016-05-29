package tn.iit.quiz.quiz.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import tn.iit.quiz.quiz.R;
import tn.iit.quiz.quiz.database.ConvertisseurBDD;
import tn.iit.quiz.quiz.database.QuizContentProvider;
import tn.iit.quiz.quiz.database.tables.UtilisateurTable;
import tn.iit.quiz.quiz.entities.Utilisateur;

/**
 * A login screen that offers login via email/password.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {


    View myView;
    int i;
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(tn.iit.quiz.quiz.R.layout.fragment_login, container, false);


        // Set up the login form.
        mEmailView = (AutoCompleteTextView) myView.findViewById(R.id.email);


        mPasswordView = (EditText) myView.findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) myView.findViewById(R.id.email_sign_in_button);
        Button inscription = (Button) myView.findViewById(R.id.Inscription);
        mLoginFormView = myView.findViewById(R.id.login_form);
        mProgressView = myView.findViewById(R.id.login_progress);

        inscription.setOnClickListener(this);
        mEmailSignInButton.setOnClickListener(this);
        return myView;
    }



    public void mInscription() {
      FragmentTransaction ft = this.getActivity().getSupportFragmentManager().beginTransaction();
        ft.hide(this);
        ft.commit();
        this.getActivity().getFragmentManager().beginTransaction()
                .replace(R.id.fragment
                        ,new InscriptionFragment())
                .commit();



    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Inscription:
                mInscription();
                break;
            case R.id.email_sign_in_button:
                attemptLogin();
                break;
        }


    }


    public LoginFragment() {
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {


        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {


            String[] whereArgs = {email, password};
            Cursor cursor = this.getActivity().getContentResolver().query(QuizContentProvider.UTILISATEUR_CONTENT_URI,
                    new String[]{UtilisateurTable._ID, UtilisateurTable.lOGIN, UtilisateurTable.NOM, UtilisateurTable.PRENOM, UtilisateurTable.EMAIL, UtilisateurTable.PASSWORD},
                    UtilisateurTable.EMAIL + " = ? AND " + UtilisateurTable.PASSWORD + " = ? ", whereArgs, null);
            ConvertisseurBDD conv = new ConvertisseurBDD();
            Utilisateur user = conv.cursorToUtilisateur(cursor);

            if (user != null) {

                Toast.makeText(this.getActivity().getApplicationContext(), user.toString(), Toast.LENGTH_LONG).show();
                this.getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment, new PlayFragment())
                        .commit();
            } else {
                Toast.makeText(this.getActivity().getApplicationContext(), "you are not registered ", Toast.LENGTH_LONG).show();
            }

        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


}


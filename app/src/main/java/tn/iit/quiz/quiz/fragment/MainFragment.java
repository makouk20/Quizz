package tn.iit.quiz.quiz.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import tn.iit.quiz.quiz.MainActivity;
import tn.iit.quiz.quiz.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment implements View.OnClickListener {
    //       -------Login   Facebook ---------------  //
    View myView;
    //  private TextView mtextDetails;
    //  private ImageView img;
    private CallbackManager mcallbackManager;
    //  private ProfileTracker mprofileTracker;
    //  private AccessTokenTracker mtokenTracker;
    private FacebookCallback<LoginResult> mcallback = new FacebookCallback<LoginResult>() {
        /*  -----------Login   Google plus ---------------  */


        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();
            displayWelcomeMessage(profile);




            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment, new PlayFragment())
                    .commit();




        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {

        }
    };
    private int fragment;

    public MainFragment() {


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //       -------Login   Facebook :)---------------  //
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        mcallbackManager = CallbackManager.Factory.create();

        AccessTokenTracker tracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken old, AccessToken newToken) {

            }
        };
        ProfileTracker profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldprofile, Profile newprofile) {
                displayWelcomeMessage(newprofile);
            }
        };
        tracker.startTracking();
        profileTracker.startTracking();
        //       -------Login   Google plus ---------------  //


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(tn.iit.quiz.quiz.R.layout.fragment_main, container, false);

        Button login = (Button) myView.findViewById(R.id.login);

        login.setOnClickListener(this);

        return myView;


    }

    private void displayWelcomeMessage(Profile profile) {
        // StringBuffer stringBuffer = new StringBuffer();
        if (profile != null) {
            //   mtextDetails.setText("Welcome " + profile.getName() );
        }
        //return stringBuffer.toString();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //       -------Login   Facebook ---------------  //
        LoginButton loginButton = (LoginButton) view.findViewById(tn.iit.quiz.quiz.R.id.login_button);
        loginButton.setReadPermissions("user_friends");
        loginButton.setFragment(this);
        loginButton.registerCallback(mcallbackManager, mcallback);
        //   mtextDetails = (TextView) view.findViewById(tn.iit.quiz.quiz.R.id.nameprofile);
        //       -------Login   Google plus ---------------  //

    }

    @Override
    public void onResume() {
        super.onResume();
        Profile profile = Profile.getCurrentProfile();
        displayWelcomeMessage(profile);
        //ktata


    }

    @Override
    public void onStop() {
        super.onStop();


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mcallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void loginClick() {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment
                        , new LoginFragment())
                .commit();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                loginClick();
                break;

        }

    }
}

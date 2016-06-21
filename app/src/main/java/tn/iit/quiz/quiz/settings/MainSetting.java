package tn.iit.quiz.quiz.settings;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

import tn.iit.quiz.quiz.R;

public class MainSetting extends AppCompatActivity implements View.OnClickListener {
    private Locale myLocale;
    private Button btn_en, btn_it, btn_fr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_setting);
        this.btn_en = (Button) findViewById(R.id.anglais);
        this.btn_it = (Button) findViewById(R.id.italien);
        this.btn_fr = (Button) findViewById(R.id.fr);
        this.btn_en.setOnClickListener(this);
        this.btn_it.setOnClickListener(this);
        this.btn_fr.setOnClickListener(this);

        loadLocale();
    }

    public void changeLang(String lang) {
        if (lang.equalsIgnoreCase(""))
            return;
        myLocale = new Locale(lang);
        saveLocale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

    }
    public void saveLocale(String lang) {
        String langPref = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(langPref, lang);
        editor.commit();
    }

    public void loadLocale() {
        String langPref = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        String language = prefs.getString(langPref, "");
        changeLang(language);
    }

    public void onClick(View v) {
        String lang = "en";
        switch (v.getId()) {
            case R.id.anglais:
                lang = "en";
                break;
            case R.id.italien:
                lang = "it";
                break;
            case R.id.fr:
                lang = "fr";
                break;
            default:
                break;
        }
        changeLang(lang);
    }
}

package tn.iit.quiz.quiz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Environment;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import tn.iit.quiz.quiz.entities.Reconnaissance;

public class ReconnaissanceActivity extends AppCompatActivity {
    private ImageView imgView;
    private TextView question;
    private ListView lv;
    int pos;
    int score=0;
    View v;
    List<Reconnaissance> reclist=new ArrayList<Reconnaissance>();
    int index=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reconnaissance);
        question=(TextView)findViewById(R.id.question);
        imgView=(ImageView)findViewById(R.id.img);
        lv=(ListView)findViewById(R.id.listView1);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v("mmmm", reclist.get(index).getQuestion());
                pos=position;
                v=view;


                Animation anim = AnimationUtils.loadAnimation(
                        getApplication(), android.R.anim.slide_out_right
                );
                anim.setDuration(3000);
                view.startAnimation(anim);
                if (reclist.get(index).getReponse() == pos)
                {
                    score ++;
                    v.setBackgroundColor(Color.GREEN);
                }
                else
                    v.setBackgroundColor(Color.RED);
                Handler handler =new Handler();
                new Handler().postDelayed(new Runnable() {

                    public void run() {

                    }
                }, anim.getDuration());

                index++;
                if(index==reclist.size())
                {
                   /* Intent intent =new Intent(getApplicationContext(),ResultatActivity.class);
                    startActivity(intent);*/
                }
                else
                {
                    Bitmap bMap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/" + reclist.get(index).getImg());
                    imgView.setImageBitmap(bMap);
                    question.setText(reclist.get(index).getQuestion());
                    lv.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.tv, reclist.get(index).getList_proposition()));
                }
            }
        });

        new JSONAsyncTask().execute();
    }
    class JSONAsyncTask extends AsyncTask<String, String, JsonObject> {
        ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ReconnaissanceActivity.this);
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        @Override
        protected JsonObject doInBackground(String... params) {
            File f = new File (Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/rec.json");
            JsonReader reader = null;
            try {
                reader = new JsonReader(new InputStreamReader(
                        new FileInputStream(f)));
            } catch (FileNotFoundException e) {
                Log.v("rec", "file n'existe pas");
            }
            JsonParser jsonParser = new JsonParser();
            JsonObject userArray = jsonParser.parse(reader).getAsJsonObject();
            return userArray;
        }
        protected void onPostExecute(JsonObject userArray) {
            pDialog.dismiss();
            try {
                JsonArray objets = userArray.getAsJsonArray("reconnaissance");
                List<Reconnaissance> rec_list = new ArrayList<Reconnaissance>();
                Gson myGson = new Gson();
                for (JsonElement aUser : objets) {
                    //	System.out.println(aUser);
                    Reconnaissance arec = myGson.fromJson(aUser, Reconnaissance.class);
                    rec_list.add(arec);
                }
                setQuestion(rec_list);
                Bitmap bMap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/" + rec_list.get(0).getImg());
                imgView.setImageBitmap(bMap);
                        question.setText(reclist.get(0).getQuestion());
                lv.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.tv, rec_list.get(0).getList_proposition()));

            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        private void setQuestion(List l)
        {
            reclist=l;
        }
    }
}

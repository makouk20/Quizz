package tn.iit.quiz.quiz.fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Fragment;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tn.iit.quiz.quiz.entities.Reconnaissance;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import tn.iit.quiz.quiz.R;
import tn.iit.quiz.quiz.settings.Resultat;


public class ReconnaissanceFragment extends Fragment {

    public  ImageView imgView;
    private TextView question,t;
    private ListView lv;
    int pos;
    View v;
    CountDownTimer timer =  new CountDownTimer(15000, 1000) {

        public void onTick(long millisUntilFinished) {
            t.setText("" + millisUntilFinished / 1000);
        }

        public void onFinish() {
            index++;
            if (index == reclist.size()) {
              /*  getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.fragment, new ReconnaissanceFragment())
                        .addToBackStack(null).commit();*/
                Intent intent = new Intent(getActivity().getApplication(), Resultat.class);
                startActivity(intent);

            } else {
                // Bitmap bMap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/" + reclist.get(index).getImg());
                //imgView.setImageBitmap(bMap);
                Picasso.with(getActivity().getApplicationContext())
                        .load(reclist.get(index).getImg())
                        .into(imgView);
                question.setText(reclist.get(index).getQuestion());
                lv.setAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.tv, reclist.get(index).getList_proposition()));
                timer.start();
            }
        }
    };
    List<Reconnaissance> reclist=new ArrayList<Reconnaissance>();
    int index=0;
    int i=0;

    private WeakReference<MyAsyncTask> asyncTaskWeakRef;
    public ReconnaissanceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
        startNewAsyncTask();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reconnaissance, container, false);
        question=(TextView)view.findViewById(R.id.question);
        imgView=(ImageView)view.findViewById(R.id.img);
        t =(TextView) view.findViewById(R.id.timer);
        lv=(ListView)view.findViewById(R.id.listView1);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v("mmmm", reclist.get(index).getQuestion());
                pos = position;
                v = view;
                pos = position;
                v = view;


                Animation anim = AnimationUtils.loadAnimation(
                        getActivity().getApplication(), android.R.anim.slide_out_right
                );
                anim.setDuration(3000);
                view.startAnimation(anim);
                if (reclist.get(index).getReponse() == pos)
                    v.setBackgroundColor(Color.GREEN);

                else {
                    v.setBackgroundColor(Color.RED);
                    ImageView im1 = (ImageView) getActivity().findViewById(R.id.c3);
                    ImageView im2 = (ImageView) getActivity().findViewById(R.id.c2);
                    ImageView im3 = (ImageView) getActivity().findViewById(R.id.c1);

                    if (i == 0)
                        im1.setVisibility(view.INVISIBLE);
                    if (i == 1)
                        im2.setVisibility(view.INVISIBLE);
                    if (i == 2)
                        im3.setVisibility(view.INVISIBLE);
                    i++;
                }
                Handler handler = new Handler();


                new Handler().postDelayed(new Runnable() {

                    public void run() {

                    }
                }, anim.getDuration());

                index++;
                if (index == reclist.size()) {
                  /*  getActivity().getFragmentManager().beginTransaction()
                            .replace(R.id.fragment, new ReconnaissanceFragment())
                            .addToBackStack(null).commit();*/
                    Intent intent = new Intent(getActivity().getApplication(), Resultat.class);
                    startActivity(intent);
                } else {
                    // Bitmap bMap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/" + reclist.get(index).getImg());
                    //imgView.setImageBitmap(bMap);
                    Picasso.with(getActivity().getApplicationContext())
                            .load(reclist.get(index).getImg())
                            .into(imgView);
                    question.setText(reclist.get(index).getQuestion());
                    lv.setAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.tv, reclist.get(index).getList_proposition()));

                }
            }
        });
        return view;
    }
    private class MyAsyncTask extends AsyncTask<String, String, JSONObject> {

        private WeakReference<ReconnaissanceFragment> fragmentWeakRef;
        private ProgressDialog pDialog;
        private MyAsyncTask (ReconnaissanceFragment fragment ) {
            this.fragmentWeakRef = new WeakReference<ReconnaissanceFragment>(fragment);
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        @Override
        protected JSONObject doInBackground(String... params) {
            JSONObject userArray = null;
            try {
                userArray = new JSONObject(loadJSONFromAsset());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return userArray;
        }
        protected void onPostExecute(JSONObject userArray) {
            pDialog.dismiss();
            if(this.fragmentWeakRef.get()!=null)
            {
                try
                {
                    JSONArray objets = userArray.optJSONArray("reconnaissance");
                    List<Reconnaissance> rec_list = new ArrayList<Reconnaissance>();
                    Gson myGson = new Gson();
                    for(int i=0; i < objets.length(); i++){
                        Reconnaissance arec = myGson.fromJson(String.valueOf(objets.getJSONObject(i)), Reconnaissance.class);
                        rec_list.add(arec);
                    }
                    setQuestion(rec_list);
                    //Bitmap bMap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/" + rec_list.get(0).getImg());
                    // imgView.setImageBitmap(bMap);
                    Picasso.with(getActivity().getApplicationContext())
                            .load( rec_list.get(0).getImg())
                            .into(imgView);
                    question.setText(reclist.get(0).getQuestion());
                    lv.setAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.tv, rec_list.get(0).getList_proposition()));
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void startNewAsyncTask() {
        MyAsyncTask asyncTask = new MyAsyncTask(this);
        this.asyncTaskWeakRef = new WeakReference<MyAsyncTask >(asyncTask );
        asyncTask.execute();
        timer.start();
    }
    private boolean isAsyncTaskPendingOrRunning() {
        return this.asyncTaskWeakRef != null &&
                this.asyncTaskWeakRef.get() != null &&
                !this.asyncTaskWeakRef.get().getStatus().equals(AsyncTask.Status.FINISHED);
    }
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("rec.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
    private void setQuestion(List l)
    {
        reclist=l;
    }


}

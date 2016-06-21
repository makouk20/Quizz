package tn.iit.quiz.quiz.fragment;

import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.app.Fragment;
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
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import tn.iit.quiz.quiz.R;
import tn.iit.quiz.quiz.database.ConvertisseurBDD;
import tn.iit.quiz.quiz.database.QuizContentProvider;
import tn.iit.quiz.quiz.database.tables.ChoixTable;
import tn.iit.quiz.quiz.database.tables.QestionTable;
import tn.iit.quiz.quiz.database.tables.UtilisateurTable;
import tn.iit.quiz.quiz.entities.Reconnaissance;
import tn.iit.quiz.quiz.entities.Utilisateur;


public class ReconnaissanceSqliteFragment extends Fragment {

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
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.fragment, new ReconnaissanceSqliteFragment())
                        .addToBackStack(null).commit();
            } else {

                Picasso.with(getActivity().getApplicationContext())
                        .load(reclist.get(index).getImg())
                        .into(imgView);
                question.setText(reclist.get(index).getQuestion());
                lv.setAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.tv, reclist.get(index).getList_proposition()));
                timer.start();
            }
        }
    };
    Cursor cursorQestion = this.getActivity().getContentResolver().query(QuizContentProvider.QESTION_CONTENT_URI, QestionTable.PROJECTION_ALL, null, null, null);
    Cursor cursorChoix =this.getActivity().getContentResolver().query(QuizContentProvider.CHOIX_CONTENT_URI, ChoixTable.PROJECTION_ALL, null, null, null);
    ConvertisseurBDD convertisseur = new ConvertisseurBDD();
    List<Reconnaissance> reclist= convertisseur.cursorToLisReconnaissance(cursorQestion,cursorChoix);
    int index=0;
    int i=0;

    private WeakReference<MyAsyncTask> asyncTaskWeakRef;
    public ReconnaissanceSqliteFragment() {
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
                    getActivity().getFragmentManager().beginTransaction()
                            .replace(R.id.fragment, new ReconnaissanceSqliteFragment())
                            .addToBackStack(null).commit();
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
        v =view;
        return view;
    }
    private  class MyAsyncTask extends AsyncTask<String,String,Boolean   > {


        private WeakReference<ReconnaissanceSqliteFragment> fragmentWeakRef;
        private ProgressDialog pDialog;

        private MyAsyncTask (ReconnaissanceSqliteFragment fragment) {
            this.fragmentWeakRef = new WeakReference<ReconnaissanceSqliteFragment>(fragment);


        }




        @Override
        protected Boolean doInBackground(String... params) {

            try
            {

                return  true;

            }
            catch(Exception e) {
                e.printStackTrace();
                return false;
            }

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

        protected void onPostExecute(Boolean res ) {
            pDialog.dismiss();
            if(res=true)
            {
                if(this.fragmentWeakRef.get()!=null)
                {
                    if (reclist != null) {
                        Toast.makeText(getActivity().getApplicationContext(), reclist.toString(), Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "base vide ", Toast.LENGTH_LONG).show();
                    }
                }
            }
            else
                Toast.makeText(getActivity().getApplicationContext(), "excption dans la base ", Toast.LENGTH_LONG).show();


        }
    }
    private void startNewAsyncTask() {
        Cursor cursorQestion = this.getActivity().getContentResolver().query(QuizContentProvider.QESTION_CONTENT_URI, QestionTable.PROJECTION_ALL, null, null, null);
        Cursor cursorChoix =this.getActivity().getContentResolver().query(QuizContentProvider.CHOIX_CONTENT_URI, ChoixTable.PROJECTION_ALL, null, null, null);
        ConvertisseurBDD convertisseur = new ConvertisseurBDD();
        reclist  = convertisseur.cursorToLisReconnaissance(cursorQestion,cursorChoix);
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

    private void setQuestion(List l)
    {
        reclist=l;
    }


}

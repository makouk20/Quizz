
package tn.iit.quiz.quiz.database;

import android.database.Cursor;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import tn.iit.quiz.quiz.database.tables.ChoixTable;
import tn.iit.quiz.quiz.database.tables.QestionTable;
import tn.iit.quiz.quiz.entities.Reconnaissance;
import tn.iit.quiz.quiz.entities.Utilisateur;


public class ConvertisseurBDD {


    public Utilisateur cursorToUtilisateur(Cursor c) {

        if (c.getCount() == 0) {
            return null;
        }
        c.moveToFirst();
        Utilisateur user = new Utilisateur(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5));
        c.close();
        return user;
    }


    public List<Reconnaissance> cursorToLisReconnaissance(Cursor cursorQestion, Cursor cursorChoix) {
        List<Reconnaissance> reclist=new ArrayList<Reconnaissance>();
        if (cursorQestion.getCount() == 0) {
            return null;
        }
        int count=cursorQestion.getCount();
        cursorQestion.moveToFirst();
        for (int i=0;i<count;i++)
        { Reconnaissance rec =new Reconnaissance(cursorQestion.getInt(cursorQestion.getColumnIndex(QestionTable._ID)));
            rec.setId_categ(1);
            rec.setQuestion(cursorQestion.getString(cursorQestion.getColumnIndex(QestionTable.QESTION)));
            rec.setImg(cursorQestion.getString(cursorQestion.getColumnIndex(QestionTable.URLIMAGE)));
          int idQes =cursorQestion.getInt(cursorQestion.getColumnIndex(QestionTable._ID));
            List<String> list=new ArrayList<String>();
            int countch=cursorChoix.getCount();
            cursorChoix.moveToFirst();
            for (int j=0;i<countch;i++) {
              int idQesCh= cursorChoix.getInt(cursorChoix.getColumnIndex(ChoixTable.IDQESTION));
                if(idQesCh==idQes){
                    list.add(cursorChoix.getString(cursorChoix.getColumnIndex(ChoixTable.TEXTCHOIX)));
                    if (cursorChoix.getString(cursorChoix.getColumnIndex(ChoixTable.TEXTCHOIX)).equals("1"))
                    {
                        rec.setReponse(list.size());
                    }
                }

                cursorChoix.moveToNext();
            }

            rec.setList_proposition(list);
            list=null;
            reclist.add(rec);
            cursorQestion.moveToNext();
        }
        cursorQestion.close();
        cursorChoix.close();
        return reclist;

    }
}

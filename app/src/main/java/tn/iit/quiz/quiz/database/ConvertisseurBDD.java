
package tn.iit.quiz.quiz.database;

import android.database.Cursor;

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


}

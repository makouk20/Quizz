package tn.iit.quiz.quiz.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import tn.iit.quiz.quiz.database.tables.ChoixTable;
import tn.iit.quiz.quiz.database.tables.QestionTable;
import tn.iit.quiz.quiz.database.tables.TestTable;
import tn.iit.quiz.quiz.database.tables.UtilisateurTable;
import tn.iit.quiz.quiz.database.tables.UtilisateurTestTable;


public class QuizDatabaseHelper extends SQLiteOpenHelper {

    // database name
    private static final String DATABASE_NAME = "quizy.db";
    // data base version
    private static final int DATABASE_VERSION = 1;

    /**
     * Basic constructor
     *
     * @param context
     */
    public QuizDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Constructor
     *
     * @param context
     * @param name
     * @param factory
     * @param version
     */

    public QuizDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        UtilisateurTable.onCreate(db);
        TestTable.onCreate(db);
        QestionTable.onCreate(db);
        ChoixTable.onCreate(db);
        UtilisateurTestTable.onCreate(db);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        UtilisateurTable.onUpgrade(db, oldVersion, newVersion);
        TestTable.onUpgrade(db, oldVersion, newVersion);
        QestionTable.onUpgrade(db, oldVersion, newVersion);
        ChoixTable.onUpgrade(db, oldVersion, newVersion);
        UtilisateurTestTable.onUpgrade(db, oldVersion, newVersion);

    }

}
package tn.iit.quiz.quiz.database.tables;

/**
 * Created by MOHAMED ALI DRISS on 15/05/2016.
 */
//import android.content.ContentResolver;

import android.content.ContentResolver;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class UtilisateurTable implements BaseColumns {

    public static final String TABLE_UTILISATEUR = "utilisateur";
    public static final String lOGIN = "login";
    public static final String NOM = "nom";
    public static final String PRENOM = "prenom";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";


    private static final String CREATE_UTILISATEUR_TABLE = "CREATE TABLE "
            + TABLE_UTILISATEUR + " (" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + lOGIN
            + " TEXT NOT NULL, " + NOM
            + " TEXT NOT NULL, " + PRENOM
            + " TEXT NOT NULL, " + EMAIL
            + " TEXT NOT NULL, " + PASSWORD
            + " TEXT NOT NULL ); ";

    public static final String CONTENT_PATH = "utilisateur";
    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd.quiz.utilisateur";
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd.quiz.utilisateur";

    public static final String[] PROJECTION_ALL = {_ID, lOGIN, NOM, PRENOM, EMAIL, PASSWORD};

    /**
     * create records table
     *
     * @param database
     */
    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_UTILISATEUR_TABLE);
    }


    /**
     * upgrade the records table
     *
     * @param database
     * @param oldVersion
     * @param newVersion
     */
    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {

        database.execSQL("DROP TABLE IF EXISTS " + TABLE_UTILISATEUR);
        onCreate(database);
    }
}

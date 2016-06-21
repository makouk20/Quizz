package tn.iit.quiz.quiz.database.tables;

import android.content.ContentResolver;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by MOHAMED ALI DRISS on 17/05/2016.
 */
public class UtilisateurTestTable {
    public static final String TABLE_UTILISATEUR_TEST = "utilisateurtest";
    public static final String IDTEST = "idtest";
    public static final String IDUTIL = "idutil";
    public static final String SCORE = "score";


    private static final String CREATE_UTILISATEUR_TEST_TABLE = "CREATE TABLE "
            + TABLE_UTILISATEUR_TEST + " (" + IDTEST
            + " INTEGER NOT NULL, " + IDUTIL
            + " INTEGER NOT NULL, " + SCORE
            + " INTEGER NULL, "
            + " foreign key (" + IDTEST + ")references " + TestTable.TABLE_TEST + " (" + TestTable._ID + "),"
            + " foreign key (" + IDUTIL + ")references " + UtilisateurTable.TABLE_UTILISATEUR + " (" + UtilisateurTable._ID + "),"
            + " PRIMARY KEY("+IDUTIL+","+SCORE+")); ";

    public static final String CONTENT_PATH = "utilisateurtest";
    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd.quiz.utilisateurtest";
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd.quiz.utilisateurtest";

    public static final String[] PROJECTION_ALL = {IDTEST, IDUTIL, SCORE};

    /**
     * create records table
     *
     * @param database
     */
    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_UTILISATEUR_TEST_TABLE);
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

        database.execSQL("DROP TABLE IF EXISTS " + TABLE_UTILISATEUR_TEST);
        onCreate(database);
    }


}

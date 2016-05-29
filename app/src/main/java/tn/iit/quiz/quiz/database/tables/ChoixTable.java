package tn.iit.quiz.quiz.database.tables;

import android.content.ContentResolver;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by MOHAMED ALI DRISS on 17/05/2016.
 */
public class ChoixTable implements BaseColumns {
    public static final String TABLE_CHOIX = "choix";
    public static final String IDQESTION = "idqestion";
    public static final String TEXTCHOIX = "textChoix";
    public static final String REPONSE = "choix";
    private static final String CREATE_CHOIX_TABLE = "CREATE TABLE "
            + TABLE_CHOIX + " (" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + IDQESTION
            + " INTEGER NOT NULL, " + TEXTCHOIX
            + " TEXT NOT NULL, " + REPONSE
            + " INTEGER NOT NULL, "
            + " foreign key (" + IDQESTION + ")references " + QestionTable.TABLE_QESTION + " (" + QestionTable._ID + ") ); ";

    public static final String CONTENT_PATH = "choix";
    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd.quiz.choix";
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd.quiz.choix";

    public static final String[] PROJECTION_ALL = {_ID, IDQESTION, TEXTCHOIX, REPONSE};

    /**
     * create records table
     *
     * @param database
     */
    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_CHOIX_TABLE);
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

        database.execSQL("DROP TABLE IF EXISTS " + TABLE_CHOIX);
        onCreate(database);
    }

}

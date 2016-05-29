package tn.iit.quiz.quiz.database.tables;

import android.content.ContentResolver;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by MOHAMED ALI DRISS on 17/05/2016.
 */
public class QestionTable implements BaseColumns {

    public static final String TABLE_QESTION = "qestion";
    public static final String IDTEST = "idtest";
    public static final String QESTION = "qestion";
    public static final String URLIMAGE = "irlimage";

    private static final String CREATE_QESTION_TABLE = "CREATE TABLE "
            + TABLE_QESTION + " (" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + IDTEST
            + " INTEGER NOT NULL, " + QESTION
            + " TEXT NOT NULL, " + URLIMAGE
            + " TEXT NOT NULL, "
            + " foreign key (" + IDTEST + ")references " + TestTable.TABLE_TEST + " (" + TestTable._ID + ") ); ";

    public static final String CONTENT_PATH = "qestion";
    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd.quiz.qestion";
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd.quiz.qestion";

    public static final String[] PROJECTION_ALL = {_ID, IDTEST, QESTION, URLIMAGE};

    /**
     * create records table
     *
     * @param database
     */
    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_QESTION_TABLE);
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

        database.execSQL("DROP TABLE IF EXISTS " + TABLE_QESTION);
        onCreate(database);
    }


}

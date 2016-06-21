package tn.iit.quiz.quiz.database.tables;

import android.content.ContentResolver;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by MOHAMED ALI DRISS on 17/05/2016.
 */
public class TestTable implements BaseColumns {

    public static final String TABLE_TEST = "test";
    public static final String TYPE = "type";
    public static final String DESCRIPTION = "description";
    public static final String DIFFICULTE = "difficulte";
    private static final String CREATE_TEST_TABLE = "CREATE TABLE "
            + TABLE_TEST + " (" + _ID
            + " INTEGER PRIMARY KEY, " + TYPE
            + " TEXT NOT NULL, " + DESCRIPTION
            + " TEXT NOT NULL, " + DIFFICULTE
            + " TEXT NOT NULL );";
    public static final String CONTENT_PATH = "qestion";
    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd.quiz.qestion";
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd.quiz.qestion";

    public static final String[] PROJECTION_ALL = {_ID, TYPE, DESCRIPTION, DIFFICULTE};

    /**
     * create records table
     *
     * @param database
     */
    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TEST_TABLE);
        database.execSQL("insert into "+ TABLE_TEST+" ("+_ID+","+TYPE+","+DESCRIPTION+","+DIFFICULTE+") values (1,'History', 'h', '1');");
        database.execSQL("insert into "+ TABLE_TEST+" ("+_ID+","+TYPE+","+DESCRIPTION+","+DIFFICULTE+") values (2,'Animaux', 'animaux Sauvage', '1');");
        database.execSQL("insert into "+ TABLE_TEST+" ("+_ID+","+TYPE+","+DESCRIPTION+","+DIFFICULTE+") values (3,'Food', 'f', '1');");
        database.execSQL("insert into "+ TABLE_TEST+" ("+_ID+","+TYPE+","+DESCRIPTION+","+DIFFICULTE+") values (4,'Science', 's', '1');");
        database.execSQL("insert into "+ TABLE_TEST+" ("+_ID+","+TYPE+","+DESCRIPTION+","+DIFFICULTE+") values (5,'Technologie', 't', '1');");
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

        database.execSQL("DROP TABLE IF EXISTS " + TABLE_TEST);
        onCreate(database);
    }


}

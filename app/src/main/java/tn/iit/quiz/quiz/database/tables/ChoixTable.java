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

        database.execSQL("insert into "+ TABLE_CHOIX+" ("+IDQESTION+","+TEXTCHOIX+","+ REPONSE+") values (1, 'Gravelot à collier interrompu', '1');");
        database.execSQL("insert into "+ TABLE_CHOIX+" ("+IDQESTION+","+TEXTCHOIX+","+ REPONSE+") values (1, 'Grand gravelot', '0');");
        database.execSQL("insert into "+ TABLE_CHOIX+" ("+IDQESTION+","+TEXTCHOIX+","+ REPONSE+") values (1, 'Petit gravelot', '0');");
        database.execSQL("insert into "+ TABLE_CHOIX+" ("+IDQESTION+","+TEXTCHOIX+","+ REPONSE+") values (1, 'Vanneau huppé', '0');");

        database.execSQL("insert into "+ TABLE_CHOIX+" ("+IDQESTION+","+TEXTCHOIX+","+ REPONSE+") values (2, 'Pipit farlouse', '0');");
        database.execSQL("insert into "+ TABLE_CHOIX+" ("+IDQESTION+","+TEXTCHOIX+","+ REPONSE+") values (2, 'Troglodyte mignon', '1');");
        database.execSQL("insert into "+ TABLE_CHOIX+" ("+IDQESTION+","+TEXTCHOIX+","+ REPONSE+") values (2, 'Grive draine', '0');");
        database.execSQL("insert into "+ TABLE_CHOIX+" ("+IDQESTION+","+TEXTCHOIX+","+ REPONSE+") values (2, 'Alouette des champs', '0');");

        database.execSQL("insert into "+ TABLE_CHOIX+" ("+IDQESTION+","+TEXTCHOIX+","+ REPONSE+") values (3, 'Sarcelle d hiver', '0');");
        database.execSQL("insert into "+ TABLE_CHOIX+" ("+IDQESTION+","+TEXTCHOIX+","+ REPONSE+") values (3, 'Canard colvert', '0');");
        database.execSQL("insert into "+ TABLE_CHOIX+" ("+IDQESTION+","+TEXTCHOIX+","+ REPONSE+") values (3, 'Nette rousse', '1');");
        database.execSQL("insert into "+ TABLE_CHOIX+" ("+IDQESTION+","+TEXTCHOIX+","+ REPONSE+") values (3, 'Tadorne de Belon', '0');");

            
            
            
       
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

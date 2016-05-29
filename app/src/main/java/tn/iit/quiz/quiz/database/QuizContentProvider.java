package tn.iit.quiz.quiz.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import java.util.Arrays;
import java.util.HashSet;

import tn.iit.quiz.quiz.database.tables.ChoixTable;
import tn.iit.quiz.quiz.database.tables.QestionTable;
import tn.iit.quiz.quiz.database.tables.TestTable;
import tn.iit.quiz.quiz.database.tables.UtilisateurTable;
import tn.iit.quiz.quiz.database.tables.UtilisateurTestTable;

public class QuizContentProvider extends ContentProvider {


    // database


    private QuizDatabaseHelper mOpenHelper;

    private static final String AUTHORITY = "tn.iit.quiz.quiz.provider";

    public static final Uri UTILISATEUR_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + UtilisateurTable.CONTENT_PATH);
    public static final Uri TEST_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TestTable.CONTENT_PATH);
    public static final Uri QESTION_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + QestionTable.CONTENT_PATH);
    public static final Uri CHOIX_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + ChoixTable.CONTENT_PATH);
    public static final Uri UTILISATEUR_TEST_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + UtilisateurTestTable.CONTENT_PATH);


    private static final int UTILISATEUR_ALL = 10;
    private static final int UTILISATEUR_ID = 11;
    private static final int TEST_ALL = 12;
    private static final int TEST_ID = 13;
    private static final int QESTION_ALL = 14;
    private static final int QESTION_ID = 15;
    private static final int CHOIX_ALL = 16;
    private static final int CHOIX_ID = 17;
    private static final int UTILISATEUR_TEST_ALL = 18;
    private static final int UTILISATEUR_TEST_ID = 19;


    private static final UriMatcher TEST_PROVIDER_URI_MATCHER;

    static {
        TEST_PROVIDER_URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        TEST_PROVIDER_URI_MATCHER.addURI(AUTHORITY, UtilisateurTable.CONTENT_PATH, UTILISATEUR_ALL);
        TEST_PROVIDER_URI_MATCHER.addURI(AUTHORITY, TestTable.CONTENT_PATH, TEST_ALL);
        TEST_PROVIDER_URI_MATCHER.addURI(AUTHORITY, QestionTable.CONTENT_PATH, QESTION_ALL);
        TEST_PROVIDER_URI_MATCHER.addURI(AUTHORITY, ChoixTable.CONTENT_PATH, CHOIX_ALL);
        TEST_PROVIDER_URI_MATCHER.addURI(AUTHORITY, UtilisateurTestTable.CONTENT_PATH, UTILISATEUR_TEST_ALL);
        TEST_PROVIDER_URI_MATCHER.addURI(AUTHORITY, UtilisateurTable.CONTENT_PATH + "/#", UTILISATEUR_ID);
        TEST_PROVIDER_URI_MATCHER.addURI(AUTHORITY, UtilisateurTestTable.CONTENT_PATH + "/#/#", UTILISATEUR_TEST_ID);


    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase sqlDB = mOpenHelper.getWritableDatabase();
        int rowsDeleted = 0;
        String id;
        switch (TEST_PROVIDER_URI_MATCHER.match(uri)) {
            case UTILISATEUR_ALL:
                rowsDeleted = sqlDB.delete(UtilisateurTable.TABLE_UTILISATEUR, selection, selectionArgs);
                break;
            case UTILISATEUR_ID:
                //retrieve the record id to delete
                id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = sqlDB.delete(UtilisateurTable.TABLE_UTILISATEUR,
                            UtilisateurTable._ID + "=" + id, null);
                } else {
                    rowsDeleted = sqlDB.delete(UtilisateurTable.TABLE_UTILISATEUR,
                            UtilisateurTable._ID + "=" + id + " and " + selection,
                            selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public String getType(Uri uri) {
        switch (TEST_PROVIDER_URI_MATCHER.match(uri)) {
            case UTILISATEUR_ALL:
                return UtilisateurTable.CONTENT_TYPE;
            case UTILISATEUR_ID:
                return UtilisateurTable.CONTENT_ITEM_TYPE;

            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase database = mOpenHelper.getWritableDatabase();
        Uri itemUri = null;

        long id = 0;


        switch (TEST_PROVIDER_URI_MATCHER.match(uri)) {
            case UTILISATEUR_ALL:

                id = database.insert(UtilisateurTable.TABLE_UTILISATEUR, null, values);
                if (id > 0) {
                    // notify all listeners of changes and return itemUri:
                    itemUri = ContentUris.withAppendedId(uri, id);
                    getContext().getContentResolver().notifyChange(itemUri, null);
                } else {
                    // something went wrong:
                    throw new SQLException("Problem while inserting into "
                            + UtilisateurTable.TABLE_UTILISATEUR + ", uri: " + uri);
                }
                break;
            case UTILISATEUR_TEST_ALL:

                id = database.insert(UtilisateurTestTable.TABLE_UTILISATEUR_TEST, null, values);
                if (id > 0) {
                    // notify all listeners of changes and return itemUri:
                    itemUri = ContentUris.withAppendedId(uri, id);
                    getContext().getContentResolver().notifyChange(itemUri, null);
                } else {
                    // something went wrong:
                    throw new SQLException("Problem while inserting into "
                            + UtilisateurTestTable.TABLE_UTILISATEUR_TEST + ", uri: " + uri);
                }
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        return itemUri;
    }


    @Override
    public boolean onCreate() {
        mOpenHelper = new QuizDatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Log.v("iit", "ContentProvider query method: CallingPackage = " + getCallingPackage());

        } else {
            //TODO

        }

        // Using SQLiteQueryBuilder instead of query() method
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        switch (TEST_PROVIDER_URI_MATCHER.match(uri)) {
            case UTILISATEUR_ALL:
                checkUtilisateurTableColumns(projection);
                queryBuilder.setTables(UtilisateurTable.TABLE_UTILISATEUR);
                break;
            case TEST_ALL:
                checkUtilisateurTableColumns(projection);
                queryBuilder.setTables(TestTable.TABLE_TEST);
                break;
            case QESTION_ALL:
                checkUtilisateurTableColumns(projection);
                queryBuilder.setTables(QestionTable.TABLE_QESTION);
                break;
            case CHOIX_ALL:
                checkUtilisateurTableColumns(projection);
                queryBuilder.setTables(ChoixTable.TABLE_CHOIX);
                break;
            case UTILISATEUR_TEST_ALL:
                checkUtilisateurTableColumns(projection);
                queryBuilder.setTables(UtilisateurTestTable.TABLE_UTILISATEUR_TEST);
                break;
            case UTILISATEUR_ID:
                // Check if the caller has requested a column which does not
                // exists
                checkUtilisateurTableColumns(projection);
                // Set the table
                queryBuilder.setTables(UtilisateurTable.TABLE_UTILISATEUR);
                // Adding the ID to the original query
                queryBuilder.appendWhere(UtilisateurTable._ID + "="
                        + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }

        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection,
                selectionArgs, null, null, sortOrder);
        // Make sure that potential listeners are getting notified
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        SQLiteDatabase database = mOpenHelper.getWritableDatabase();
        int rowsUpdated = 0;
        String id;
        switch (TEST_PROVIDER_URI_MATCHER.match(uri)) {

            case UTILISATEUR_ID:

                id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated = database.update(UtilisateurTable.TABLE_UTILISATEUR,
                            values, UtilisateurTable._ID + "=" + id, null);
                } else {
                    rowsUpdated = database.update(UtilisateurTable.TABLE_UTILISATEUR,
                            values, UtilisateurTable._ID + "=" + id + " and "
                                    + selection, selectionArgs);
                }
                break;
            case UTILISATEUR_TEST_ID:

                id = uri.getLastPathSegment();
                String idtest = uri.getPathSegments().get(uri.getPathSegments().size() - 1);
                String idutil = uri.getPathSegments().get(uri.getPathSegments().size());
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated = database.update(UtilisateurTestTable.TABLE_UTILISATEUR_TEST,
                            values, UtilisateurTestTable.IDTEST + "=" + idtest + " AND " + UtilisateurTestTable.IDUTIL + "=" + idutil, null);
                } else {
                    rowsUpdated = database.update(UtilisateurTable.TABLE_UTILISATEUR,
                            values, UtilisateurTable._ID + "=" + id + " and "
                                    + selection, selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }

    private void checkUtilisateurTableColumns(String[] projection) {

        if (projection != null) {
            HashSet<String> requestedColumns = new HashSet<String>(
                    Arrays.asList(projection));
            HashSet<String> availableColumns = new HashSet<String>(
                    Arrays.asList(UtilisateurTable.PROJECTION_ALL));
            // Check if all columns which are requested are available
            if (!availableColumns.containsAll(requestedColumns)) {
                throw new IllegalArgumentException(
                        "Unknown columns in projection");
            }
        }
    }
}
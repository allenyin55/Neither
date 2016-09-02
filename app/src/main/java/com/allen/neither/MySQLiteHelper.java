package com.allen.neither;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorJoiner;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;

public class MySQLiteHelper extends SQLiteOpenHelper {

    // Results table name
    private static final String TABLE_RESULTS = "results";

    // Results Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_YESRESULT = "yesResult";
    private static final String KEY_NORESULT = "noResult";
    private static final String KEY_NEIRESULT = "neiResult";

    private static final String[] COLUMNS = {KEY_ID,KEY_YESRESULT,KEY_NORESULT, KEY_NEIRESULT};

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "ResultDB";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create result table
        String CREATE_RESULT_TABLE = "CREATE TABLE results ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "yesResult INTEGER, "+
                "noResult INTEGER, "+
                "neiResult INTEGER )";

        // create Results table
        db.execSQL(CREATE_RESULT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS books");

        // create fresh books table
        this.onCreate(db);
    }

    public void addResults(ResultDataClass result){
        //for logging
        Log.d("addResult", result.toString());

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_YESRESULT, result.getYesResult());
        values.put(KEY_NORESULT, result.getNoResult());
        values.put(KEY_NEIRESULT, result.getNeiResult());

        // 3. insert
        db.insert(TABLE_RESULTS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    public List<ResultDataClass> getAllResults() {
        List<ResultDataClass> results = new LinkedList<ResultDataClass>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_RESULTS;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        ResultDataClass result = null;
        if (cursor.moveToFirst()) {
            do {
                result = new ResultDataClass();
                result.setId(Integer.parseInt(cursor.getString(0)));
                result.setYesResult(Integer.parseInt(cursor.getString(1)));
                result.setNoResult(Integer.parseInt(cursor.getString(2)));
                result.setNeiResult(Integer.parseInt(cursor.getString(3)));

                // Add result to results
                results.add(result);
            } while (cursor.moveToNext());
        }

        Log.d("getAllResults()", results.toString());

        // return results
        return results;
    }

    public ResultDataClass getResult(int id){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_RESULTS, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build book object
        ResultDataClass result = new ResultDataClass();
        result.setId(Integer.parseInt(cursor.getString(0)));
        result.setYesResult(Integer.parseInt(cursor.getString(1)));
        result.setNoResult(Integer.parseInt(cursor.getString(2)));
        result.setNeiResult(Integer.parseInt(cursor.getString(3)));


        //log
        Log.d("getBook("+id+")", result.toString());

        // 5. return book
        return result;
    }

    public void deleteResult() {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.execSQL("delete from "+ TABLE_RESULTS);

        // 3. close
        db.close();

    }

}
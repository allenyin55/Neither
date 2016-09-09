package com.allen.neither;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

public class MySQLiteThoughts extends SQLiteOpenHelper {

    // thoughts table name
    private static final String TABLE_THOUGHTS = "thoughts";

    // thoughts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_THOUGHTS = "newThoughts";

    private static final String[] COLUMNS = {KEY_ID,KEY_THOUGHTS};

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "ThoughtsDB";

    public MySQLiteThoughts(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create result table
        String CREATE_THOUGHT_TABLE = "CREATE TABLE thoughts ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "thoughts STRING)";

        // create thoughts table
        db.execSQL(CREATE_THOUGHT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS books");

        // create fresh books table
        this.onCreate(db);
    }

    public void addThoughts(ThoughtsDataClass thoughts){
        //for logging
        Log.d("addThought", thoughts.toString());

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_THOUGHTS, thoughts.getThoughts());

        // 3. insert
        db.insert(TABLE_THOUGHTS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    public List<ThoughtsDataClass> getAllThoughts() {
        List<ThoughtsDataClass> thoughts = new LinkedList<ThoughtsDataClass>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_THOUGHTS;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        ThoughtsDataClass thought = null;
        if (cursor.moveToFirst()) {
            do {
                thought = new ThoughtsDataClass();
                thought.setId(Integer.parseInt(cursor.getString(0)));
                thought.setThoughts(cursor.getString(1));

                // Add result to thoughts
                thoughts.add(thought);
            } while (cursor.moveToNext());
        }

        Log.d("getAllThoughts()", thoughts.toString());

        // return thoughts
        return thoughts;
    }

    public ThoughtsDataClass getThoughts(int id){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_THOUGHTS, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got thoughts get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build book object
        ThoughtsDataClass thought = new ThoughtsDataClass();
        thought.setId(Integer.parseInt(cursor.getString(0)));
        thought.setThoughts(cursor.getString(1));


        //log
        Log.d("getThoughts("+id+")", thought.toString());

        // 5. return book
        return thought;
    }

    public void deleteThoughts() {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.execSQL("delete from "+ TABLE_THOUGHTS);

        // 3. close
        db.close();

    }

}
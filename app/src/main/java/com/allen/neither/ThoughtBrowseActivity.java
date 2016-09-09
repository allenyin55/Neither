package com.allen.neither;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class ThoughtBrowseActivity extends AppCompatActivity {

    private static final String TAG = "thoughts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thought_browse);

        Intent intent = getIntent();

        String thought = intent.getStringExtra("newThought");

        TextView t = (TextView)findViewById(R.id.showThoughts);

        MySQLiteThoughts db = new MySQLiteThoughts(this);

        /**
         * CRUD Operations
         * */
        // add Books
        db.addThoughts(new ThoughtsDataClass(thought));

        /** Caution!! This is for DELETING all the data!!
         *db.deleteResult();
         *  */

        // get all books
        List<ThoughtsDataClass> list = db.getAllThoughts();

        Log.i(TAG, String.valueOf(list.size()));

        for (int i = 0; i < list.size(); i++){
            Log.i(TAG, list.get(i).getThoughts());

        }


    }
}

package com.allen.neither;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ThoughtEntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thought_entry);

        final EditText thoughtsEntry = (EditText) findViewById(R.id.thoughtsEntry);
        Button saveButton = (Button) findViewById(R.id.saveTButton);



        saveButton.setOnClickListener(
                new Button.OnClickListener()

                {
                    public void onClick (View v){
                        Intent intent = new Intent(ThoughtEntryActivity.this, ThoughtBrowseActivity.class);
                        intent.putExtra("newThought", thoughtsEntry.getText().toString());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        ThoughtEntryActivity.this.startActivity(intent);
                    }
                }

        );
    }
}

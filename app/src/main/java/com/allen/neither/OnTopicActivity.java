package com.allen.neither;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OnTopicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_topic);

        Button yesResultButton = (Button) findViewById(R.id.posButton);
        Button negResultButton = (Button) findViewById(R.id.negButton);
        Button neiResultButton = (Button) findViewById(R.id.neiButton);
        Button thoughtsEntryButton = (Button) findViewById(R.id.yourThought);
        Button thoughtsBrowseButton = (Button) findViewById(R.id.peopleThought);

        yesResultButton.setOnClickListener(
                new Button.OnClickListener()

                {
                    public void onClick (View v){
                        Intent intent = new Intent(OnTopicActivity.this, TopicResultActivity.class);
                        intent.putExtra("result", "yes");
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        OnTopicActivity.this.startActivity(intent);
                    }
                }

        );

        negResultButton.setOnClickListener(
                new Button.OnClickListener()

                {
                    public void onClick (View v){
                        Intent intent = new Intent(OnTopicActivity.this, TopicResultActivity.class);
                        intent.putExtra("result", "no");
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        OnTopicActivity.this.startActivity(intent);
                    }
                }

        );

        neiResultButton.setOnClickListener(
                new Button.OnClickListener()

                {
                    public void onClick (View v){
                        Intent intent = new Intent(OnTopicActivity.this, TopicResultActivity.class);
                        intent.putExtra("result", "nei");
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        OnTopicActivity.this.startActivity(intent);
                    }
                }

        );

        thoughtsEntryButton.setOnClickListener(
                new Button.OnClickListener()

                {
                    public void onClick (View v){
                        Intent intent = new Intent(OnTopicActivity.this, ThoughtEntryActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        OnTopicActivity.this.startActivity(intent);
                    }
                }

        );

        thoughtsBrowseButton.setOnClickListener(
                new Button.OnClickListener()

                {
                    public void onClick (View v){
                        Intent intent = new Intent(OnTopicActivity.this, ThoughtBrowseActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        OnTopicActivity.this.startActivity(intent);
                    }
                }

        );

    }
}

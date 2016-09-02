package com.allen.neither;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button toMediaButton = (Button) findViewById(R.id.mediaButton1);

        toMediaButton.setOnClickListener(
                new Button.OnClickListener()

                {
                    public void onClick (View v){
                        Intent intent = new Intent(MainActivity.this, OnMediaActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        MainActivity.this.startActivity(intent);
                    }
                }

        );

        Button toTopicButton = (Button) findViewById(R.id.topicButton1);

        toTopicButton.setOnClickListener(
                new Button.OnClickListener()
                {
                    public  void onClick(View v){
                        Intent intent = new Intent (MainActivity.this, OnTopicActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        MainActivity.this.startActivity(intent);
                    }
                }
        );

    }


}

package com.android.highlifestudio.fitnessapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TrainingActivity extends AppCompatActivity {

    Button startStop;
    boolean clicked=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        startStop= (Button) findViewById(R.id.startStopButton);
        startStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked= !clicked;
                if(clicked)
                    startStop.setText("Stop");
                else
                    startStop.setText("Start");
            }
        });
    }
}

package com.android.highlifestudio.fitnessapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.api.GoogleApiClient;

public class TrainingActivity extends AppCompatActivity {

    Button startStop;
    GoogleApiClient mClient;
    boolean clicked=false;
    App state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        state=(App) getApplicationContext();
        mClient=state.getClient();
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

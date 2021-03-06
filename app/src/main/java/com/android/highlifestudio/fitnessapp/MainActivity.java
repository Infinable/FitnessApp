package com.android.highlifestudio.fitnessapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.request.DataReadRequest;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "XFit";
    private GoogleApiClient mClient = null;
    private Steps steps;
    private TextView textViewTotalDailySteps;
    private TextView textViewDistance;
    Button startTraining;
    App state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        state=((App) getApplicationContext());
        startTraining= (Button) findViewById(R.id.startTraining);
        startTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, TrainingActivity.class);
                startActivity(intent);
            }
        });
        textViewTotalDailySteps = (TextView) findViewById(R.id.textViewDailySteps);
        textViewDistance = (TextView) findViewById(R.id.textViewDistance);
        if(mClient == null) { // TODO add checkPermission if needed by Data type + add requestPermission for android 6.0+
            connectGoogleApi();
        }
        Subscription subscription = new Subscription(mClient);
        subscription.subscribeToAll();

        steps = new Steps(mClient,this,textViewTotalDailySteps);
        steps.getDailySteps();

        Distance distance = new Distance(mClient,textViewDistance);
        distance.readTotalDailyDistance();

    }

    void connectGoogleApi()
    {
        mClient = new GoogleApiClient.Builder(this)
                .addApi(Fitness.HISTORY_API)
                .addApi(Fitness.RECORDING_API)
                .addScope(new Scope(Scopes.FITNESS_LOCATION_READ))
                .addScope(new Scope(Scopes.FITNESS_ACTIVITY_READ))
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(@Nullable Bundle bundle) {
                        Log.d(TAG,"Connected to GoogleApi!");
                    }

                    @Override
                    public void onConnectionSuspended(int i) {
                        if (i == GoogleApiClient.ConnectionCallbacks.CAUSE_NETWORK_LOST) {
                            Log.d(TAG, "Connection lost.  Cause: Network Lost.");
                        } else if (i
                                == GoogleApiClient.ConnectionCallbacks.CAUSE_SERVICE_DISCONNECTED) {
                            Log.d(TAG,
                                    "Connection lost.  Reason: Service Disconnected");
                        }
                    }
                })
                .enableAutoManage(this, 0, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Log.d(TAG,"Google Play services connection failed. Cause: " +
                                connectionResult.toString());
                        Toast.makeText(MainActivity.this, "Connection to GoogleApi failed!", Toast.LENGTH_LONG).show();
                    }
                })
                .build();
        state.setClient(mClient);
    }

}

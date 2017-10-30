package com.android.highlifestudio.fitnessapp;

import android.app.Application;

import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Angelo on 30.10.2017.
 */

public class App extends Application {
    private GoogleApiClient mClient;

    public void setClient(GoogleApiClient mClient){
        this.mClient=mClient;
    }
    public GoogleApiClient getClient(){
        return mClient;
    }
}

package com.android.highlifestudio.fitnessapp;

import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessStatusCodes;
import com.google.android.gms.fitness.data.DataType;


public class Subscription {
    private GoogleApiClient mClient;

    private String TAG = "XFit";

   public Subscription(GoogleApiClient mClient){
       this.mClient = mClient;
       subscribeToAll();
   }

   private void subscribeToAll(){
       Fitness.RecordingApi.subscribe(mClient, DataType.TYPE_STEP_COUNT_DELTA)
               .setResultCallback(new ResultCallback<Status>() {
                   @Override
                   public void onResult(@NonNull Status status) {
                       if(status.isSuccess()){
                           if(status.getStatusCode() == FitnessStatusCodes.SUCCESS_ALREADY_SUBSCRIBED){
                               Log.d(TAG,"Existing subscription for activity detected");
                           } else {
                               Log.d(TAG,"Successfully subscribed!");
                           }
                       } else{
                           Log.d(TAG,"There was a problem subscribing");
                       }
                           }
                       }
               );

       Fitness.RecordingApi.subscribe(mClient, DataType.TYPE_SPEED) // m/s
               .setResultCallback(new ResultCallback<Status>() {
                                      @Override
                                      public void onResult(@NonNull Status status) {
                                          if(status.isSuccess()){
                                              if(status.getStatusCode() == FitnessStatusCodes.SUCCESS_ALREADY_SUBSCRIBED){
                                                  Log.d(TAG,"Existing subscription for activity detected");
                                              } else {
                                                  Log.d(TAG,"Successfully subscribed!");
                                              }
                                          } else{
                                              Log.d(TAG,"There was a problem subscribing");
                                          }
                                      }
                                  }
               );

       Fitness.RecordingApi.subscribe(mClient, DataType.TYPE_DISTANCE_CUMULATIVE) //TODO
               .setResultCallback(new ResultCallback<Status>() {
                                      @Override
                                      public void onResult(@NonNull Status status) {
                                          if(status.isSuccess()){
                                              if(status.getStatusCode() == FitnessStatusCodes.SUCCESS_ALREADY_SUBSCRIBED){
                                                  Log.d(TAG,"Existing subscription for activity detected");
                                              } else {
                                                  Log.d(TAG,"Successfully subscribed!");
                                              }
                                          } else{
                                              Log.d(TAG,"There was a problem subscribing");
                                          }
                                      }
                                  }
               );

       Fitness.RecordingApi.subscribe(mClient, DataType.AGGREGATE_DISTANCE_DELTA)
               .setResultCallback(new ResultCallback<Status>() {
                                      @Override
                                      public void onResult(@NonNull Status status) {
                                          if(status.isSuccess()){
                                              if(status.getStatusCode() == FitnessStatusCodes.SUCCESS_ALREADY_SUBSCRIBED){
                                                  Log.d(TAG,"Existing subscription for activity detected");
                                              } else {
                                                  Log.d(TAG,"Successfully subscribed!");
                                              }
                                          } else{
                                              Log.d(TAG,"There was a problem subscribing");
                                          }
                                      }
                                  }
               );

   }

}

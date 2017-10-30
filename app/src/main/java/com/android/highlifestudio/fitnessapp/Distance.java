package com.android.highlifestudio.fitnessapp;

import android.os.AsyncTask;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.result.DailyTotalResult;

import org.w3c.dom.Text;

/**
 * Created by Pascal on 30.10.2017.
 */

public class Distance {

    private GoogleApiClient mClient;

    private float totalDailydistance;

    private TextView textViewTotalDailyDistance;

    public Distance(GoogleApiClient mClient, TextView textViewTotalDailyDistance){
        this.mClient = mClient;
        this.textViewTotalDailyDistance = textViewTotalDailyDistance;
    }

    public void readDistanceWithGivenTime(){

    }

    public void readTotalDailyDistance(){
        PendingResult pendingResult = Fitness.HistoryApi.readDailyTotal(mClient, DataType.TYPE_DISTANCE_DELTA);
    }

    private class ReadTotalDailyDistance extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected Void doInBackground(Void... params) {

            PendingResult<DailyTotalResult> pendingResult = Fitness.HistoryApi.readDailyTotal(mClient,DataType.TYPE_DISTANCE_DELTA);
            DailyTotalResult dailyTotalResult = pendingResult.await();
            DataSet dataSet = dailyTotalResult.getTotal();
            if(!dataSet.isEmpty()){

                totalDailydistance = dataSet.getDataPoints().get(0).getValue(Field.FIELD_DISTANCE).asFloat() / 2; //get data and convert from m to km
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            String temp = Float.toString(totalDailydistance) + " km";
            textViewTotalDailyDistance.setText(temp);

        }
    }
}

package com.android.highlifestudio.fitnessapp;

import android.content.Context;
import android.icu.text.LocaleDisplayNames;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessStatusCodes;
import com.google.android.gms.fitness.HistoryApi;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.result.DailyTotalResult;
import com.google.android.gms.fitness.result.DataReadResult;

/**
 * Created by Pascal on 28.10.2017.
 */

public class Steps {

    private String TAG = "XFit";

    private GoogleApiClient mClient = null;

    private long dailySteps = 0;

    private Context context;


    public Steps(GoogleApiClient mClient,Context context) {

        this.mClient = mClient;
        this.context = context;
    }

    public void getDailySteps() {
        ReadStepsFromApi readStepsFromApi = new ReadStepsFromApi();
        readStepsFromApi.execute();


    }




    private class ReadStepsFromApi extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected Void doInBackground(Void... params) {
            PendingResult<DailyTotalResult> pendingResult = Fitness.HistoryApi.readDailyTotal(mClient,DataType.TYPE_STEP_COUNT_DELTA);
            DailyTotalResult totalResult = pendingResult.await(); // wait for result
            if(totalResult.getStatus().isSuccess()) {
                DataSet totalSet = totalResult.getTotal();
                if(totalSet.isEmpty()){ // if set is empty Steps = 0
                    Log.d(TAG,"No steps for today");
                } else{
                    dailySteps = totalSet.getDataPoints().get(0).getValue(Field.FIELD_STEPS).asInt(); // if set has element: First element is the number of steps
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(context,Long.toString(dailySteps),Toast.LENGTH_LONG).show();
            super.onPostExecute(aVoid);

        }
    }
}

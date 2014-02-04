package com.isextension;

import com.ideaworks3d.marmalade.LoaderAPI;
import com.ideaworks3d.marmalade.LoaderActivity;

import android.util.Log;
import android.os.Bundle;
import com.chartboost.sdk.*;


public class IsChartboostActivity extends LoaderActivity
{
    private final String TAG = "CHARTBOOST";
    
    @Override
    protected void onStart()
    {
        super.onStart();
        
		Chartboost.sharedChartboost();
    }
    
    @Override
    protected void onPause()
    {
        Log.v(TAG, "onPause");
        super.onPause();
    }

     @Override
    protected void onResume()
    {
        Log.v(TAG, "onResume");
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
		Chartboost.sharedChartboost().onDestroy(this);
    }

	@Override
	protected void onStop() {
		super.onStop();
		Chartboost.sharedChartboost().onStop(this);
	}

    @Override
    public void onBackPressed() {
       // If an interstitial is on screen, close it. Otherwise continue as normal.
       if (Chartboost.sharedChartboost().onBackPressed())
          return;
	   else
            super.onBackPressed();
    }
}


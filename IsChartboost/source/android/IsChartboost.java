package com.isextension;

import com.ideaworks3d.marmalade.LoaderAPI;
import com.ideaworks3d.marmalade.LoaderActivity;

import android.util.Log;
import android.os.Bundle;
import com.chartboost.sdk.*;


public class IsChartboost
{
	private final String TAG = "CHARTBOOST";

	private String APP_ID = null;
    private String APP_SIGNATURE = null;
    private Boolean setCallback = false;
    
    // Native JNI functions used for EDK Callback
    public native void IsChartboostRequestCallback(int result);
    public native void IsChartboostAdClosedCallback(int result);
    public native void IsChartboostAdDismissedRequestCallback(int result);
    public native void IsChartboostAdClickedRequestCallback(int result);
	
    public void IsChartboostCacheInterstitial(String name)
    {
        Chartboost.sharedChartboost().cacheInterstitial(name);
	}

        public void IsChartboostSetAppID(String id)
    {
        if(id == null)
            return;
            		Log.i(TAG, "AppID Called with "+ id);

		APP_ID = id;

    }
    public void IsChartboostSetAppSignature(String signature)
    {
        if(signature == null)
            return;

        Log.i(TAG, "AppSig Called with "+ signature);

		APP_SIGNATURE = signature;
    }
    public void IsChartboostStartSession()
    {
		Chartboost.sharedChartboost().onCreate(LoaderActivity.m_Activity, APP_ID, APP_SIGNATURE, chartBoostDelegate);
		Chartboost.sharedChartboost().startSession();
		Chartboost.sharedChartboost().onStart(LoaderActivity.m_Activity);
    }

    public void IsChartboostRequestAd()
    {
        setCallback = true;
        Chartboost.sharedChartboost().showInterstitial();
    }
    public void IsChartboostShowInterstitial(String name)
    {
        Chartboost.sharedChartboost().showInterstitial();
    }
    public void IsChartboostCacheMoreApps()
    {
        Chartboost.sharedChartboost().cacheMoreApps();
    }
    public void IsChartboostShowMoreApps()
    {
        Chartboost.sharedChartboost().showMoreApps();
    }
    private ChartboostDelegate chartBoostDelegate = new ChartboostDelegate() {

    // See https://help.chartboost.com/documentation/android/delegation
    // For more information on delegates and how they behave on android 
    // by chartboost.

        @Override 
        public boolean shouldRequestInterstitial(String paramString)
        {
            Log.i(TAG, "shouldRequestInterstitial");
            return true;
        }
        
        @Override 
        public boolean shouldDisplayInterstitial(String paramString)
        {
            Log.i(TAG, "shouldDisplayInterstitial CB Set to " +  setCallback + " : " + paramString);

            if(setCallback)
            {
                setCallback = false;
                Chartboost chartb = Chartboost.sharedChartboost();
                chartb.cacheInterstitial();
                IsChartboostRequestCallback(0); // Success
                return false;
            }

           return true;


        }
        @Override
        public void didCacheInterstitial(String paramString) {}

        @Override
        public void didFailToLoadInterstitial(String paramString)
        {
            IsChartboostRequestCallback(1); 
            Log.i(TAG, "INTERSTITIAL REQUEST FAILED");
            
        }

        @Override
        public void didDismissInterstitial(String paramString)
        {
            IsChartboostAdDismissedRequestCallback(1);
            Log.i(TAG, "INTERSTITIAL DISMISSED");
        }
        
        @Override
        public void didCloseInterstitial(String paramString)
        {
            IsChartboostAdClosedCallback(1);
            Log.i(TAG, "INSTERSTITIAL CLOSED");

        }

        @Override
        public void didClickInterstitial(String paramString)
        {
            IsChartboostAdClosedCallback(1);
            Log.i(TAG, "DID CLICK INTERSTITIAL");
        }
        
        @Override
        public void didShowInterstitial(String paramString){}
   
        @Override 
        public boolean shouldDisplayLoadingViewForMoreApps()
        {
            return true;
        }
        
        @Override
        public boolean shouldRequestMoreApps() {

            return true;
        }
        
        @Override
        public void didCacheMoreApps()
        {

        }

        @Override
        public boolean shouldDisplayMoreApps()
        {
            Log.i(TAG, "SHOULD DISPLAY MORE APPS?");
            return true;
        }
        
        @Override
        public void didFailToLoadMoreApps()
        {
            Log.i(TAG, "MORE APPS REQUEST FAILED");
   
        }

        @Override
        public void didDismissMoreApps()
        {
            Log.i(TAG, "MORE APPS DISMISSED");
        }
        
        @Override
        public void didCloseMoreApps()
        {
            Log.i(TAG, "MORE APPS CLOSED");
        }

        @Override
        public void didClickMoreApps()
        {
            Log.i(TAG, "MORE APPS CLICKED");
        }       

        @Override
        public void didShowMoreApps()
        {

        }

        @Override
        public boolean shouldRequestInterstitialsInFirstSession()
        {
            return true;
        }
    };

}


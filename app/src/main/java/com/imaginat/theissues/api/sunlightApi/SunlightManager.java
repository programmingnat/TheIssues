package com.imaginat.theissues.api.sunlightApi;

import com.imaginat.theissues.api.sunlightApi.models.SunLight_District_Query_Results;
import com.imaginat.theissues.api.sunlightApi.models.SunLight_Legislator_Query_Results;
import com.imaginat.theissues.models.User_info;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by nat on 6/22/16.
 */
public class SunlightManager {
    //implements Callback<SunLight_District_Query_Results>

    private static final String TAG = SunlightManager.class.getSimpleName();
    private static SunlightManager mInstance;
    private SunlightAPIService mSunlightAPIService=null;

    private SunlightManager(){
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//added this once started using RxJava
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://congress.api.sunlightfoundation.com/")
                .build();

        mSunlightAPIService = retrofit.create(SunlightAPIService.class);
    }



    public static SunlightManager getInstance(){
        if(mInstance==null){
            mInstance = new SunlightManager();
        }
        return mInstance;
    }

    public Observable<SunLight_District_Query_Results>getSunlightDistrictResults(User_info userInfo,String apiKey){
        return mSunlightAPIService
                .getDistricts(userInfo.getLatitude(),
                        userInfo.getLongitude(),
                        userInfo.getZipCode(),
                        apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public Observable<SunLight_Legislator_Query_Results>getSunlightLegislatorResults(User_info userInfo,String apiKey){
        return mSunlightAPIService
                .getLegislators(userInfo.getLatitude(),
                        userInfo.getLongitude(),
                        userInfo.getZipCode(),
                        apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //Pre RxJava
/*
    public void getDistrict(User_info userInfo,String apiKey){
        Call<SunLight_District_Query_Results> callResults = mSunlightAPIService.getDistricts(userInfo.getLatitude(),
                userInfo.getLongitude(),
                userInfo.getZipCode(), apiKey);
        callResults.enqueue(SunlightManager.this);
    }
    @Override
    public void onResponse(Call<SunLight_District_Query_Results> call, Response<SunLight_District_Query_Results> response) {
        Log.d(TAG,"Sunlight_District_Query_Result onREsponse called");
        SunLight_District_Query_Results queryBody = response.body();
        SunLight_District_Query_Result[] results = queryBody.getResults();
        for(SunLight_District_Query_Result r:results){
            Log.d(TAG,"DISTRICT FOUND FOR USER IS "+r.getDistrict());
        }

    }

    @Override
    public void onFailure(Call<SunLight_District_Query_Results> call, Throwable t) {
            Log.d(TAG,"Sunlight_District_Query_Result onFailure called");
    }
    */
}

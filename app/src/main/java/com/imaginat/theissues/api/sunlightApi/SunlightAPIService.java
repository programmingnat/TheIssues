package com.imaginat.theissues.api.sunlightApi;

import com.imaginat.theissues.api.sunlightApi.models.SunLight_District_Query_Results;
import com.imaginat.theissues.api.sunlightApi.models.SunLight_Legislator_Query_Results;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by nat on 6/22/16.
 */
public interface SunlightAPIService {

    @GET("districts/locate")
    Observable<SunLight_District_Query_Results> getDistricts(@Query("latitude")String latitude,
                                                             @Query("longitude")String longitude,
                                                             @Query("zip")String zipCode,
                                                             @Query("apikey")String key);

    @GET("legislators/locate")
    Observable<SunLight_Legislator_Query_Results>getLegislators(@Query("latitude")String latitude,
                                                                @Query("longitude")String longitude,
                                                                @Query("zip")String zipCode,
                                                                @Query("apikey")String key);



}

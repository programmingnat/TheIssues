package com.imaginat.theissues.api.nytimesApi;

import android.util.Log;

import com.imaginat.theissues.api.nytimesApi.models.Doc;
import com.imaginat.theissues.api.nytimesApi.models.Headline;
import com.imaginat.theissues.api.nytimesApi.models.NYTimesArticleSearchResult;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by nat on 6/25/16.
 */
public class NYTimesManager
    implements Callback<NYTimesArticleSearchResult> {

    private static final String TAG = NYTimesManager.class.getSimpleName();
    private static NYTimesManager mInstance;
    private NYTimesAPIService mNYTimesAPIService=null;

    private NYTimesManager(){

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

//
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//added this once started using RxJava
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.nytimes.com/svc/search/")
                .client(client.build())
                .build();




        mNYTimesAPIService = retrofit.create(NYTimesAPIService.class);
    }



    public static NYTimesManager getInstance(){
        if(mInstance==null){
            mInstance = new NYTimesManager();
        }
        return mInstance;
    }

    public Observable<NYTimesArticleSearchResult> searchArticles(String query, String apiKey){
        return mNYTimesAPIService.searchForArticles(query, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
    @Override
    public void onResponse(Call<NYTimesArticleSearchResult> call, Response<NYTimesArticleSearchResult> response) {
        Log.d(TAG,"We have some responses");

        NYTimesArticleSearchResult nytSearchResults =  response.body();
        com.imaginat.theissues.api.nytimesApi.models.Response searchResponse=nytSearchResults.getResponse();
        Doc[] docs = searchResponse.getDocs();

        if(docs==null){
            return;
        }
        for(Doc d:docs){
            Headline headline = d.getHeadline();
            String main = headline.getMain();
           Log.d(TAG,"headline "+main);
        }
    }

    @Override
    public void onFailure(Call<NYTimesArticleSearchResult> call, Throwable t) {
        Log.d(TAG,"onFailure");
    }


}

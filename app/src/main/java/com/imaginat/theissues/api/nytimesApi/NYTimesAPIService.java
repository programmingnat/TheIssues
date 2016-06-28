package com.imaginat.theissues.api.nytimesApi;

import com.imaginat.theissues.api.nytimesApi.models.NYTimesArticleSearchResult;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by nat on 6/25/16.
 */
public interface NYTimesAPIService {

    @GET("v2/articlesearch.json")
    Observable<NYTimesArticleSearchResult> searchForArticles(@Query("q")String q,
                                                             @Query("api-key")String apikey);
}

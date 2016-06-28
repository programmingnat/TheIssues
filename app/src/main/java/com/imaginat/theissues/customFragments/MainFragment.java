package com.imaginat.theissues.customFragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imaginat.theissues.R;
import com.imaginat.theissues.api.nytimesApi.NYTimesManager;
import com.imaginat.theissues.api.nytimesApi.models.Doc;
import com.imaginat.theissues.api.nytimesApi.models.Headline;
import com.imaginat.theissues.api.nytimesApi.models.Multimedium;
import com.imaginat.theissues.api.nytimesApi.models.Response;
import com.imaginat.theissues.api.sunlightApi.SunlightManager;
import com.imaginat.theissues.api.sunlightApi.models.SunLight_District_Query_Results;
import com.imaginat.theissues.api.sunlightApi.models.SunLight_Legislator_Query_Result;
import com.imaginat.theissues.managers.HeadlinesManager;
import com.imaginat.theissues.managers.LegislatorManager;
import com.imaginat.theissues.models.User_info;

import java.util.List;

import rx.Subscriber;

/**
 * Created by nat on 6/25/16.
 */
public class MainFragment extends Fragment
    implements MainFragment_NYT_Adapter.INYT_Adapter{

    private static final String TAG = MainFragment.class.getSimpleName();
    private static int counter = 0;
    private RecyclerView mLegislators_RecyclerView,mHeadlines_RecyclerView;
    private MainFragment_NYT_Adapter mHeadlines_Adapter;
    private MainFragment_Leg_Adapter mLeg_Adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);

        //Legislator List
        mLegislators_RecyclerView = (RecyclerView)view.findViewById(R.id.legislators_recyclerview);
        mLeg_Adapter=new MainFragment_Leg_Adapter();
        mLegislators_RecyclerView.setAdapter(mLeg_Adapter);
        LinearLayoutManager legAdapterLinearLayout=new LinearLayoutManager(getActivity());
        legAdapterLinearLayout.setOrientation(LinearLayoutManager.HORIZONTAL);
        mLegislators_RecyclerView.setLayoutManager(legAdapterLinearLayout);

        //Headlines List
        mHeadlines_RecyclerView = (RecyclerView)view.findViewById(R.id.headlines_recyclerview);
        mHeadlines_Adapter = new MainFragment_NYT_Adapter(this);
        mHeadlines_RecyclerView.setAdapter(mHeadlines_Adapter);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mHeadlines_RecyclerView.setLayoutManager(linearLayoutManager);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "Inside onREsume");
        SunlightManager slManager = SunlightManager.getInstance();
        User_info user = new User_info();
        user.setLatitude("40.959560");
        user.setLongitude("-73.809557");
        user.setZipCode("10707");
        Subscriber<SunLight_District_Query_Results> mySubscriber = new Subscriber<SunLight_District_Query_Results>() {


            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(SunLight_District_Query_Results sunLight_district_query_results) {
                Log.d(TAG, "onNext of observer called");
            }
        };
        //Get the district
        //slManager.getSunlightDistrictResults(user, getActivity().getResources().getString(R.string.sunlightAPIKEY))
        //        .subscribe(mySubscriber);

        LegislatorManager legislatorManager = LegislatorManager.getInstance();

        //listen for changes in legislator manager?
        legislatorManager.getObservableLegislatorList().subscribe(legislator -> {
            Log.d(TAG, "LOOK AT WHAT I OBSERVED" + legislator.toString());


        }, error -> {
            Log.d(TAG, error.toString());
        });


        //Get the Senators and Representatives
        slManager.getSunlightLegislatorResults(user, getActivity().getResources().getString(R.string.sunlightAPIKEY))
                .subscribe(results -> {


                    SunLight_Legislator_Query_Result[] allResults = results.getResults();

                    for (SunLight_Legislator_Query_Result query_result : allResults) {
                        legislatorManager.addLegislator(query_result.getFirst_name(),
                                query_result.getLast_name(),
                                query_result.getChamber(),
                                query_result.getParty(),
                                query_result.getBioguide_id());

                    }


                }, error -> {
                    Log.d(TAG, error.toString());
                }, () -> {
                    mLeg_Adapter.notifyDataSetChanged();
                    Log.d(TAG, "COMPLETED CALL");
                    legislatorManager.printAll();
                });

        NYTimesManager nytManager = NYTimesManager.getInstance();
        nytManager.searchArticles("Chuck Schumer", getString(R.string.nyTimesAPIKEY))
                .subscribe(nyTimesArticleSearchResult -> {
                    Response response = nyTimesArticleSearchResult.getResponse();
                    Doc[] docs = response.getDocs();
                    HeadlinesManager headlinesManager = HeadlinesManager.getInstance();

                    for (Doc d : docs) {
                        Headline hl = d.getHeadline();
                        if(hl==null){
                            continue;
                        }
                        Log.d(TAG, hl.getMain());
                        List<Multimedium> multimediaList = d.getMultimedia();

                        com.imaginat.theissues.models.Headline newlyCreatedHeadline=null;
                        if(multimediaList.size()>0) {
                            Multimedium multimedium=null;
                            multimedium = multimediaList.get(0);
                            newlyCreatedHeadline =
                                    new com.imaginat.theissues.models.Headline(hl.getMain(),"http://www.nytimes.com/" +multimedium.getUrl(),
                                            d.getSnippet(),d.getWebUrl());
                        }else{
                            Log.d(TAG,"multimediu does not have image");
                            newlyCreatedHeadline =
                                    new com.imaginat.theissues.models.Headline(hl.getMain(),null,
                                            d.getSnippet(),d.getWebUrl());
                        }



                        Log.d(TAG,"adding headlinen to manager");
                        headlinesManager.addHeadline(newlyCreatedHeadline);

                    }
                    mHeadlines_Adapter.notifyDataSetChanged();
                },error->{
                    Log.d(TAG,"Error from nyTimeManager search "+error.toString());
                });

    }


    @Override
    public void onClick(View v, String data) {
        //Toast.makeText(getContext(),data,Toast.LENGTH_SHORT).show();
        Uri uri = Uri.parse(data);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}

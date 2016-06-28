package com.imaginat.theissues.managers;

import android.util.Log;

import com.imaginat.theissues.models.Legislator;
import com.imaginat.theissues.models.Representative;
import com.imaginat.theissues.models.Senator;

import java.util.ArrayList;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by nat on 6/25/16.
 */
public class LegislatorManager {
    private static final String TAG = LegislatorManager.class.getSimpleName();
    ArrayList<Legislator>mLegislators;
    PublishSubject<Legislator>onAdd;

    private static LegislatorManager instance;


    private LegislatorManager(){
        mLegislators = new ArrayList<>();
        onAdd = PublishSubject.create();
    }

    public static LegislatorManager getInstance(){
        if(instance==null){
            instance = new LegislatorManager();

        }
        return instance;
    }

    public void addLegislator(String firstName, String lastName, String chamber,String party,String bioguideID){
        Legislator legislator=null;
        if(chamber.equalsIgnoreCase("SENATE")){
            legislator=  new Senator(firstName,lastName,party,bioguideID);

        }else{
            legislator = new Representative(firstName,lastName,party,bioguideID);
        }
        onAdd.onNext(legislator);
        mLegislators.add(legislator);
    }

    public Observable<Legislator> getObservableLegislatorList(){
        return onAdd;
    }
    public Legislator getLegislatorAt(int i){
        if(i>mLegislators.size()-1){
            return null;
        }
        return mLegislators.get(i);
    }
    public int  getSize(){
        return mLegislators.size();
    }
    public void printAll(){
        Log.d(TAG,"Size of array list is "+mLegislators.size());
        for(Legislator l:mLegislators){
            Log.d(TAG,"Found "+l);
        }
    }
}

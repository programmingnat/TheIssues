package com.imaginat.theissues.managers;

import com.imaginat.theissues.models.Headline;

import java.util.ArrayList;

/**
 * Created by nat on 6/26/16.
 */
public class HeadlinesManager {
    private ArrayList<Headline>mHeadlines;
    private static HeadlinesManager instance;

    private HeadlinesManager(){
        mHeadlines = new ArrayList<>();
    }
    public static HeadlinesManager getInstance(){
        if(instance==null){
            instance = new HeadlinesManager();
        }
        return instance;
    }

    public void addHeadline(Headline headline){
        mHeadlines.add(headline);
    }

    public Headline getHeadline(int i){
        return mHeadlines.get(i);
    }

    public int getSize(){
        return mHeadlines.size();
    }

}

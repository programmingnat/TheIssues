package com.imaginat.theissues.models;

/**
 * Created by nat on 6/25/16.
 */
public class Representative extends Legislator {
    public Representative(String firstName, String lastName, String party,String bioguide) {
        super(firstName, lastName, "House of Reps", party,bioguide);
    }
}

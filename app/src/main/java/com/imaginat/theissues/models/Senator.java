package com.imaginat.theissues.models;

/**
 * Created by nat on 6/25/16.
 */
public class Senator extends Legislator {

    public Senator(String firstName, String lastName, String party,String bioguide) {
        super(firstName, lastName, "senate", party,bioguide);
    }
}

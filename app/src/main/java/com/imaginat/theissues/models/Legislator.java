package com.imaginat.theissues.models;

/**
 * Created by nat on 6/25/16.
 */
public class Legislator {
    private String mFirstName;
    private String mLastName;
    private String mChamber;
    private String mParty;
    private String mBioguide;

    public Legislator(String firstName, String lastName, String chamber, String party,String bioguide) {
        mFirstName = firstName;
        mLastName = lastName;
        mChamber = chamber;
        mParty = party;
        mBioguide=bioguide;
    }


    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getChamber() {
        return mChamber;
    }

    public void setChamber(String chamber) {
        mChamber = chamber;
    }

    public String getParty() {
        return mParty;
    }

    public void setParty(String party) {
        mParty = party;
    }

    public String getBioguide() {
        return mBioguide;
    }

    public void setBioguide(String bioguide) {
        mBioguide = bioguide;
    }

    @Override
    public String toString() {
        return "Legislator{" +
                "mFirstName='" + mFirstName + '\'' +
                ", mLastName='" + mLastName + '\'' +
                ", mChamber='" + mChamber + '\'' +
                ", mParty='" + mParty + '\'' +
                '}';
    }
}

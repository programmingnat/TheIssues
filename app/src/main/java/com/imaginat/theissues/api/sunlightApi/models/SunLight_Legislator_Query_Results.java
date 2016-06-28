package com.imaginat.theissues.api.sunlightApi.models;

/**
 * Created by nat on 6/24/16.
 */
public class SunLight_Legislator_Query_Results {
    SunLight_Legislator_Query_Result[] results;
    int count;

    public SunLight_Legislator_Query_Result[] getResults() {
        return results;
    }

    public void setResults(SunLight_Legislator_Query_Result[] results) {
        this.results = results;
    }
}

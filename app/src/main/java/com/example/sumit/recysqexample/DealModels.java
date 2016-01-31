
package com.example.sumit.recysqexample;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

//@Generated("org.jsonschema2pojo")
public class DealModels {

    @SerializedName("deals")
    @Expose
    public List<Deal> deals = new ArrayList<Deal>();

    public List<Deal> getDeals() {
        return deals;
    }

    public void setDeals(List<Deal> deals) {
        this.deals = deals;
    }
}

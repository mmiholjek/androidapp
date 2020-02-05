package com.mobileapps.myphonebook.network.data;

import com.google.gson.annotations.SerializedName;

public class Birthday {

    @SerializedName("dmy")
    private String dmy;
    @SerializedName("mdy")
    private String mdy;
    @SerializedName("raw")
    private Integer raw;

    public Birthday(String dmy, String mdy, Integer raw) {
        this.dmy = dmy;
        this.mdy = mdy;
        this.raw = raw;
    }

    public String getDmy() {
        return dmy;
    }

    public void setDmy(String dmy) {
        this.dmy = dmy;
    }

    public String getMdy() {
        return mdy;
    }

    public void setMdy(String mdy) {
        this.mdy = mdy;
    }

    public Integer getRaw() {
        return raw;
    }

    public void setRaw(Integer raw) {
        this.raw = raw;
    }

}
package com.mobileapps.myphonebook.network.data;

import com.google.gson.annotations.SerializedName;

public class CreditCard {
    @SerializedName("expiration")
    private String expiration;
    @SerializedName("number")
    private String number;
    @SerializedName("pin")
    private Integer pin;
    @SerializedName("security")
    private Integer security;

    public CreditCard(String expiration, String number, Integer pin, Integer security) {
        this.expiration = expiration;
        this.number = number;
        this.pin = pin;
        this.security = security;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    public Integer getSecurity() {
        return security;
    }

    public void setSecurity(Integer security) {
        this.security = security;
    }
}

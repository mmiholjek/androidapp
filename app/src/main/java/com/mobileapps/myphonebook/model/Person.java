package com.mobileapps.myphonebook.model;

public class Person {
    private long id;
    private String name;
    private String surname;
    private String address;
    private String country;
    private String email;
    private String gsm;
    private String company;
    private String imageUrl;

    public Person(long id, String name, String surname, String address, String country, String email, String gsm, String company, String imageUrl) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.country = country;
        this.email = email;
        this.gsm = gsm;
        this.company = company;
        this.imageUrl = imageUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGsm() {
        return gsm;
    }

    public void setGsm(String gsm) {
        this.gsm = gsm;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

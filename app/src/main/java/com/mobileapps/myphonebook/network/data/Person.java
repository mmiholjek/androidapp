package com.mobileapps.myphonebook.network.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.UUID;

public class Person {
    @SerializedName("name")
    private String name;
    @SerializedName("surname")
    private String surname;
    @SerializedName("gender")
    private String gender;
    @SerializedName("region")
    private String region;
    @SerializedName("age")
    private Integer age;
    @SerializedName("title")
    private String title;
    @SerializedName("phone")
    private String phone;
    @SerializedName("birthday")
    private Birthday birthday;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("credit_card")
    private CreditCard creditCard;
    @SerializedName("photo")
    private String photo;

    @Expose(serialize = false, deserialize = false)
    private UUID uuid = UUID.randomUUID();

    public Person(String name, String surname, String gender, String region, Integer age, String title, String phone, Birthday birthday, String email, String password, CreditCard creditCard, String photo) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.region = region;
        this.age = age;
        this.title = title;
        this.phone = phone;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
        this.creditCard = creditCard;
        this.photo = photo;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Birthday getBirthday() {
        return birthday;
    }

    public void setBirthday(Birthday birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}

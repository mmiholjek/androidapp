package com.mobileapps.myphonebook.network;

import com.mobileapps.myphonebook.network.data.Person;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PersonService {

    @GET("?ext")
    Call<List<Person>> getPeople(@Query(value = "amount") int amount);

    @GET("?ext&amount=1")
    Call<Person> getPerson();
}

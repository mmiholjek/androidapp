package com.mobileapps.myphonebook.repository;

import androidx.lifecycle.MutableLiveData;

import com.mobileapps.myphonebook.BuildConfig;
import com.mobileapps.myphonebook.network.PersonService;
import com.mobileapps.myphonebook.network.data.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PersonRepository {

    private static PersonRepository instance;

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static PersonRepository getInstance() {
        if (instance == null) {
            instance = new PersonRepository();
        }
        return instance;
    }

    private List<Person> people = new ArrayList<>();
    private MutableLiveData<List<Person>> peopleLiveData = new MutableLiveData<>();

    private PersonRepository() {
    }


    public MutableLiveData<List<Person>> getPeople() {
        retrofit.create(PersonService.class)
                .getPeople(6)
                .enqueue(new Callback<List<Person>>() {

                    @Override
                    public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                        List<Person> persons = response.body();
                        if (persons != null) {
                            persons = persons.stream().map(person -> {
                                person.setUuid(UUID.randomUUID());
                                return person;
                            }).collect(Collectors.toList());
                        }
                        peopleLiveData.postValue(persons);
                    }

                    @Override
                    public void onFailure(Call<List<Person>> call, Throwable t) {
                        peopleLiveData.postValue(null);
                    }
                });

        return peopleLiveData;
    }

    public Person findPersonById(UUID id) {
        if (peopleLiveData.getValue() == null) {
            return null;
        }

        for (Person person : peopleLiveData.getValue()) {
            if (person.getUuid().equals(id)) {
                return person;
            }
        }
        return null;
    }

    public MutableLiveData<List<Person>> addPerson() {
        retrofit.create(PersonService.class)
                .getPerson()
                .enqueue(new Callback<Person>() {

                    @Override
                    public void onResponse(Call<Person> call, Response<Person> response) {
                        Person person = response.body();
                        List<Person> existingPersons = peopleLiveData.getValue();
                        if (existingPersons == null) {
                            existingPersons = new ArrayList<>();
                        }
                        if (person != null) {
                            person.setUuid(UUID.randomUUID());
                            existingPersons.add(person);
                        }

                        peopleLiveData.postValue(existingPersons);
                    }

                    @Override
                    public void onFailure(Call<Person> call, Throwable t) {
                        peopleLiveData.postValue(null);
                    }
                });

        return peopleLiveData;
    }
}

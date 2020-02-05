package com.mobileapps.myphonebook.screen.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mobileapps.myphonebook.network.data.Person;
import com.mobileapps.myphonebook.repository.PersonRepository;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private PersonRepository personRepository = PersonRepository.getInstance();

    public LiveData<List<Person>> getPeople() {
        return personRepository.getPeople();
    }

    public LiveData<List<Person>> addRandomPerson() {
        return personRepository.addPerson();
    }
}

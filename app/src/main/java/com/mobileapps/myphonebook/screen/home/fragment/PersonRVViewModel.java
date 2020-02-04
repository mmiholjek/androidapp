package com.mobileapps.myphonebook.screen.home.fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mobileapps.myphonebook.model.Person;
import com.mobileapps.myphonebook.repository.PersonRepository;

import java.util.List;
import java.util.Random;

public class PersonRVViewModel extends ViewModel {

    private PersonRepository personRepository = PersonRepository.getInstance();

    public LiveData<List<Person>> getPerson (){return personRepository.getPeople();}

    public void addPerson(final Person person) {personRepository.addPerson(person);}

    public void addRandomPerson() {
        if (getPerson().getValue() == null){
            return;
        }
        int numberOfPerson = getPerson().getValue().size();
        int radomIndex = new Random().nextInt(numberOfPerson);
        Person randomPerson = getPerson().getValue().get(radomIndex);
        personRepository.addPerson(randomPerson);
    }
}

package com.mobileapps.myphonebook.screen.persondetails;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mobileapps.myphonebook.model.Person;
import com.mobileapps.myphonebook.repository.PersonRepository;

import java.util.List;
import java.util.Random;

public class PersonDetailsViewModel extends ViewModel {

    private PersonRepository personRepository = PersonRepository.getInstance();

    public MutableLiveData<Person> getPersonId(long id) {
        Person person = personRepository.findPersonById(id);
        MutableLiveData<Person> personLiveData = new MutableLiveData<>();
        personLiveData.setValue(person);
        return personLiveData;
    }

}

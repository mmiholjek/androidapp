package com.mobileapps.myphonebook.screen.persondetails;

import androidx.lifecycle.ViewModel;

import com.mobileapps.myphonebook.network.data.Person;
import com.mobileapps.myphonebook.repository.PersonRepository;

import java.util.UUID;

public class PersonDetailsViewModel extends ViewModel {

    private PersonRepository personRepository = PersonRepository.getInstance();

    public Person getPersonById(UUID id) {
        return personRepository.findPersonById(id);
    }
}

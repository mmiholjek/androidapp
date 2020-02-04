package com.mobileapps.myphonebook.repository;

import androidx.lifecycle.MutableLiveData;

import com.mobileapps.myphonebook.model.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonRepository {

    private static PersonRepository instance;

    public static PersonRepository getInstance(){
        if(instance == null){
            instance = new PersonRepository();
        }
        return instance;
    }

    private List<Person> people = new ArrayList<>();
    private MutableLiveData<List<Person>> liveDataPersons = new MutableLiveData<>();

    private PersonRepository(){initRepository();}

    private void initRepository(){
        people = new ArrayList<Person>(){{
            add(new Person(1,"Pero","Perić","Zagrebačka 5, Osijek","Hrvatska","pero.peric@gmail.com","+385 98 123 4567","Mobile d.o.o.","https://images.unsplash.com/photo-1500648767791-00dcc994a43e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80"));
            add(new Person(2,"Luka","Lukić","Osiječka 55, Split","Hrvatska","llukic@yahoo.com","+385 92 987 3216","DevOfTomorrow j.d.o.o.","https://i.dailymail.co.uk/i/pix/2013/08/29/article-2405475-1B8389EE000005DC-718_634x550.jpg"));
            add(new Person(3,"Ivo","Ivić","Splitska 555, Zagreb","Hrvatska","ivo.ivic@gmail.com","+385 97 654 4531","Ivic & co.","https://media.distractify.com/brand-img/adCWlmDCl/480x252/matt-brown-alaskan-bush-people-now-1574876386822.jpg"));
        }};
        liveDataPersons= new MutableLiveData<>();
        liveDataPersons.setValue(people);
    }

    public MutableLiveData<List<Person>> getPeople(){return liveDataPersons;}

    public Person findPersonById(long id){
        if(liveDataPersons.getValue() == null){
            return null;
        }
        for (Person person : liveDataPersons.getValue()){
            if (person.getId() == id){
                return person;
            }
        }
        return null;
    }

    public void addPerson(Person person){
        people.add(person);
        liveDataPersons.setValue(people);
    }
}

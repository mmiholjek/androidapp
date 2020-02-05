package com.mobileapps.myphonebook.screen.home;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mobileapps.myphonebook.R;
import com.mobileapps.myphonebook.network.data.Person;
import com.mobileapps.myphonebook.screen.home.recyclerview.PersonRecyclerViewAdapter;
import com.mobileapps.myphonebook.screen.persondetails.PersonDetailsActivity;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private PersonRecyclerViewAdapter personRecyclerViewAdapter;
    private HomeViewModel homeViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        setupViews();
        loadPeople();
    }

    private void setupViews() {
        FloatingActionButton addPersonFab = findViewById(R.id.addPersonFab);
        addPersonFab.setOnClickListener(view -> {
            homeViewModel.addRandomPerson().observe(this, people -> personRecyclerViewAdapter.setItems(people));
        });
        setupRecyclerView();
    }

    private void loadPeople() {
        homeViewModel.getPeople().observe(this, people -> personRecyclerViewAdapter.setItems(people));
    }

    private void setupRecyclerView() {
        RecyclerView personRecycleView = findViewById(R.id.personRecyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        personRecycleView.setLayoutManager(layoutManager);

        personRecyclerViewAdapter = new PersonRecyclerViewAdapter(new ArrayList<>(), ((view, position, person) -> openPersonDetails(person)));
        personRecycleView.setAdapter(personRecyclerViewAdapter);
    }

    private void openPersonDetails(Person person) {
        startActivity(PersonDetailsActivity.with(this, person.getUuid()));
    }
}

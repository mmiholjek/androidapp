package com.mobileapps.myphonebook.screen.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mobileapps.myphonebook.R;
import com.mobileapps.myphonebook.model.Person;
import com.mobileapps.myphonebook.screen.persondetails.PersonDetailsActivity;

import java.util.List;


public class PersonRVFragment extends Fragment {

    private PersonRVViewModel personRVViewModel;
    private RecyclerView.Adapter personRecyclerViewAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        personRVViewModel = ViewModelProviders.of(this).get(PersonRVViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_rv_person,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupView(view);
        setupRecyclerView(view,personRVViewModel.getPerson().getValue());
    }

    @Override
    public void onStart() {
        super.onStart();
        personRVViewModel.getPerson().observe(this,people -> personRecyclerViewAdapter.notifyDataSetChanged());
    }

    private void setupView(View framgmentView) {
        FloatingActionButton addPersonFab = framgmentView.findViewById(R.id.addPersonFab);
        addPersonFab.setOnClickListener(view -> personRVViewModel.addRandomPerson());
    }

    private void setupRecyclerView(View fragmentView, List<Person> people){
        RecyclerView personRecycleView = fragmentView.findViewById(R.id.personRecyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        personRecycleView.setLayoutManager(layoutManager);

        personRecyclerViewAdapter = new PersonRecyclerViewAdapter(people,((view, position, person) -> openPersonDetails(person)));
        personRecycleView.setAdapter(personRecyclerViewAdapter);
    }



    private void openPersonDetails(Person person){
        Intent intent = new Intent(getActivity(), PersonDetailsActivity.class);
        intent.putExtra(PersonDetailsActivity.PERSON_ID,person.getId());
        startActivity(intent);
    }

}

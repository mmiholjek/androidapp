package com.mobileapps.myphonebook.screen.persondetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.mobileapps.myphonebook.R;
import com.mobileapps.myphonebook.model.Person;
import com.mobileapps.myphonebook.screen.home.fragment.PersonRVFragment;
import com.mobileapps.myphonebook.util.FragmentUtil;

public class PersonDetailsFragment extends Fragment {

    public static final String PERSON_ID = "PERSON_ID";

    public static PersonDetailsFragment newInstance(long personId){
        PersonDetailsFragment fragment = new PersonDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(PERSON_ID,personId);
        fragment.setArguments(bundle);
        return fragment;
    }

    private PersonDetailsViewModel personDetailsViewModel;
    private long personId;

    private PersonDetailsFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        personDetailsViewModel = ViewModelProviders.of(this).get(PersonDetailsViewModel.class);
        if (getArguments() != null){
            personId = getArguments().getLong(PERSON_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_person_details,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        personDetailsViewModel.getPersonId(personId).observe(this,person -> setupView(view,person));
    }

    private void setupView(View view, Person person) {
        CardView personRecyclerViewItem = view.findViewById(R.id.personRecyclerViewItem);
        personRecyclerViewItem.setOnClickListener(v -> replaceWithPersonRVFragment());

        TextView nameTextView = view.findViewById(R.id.nameTextView);
        TextView surnameTextView = view.findViewById(R.id.surnameTextView);
        ImageView personImageView = view.findViewById(R.id.personImageView);

        nameTextView.setText(person.getName());
        surnameTextView.setText(person.getSurname());


        Glide.with(this)
                .load(person.getImageUrl())
                .placeholder(R.drawable.logo)
                .into(personImageView);

    }

    private void replaceWithPersonRVFragment(){
        FragmentUtil.replaceFragment(getFragmentManager(),R.id.fragmentContainer, new PersonRVFragment());
    }
}

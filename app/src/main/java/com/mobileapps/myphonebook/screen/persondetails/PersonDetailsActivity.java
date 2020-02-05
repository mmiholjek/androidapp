package com.mobileapps.myphonebook.screen.persondetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.mobileapps.myphonebook.R;
import com.mobileapps.myphonebook.network.data.Person;

import java.util.UUID;


public class PersonDetailsActivity extends AppCompatActivity {

    public static final String PERSON_ID = "PERSON_ID";

    public static Intent with(Context context, UUID id) {
        Intent intent = new Intent(context, PersonDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(PERSON_ID, id);
        intent.putExtras(bundle);
        return intent;
    }

    private UUID personId;
    private PersonDetailsViewModel personDetailsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_person_details);

        personDetailsViewModel = ViewModelProviders.of(this).get(PersonDetailsViewModel.class);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }

        personId = (UUID) getIntent().getExtras().getSerializable(PERSON_ID);
        Person person = personDetailsViewModel.getPersonById(personId);
        setupViews(person);
    }

    private void setupViews(Person person) {
        TextView nameTextView = findViewById(R.id.nameTextView);
        TextView surnameTextView = findViewById(R.id.surnameTextView);
        TextView countryTextView = findViewById(R.id.countryTextView);
        TextView emailTextView = findViewById(R.id.emailTextView);
        TextView gsmTextView = findViewById(R.id.gsmTextView);
        ImageView personImageView = findViewById(R.id.personImageView);
        nameTextView.setText(person.getName());
        surnameTextView.setText(person.getSurname());
        countryTextView.setText(person.getRegion());
        emailTextView.setText(person.getEmail());
        gsmTextView.setText(person.getPhone());
        Glide.with(this)
                .load(person.getPhoto())
                .placeholder(R.drawable.logo)
                .into(personImageView);

    }
}

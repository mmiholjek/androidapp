package com.mobileapps.myphonebook.screen.persondetails;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mobileapps.myphonebook.R;
import com.mobileapps.myphonebook.util.FragmentUtil;


public class PersonDetailsActivity extends AppCompatActivity {

    public static final String PERSON_ID = "PERSON_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_person_details);
        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }

        long persorId = getIntent().getExtras().getLong(PERSON_ID);
        FragmentUtil.attachFragment(getSupportFragmentManager(), R.id.fragmentContainer,PersonDetailsFragment.newInstance(persorId));
    }
}

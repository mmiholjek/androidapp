package com.mobileapps.myphonebook.screen.home;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.mobileapps.myphonebook.R;
import com.mobileapps.myphonebook.screen.home.fragment.PersonRVFragment;
import com.mobileapps.myphonebook.util.FragmentUtil;


public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FragmentUtil.attachFragment(getSupportFragmentManager(),R.id.fragmentContainer, new PersonRVFragment());
    }
}

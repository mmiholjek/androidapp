package com.mobileapps.myphonebook.interactors;

import androidx.lifecycle.MutableLiveData;

import com.mobileapps.myphonebook.model.User;

public interface AuthInteractor {
    MutableLiveData<User> login(String email, String password);
}

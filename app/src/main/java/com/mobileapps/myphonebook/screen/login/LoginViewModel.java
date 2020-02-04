package com.mobileapps.myphonebook.screen.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mobileapps.myphonebook.interactors.AuthInteractor;
import com.mobileapps.myphonebook.interactors.AuthInteractorMockImpl;
import com.mobileapps.myphonebook.model.User;


public class LoginViewModel extends ViewModel {

    private AuthInteractor authInteractor = new AuthInteractorMockImpl();

    public MutableLiveData<User> loginUser(String username, String password) {
        return authInteractor.login(username, password);
    }

}

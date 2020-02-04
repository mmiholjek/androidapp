package com.mobileapps.myphonebook.interactors;

import androidx.lifecycle.MutableLiveData;

import com.mobileapps.myphonebook.config.Mock;
import com.mobileapps.myphonebook.model.User;

public class AuthInteractorMockImpl implements AuthInteractor {
    private MutableLiveData<User> liveDataUser;

    public AuthInteractorMockImpl() {
        liveDataUser = new MutableLiveData<>();
    }

    public MutableLiveData<User> login(String email, String password) {
        if (email.equals(Mock.Credentials.EMAIL) && password.equals(Mock.Credentials.PASSWORD)) {
            liveDataUser.setValue(Mock.USER);
            return liveDataUser;
        }
        liveDataUser.setValue(null);
        return liveDataUser;
    }

}

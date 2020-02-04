package com.mobileapps.myphonebook.config;

import com.mobileapps.myphonebook.model.User;

public class Mock {
    public class Credentials {
        public static final String EMAIL = "test@app.com";
        public static final String PASSWORD = "password";
        public static final String TOKEN = "EvNElvTOZvi1fnczpuVR77dQT3GlKkjtaRSFZmEo";
    }

    public static final User USER = new User(Credentials.EMAIL, Credentials.TOKEN);

}

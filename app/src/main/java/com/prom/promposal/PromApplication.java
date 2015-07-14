package com.prom.promposal;

import android.app.Application;

import com.facebook.login.LoginBehavior;
import com.facebook.login.LoginManager;
import com.parse.Parse;
import com.parse.ParseCrashReporting;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

public class PromApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ParseCrashReporting.enable(this);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "7ldAPvaN6qn3g6JWMrqZmsYBngfDJZnIV4kIvGcE", "nPiDSMtCSkiusnVQTOoQfKHbgJHiM5bKZheH6S98");
        ParseUser.enableAutomaticUser();
        ParseUser.enableRevocableSessionInBackground();
        ParseFacebookUtils.initialize(this);
        LoginManager.getInstance().setLoginBehavior(LoginBehavior.SUPPRESS_SSO);
    }
}

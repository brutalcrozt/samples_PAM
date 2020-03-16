package com.example.daynightlanguage;

import android.app.Application;

public class InitApplication extends Application {
    private static InitApplication singleton = null;
    private boolean isNightModeEnabled = false;

    public static InitApplication getInstance(){
        if(singleton == null){
            singleton = new InitApplication();
        }

        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
    }

    public boolean isNightModeEnabled() {
        return isNightModeEnabled;
    }

    public void setNightModeEnabled(Boolean mode){
        this.isNightModeEnabled = mode;
    }
}

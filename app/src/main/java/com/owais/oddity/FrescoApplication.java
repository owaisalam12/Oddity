package com.owais.oddity;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

public class FrescoApplication extends Application {

    private static FrescoApplication instance;

    public static FrescoApplication getInstance() {
        return instance;
    }

    public static Context getContext(){
        return instance;
    }
    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
        Fresco.initialize(this);
    }
}

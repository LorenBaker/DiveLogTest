package com.lbconsulting.divelogtest;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;

import timber.log.Timber;

/**
 * This class initializes application wide resources
 */
public class DiveLogApp extends Application {

    public DiveLogApp() {
    }


    @Override
    public void onCreate() {
        super.onCreate();

        try {
            // Calls to setPersistenceEnabled() must be made before any other usage of FirebaseDatabase instance.
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            FirebaseDatabase.getInstance().setLogLevel(Logger.Level.DEBUG);
        } catch (Exception e) {
            Timber.w("onCreate(): Exception: %s.", e.getMessage());
        }

        // initiate Timber
        Timber.plant(new Timber.DebugTree() {
                         @Override
                         protected String createStackElementTag(StackTraceElement element) {
                             return super.createStackElementTag(element) + ":" + element.getLineNumber();
                         }
                     }
        );
        Timber.i("onCreate(): Timber initiated.");
    }
}

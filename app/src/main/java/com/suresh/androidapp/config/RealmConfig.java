package com.suresh.androidapp.config;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by suresh on 11/30/16.
 */

public class RealmConfig extends Application {

    @Override
    public void onCreate() {

        super.onCreate();
        Realm.init(getApplicationContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("contactApp.realm")
                .schemaVersion(Long.parseLong("1.1"))
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

    }
}

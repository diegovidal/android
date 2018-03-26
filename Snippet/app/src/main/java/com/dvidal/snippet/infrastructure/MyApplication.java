package com.dvidal.snippet.infrastructure;

import android.app.Application;

import com.dvidal.snippet.di.AppComponent;
import com.dvidal.snippet.di.AppModule;
import com.dvidal.snippet.di.DaggerAppComponent;
import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;
import io.realm.Realm;


/**
 * Created by diegovidal on 10/01/2018.
 */

public class MyApplication extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(
                                Stetho.defaultDumperPluginsProvider(this)
                        )
                        .enableWebKitInspector(
                                RealmInspectorModulesProvider
                                        .builder(this)
                                        .build()
                        )
                        .build());

        //Dagger
        component = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        component.inject(this);
    }

    public AppComponent getAppComponent() {
        return component;
    }
}

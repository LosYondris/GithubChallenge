package me.jansv.challenge.di;


import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import me.jansv.challenge.ChallengeApp;
import me.jansv.challenge.api.ApiModule;
import me.jansv.challenge.network.NetworkModule;

@Singleton
@Component(modules = {
        BindingModule.class,
        ApplicationModule.class,
        NetworkModule.class,
        ApiModule.class,
        AndroidInjectionModule.class
})
public interface AppComponent extends AndroidInjector<ChallengeApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder app(Application app);

        AppComponent build();
    }
}

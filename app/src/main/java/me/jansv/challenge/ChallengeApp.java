package me.jansv.challenge;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import me.jansv.challenge.di.DaggerAppComponent;

public class ChallengeApp  extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().app(this).build();
    }
}

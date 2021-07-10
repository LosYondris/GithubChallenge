package me.jansv.challenge.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import me.jansv.challenge.BuildConfig;
import me.jansv.challenge.util.schedulers.DefaultSchedulerProvider;
import me.jansv.challenge.util.schedulers.SchedulerProvider;

@Module
public abstract class ApplicationModule {

    @Binds
    abstract Context provideContext(Application app);

    @Singleton
    @Binds
    abstract SchedulerProvider schedulerProvider(DefaultSchedulerProvider provider);

    @Provides
    @Named("isDebug")
    public static Boolean provideIsDebug() {
        return BuildConfig.DEBUG;
    }
}

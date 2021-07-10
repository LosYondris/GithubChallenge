package me.jansv.challenge.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import me.jansv.challenge.ui.screens.repos.ReposActivity;
import me.jansv.challenge.ui.screens.users.UsersActivity;

@Module
public interface BindingModule {

    @ActivityScoped
    @ContributesAndroidInjector
    UsersActivity usersActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    ReposActivity reposActivity();
}

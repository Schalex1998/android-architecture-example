package io.alexanderschaefer.architectureexample.base;

import android.app.Application;

import javax.inject.Inject;

import io.alexanderschaefer.architectureexample.di.ActivityInjector;

public class ArchitectureExampleApplication extends Application {

    @Inject
    ActivityInjector activityInjector;

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
        component.inject(this);
    }

    public ActivityInjector getActivityInjector() {
        return activityInjector;
    }
}

package com.project.test.testwithasynctask.utils;

import android.app.Application;

import com.project.test.testwithasynctask.presentation.TestPresenter;

public class TestApplicationImpl extends Application {
    private static TestPresenter test = new TestPresenter();
    
    public static TestPresenter getTest() {
        return test;
    }
    
    @Override
    public void onCreate() {
        super.onCreate();
        test.setContext(this);
        test.startForegroundservice();
    }
}

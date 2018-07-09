package com.project.test.testwithasynctask.utils;

import android.app.Application;
import android.util.Log;

import com.project.test.testwithasynctask.data.TestAsyncTask;
import com.project.test.testwithasynctask.presentation.TestPresenter;

public class TestApplicationImpl extends Application {
    private static TestPresenter test = new TestPresenter();
//    private static TestAsyncTask asyncTask = new TestAsyncTask();
//    private static TestPresenter instance;
//    public static TestPresenter getPresenterInstance() {
//        if (instance == null) {
//            instance = new TestPresenter();
//        }
//        return instance;
//    }
//
    
    
    public static TestPresenter getTest() {
        return test;
    }
    
//    public static TestAsyncTask getAsyncTask() {
//        return asyncTask;
//    }
    
    @Override
    public void onCreate() {
        super.onCreate();
        test.setContext(this);
        test.startForegroundservice();
        Log.e("TEST", "0000TEST");
    }
}

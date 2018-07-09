package com.project.test.testwithasynctask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.project.test.testwithasynctask.presentation.TestPresenter;
import com.project.test.testwithasynctask.utils.TestApplicationImpl;

public class MainActivity extends AppCompatActivity {
    
    TestPresenter presenter;
    TextView notificationMessage;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationMessage = (TextView)findViewById(R.id.subjectOfNotification);
//        presenter = TestApplicationImpl.getPresenterInstance();
//        presenter.setContext(this);
    }
    
    @Override
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        notificationMessage.setText(intent.getStringExtra("text"));
    }
}

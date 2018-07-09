package com.project.test.testwithasynctask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    
    TextView notificationMessage;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationMessage = (TextView) findViewById(R.id.subjectOfNotification);
    }
    
    @Override
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        notificationMessage.setText(intent.getStringExtra("text"));
    }
}

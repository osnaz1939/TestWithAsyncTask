package com.project.test.testwithasynctask.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.project.test.testwithasynctask.MainActivity;
import com.project.test.testwithasynctask.data.NotificationModel;

import java.util.List;

import static android.support.v4.app.NotificationCompat.*;

public class TestService extends Service {
    
    TestBinder binder = new TestBinder();
    private NotificationManager notificationManager;
    
    @Nullable
    @Override
    public IBinder onBind(Intent arg0) {
        return binder;
    }
    
    @Override
    public void onCreate() {
        super.onCreate();
        startForeground(0, null);
    }
    
    public void updateNotification(List<NotificationModel> notificationsList) {
        Log.e("TEST", "TEST");
        notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
        int i = 0;

        for (NotificationModel model : notificationsList) {

            Notification builder =
                    new Builder(this)
                            .setSmallIcon(android.R.drawable.ic_dialog_email)
                            .setContentTitle(model.getSubject())
                            .setContentText(model.getText())
                            .setAutoCancel(true)
                            .setContentIntent(createPendingIntent(model.getText(), i))
                            .getNotification();
            notificationManager.notify(i, builder);
            i++;
        }

    }
    
    private PendingIntent createPendingIntent(String message, int number) {
        Intent resultIntent = new Intent(this, MainActivity.class);
        resultIntent.putExtra("text", message);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent resultPendingIntent = PendingIntent
                .getActivity(this, number, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        return resultPendingIntent;
    }
    
    public class TestBinder extends Binder {
        public TestService getService() {
            return TestService.this;
        }
    }
}

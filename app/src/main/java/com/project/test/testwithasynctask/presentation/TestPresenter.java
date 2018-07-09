package com.project.test.testwithasynctask.presentation;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;

import com.project.test.testwithasynctask.data.NotificationModel;
import com.project.test.testwithasynctask.data.TestAsyncTask;
import com.project.test.testwithasynctask.utils.TestService;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TestPresenter {
    private TestAsyncTask asyncTask;
    private Context ctx;
    private TestService testService;
    private Timer timer;
    private TimerTask tTask;
    long interval = 120000;
    private final static String url = "https://disk.tsft.ru/vtb";
    private List<NotificationModel> notifyFilterList = new ArrayList<NotificationModel>();
    
    public void setContext(final Context ctx) {
        this.ctx = ctx;
    }
    
    public void startForegroundservice() {
        Intent startIntent = new Intent(ctx, TestService.class);
        ctx.startService(startIntent);
        ServiceConnection serviceConnection = new ServiceConnection() {
            
            public void onServiceConnected(ComponentName name, IBinder binder) {
                testService = ((TestService.TestBinder) binder).getService();
                startTimer();
            }
            
            public void onServiceDisconnected(ComponentName name) {
            }
        };
        
        ctx.bindService(startIntent, serviceConnection, 0);
    }
    
    private void startTimer() {
        timer = new Timer();
        schedule();
    }
    
    public void startServ(List<NotificationModel> notifyList) {
        notifyFilterList.clear();
        for (NotificationModel filterModel : notifyList) {
            if (filterModel.getStartDateTime().getTime() < System.currentTimeMillis()
                    && filterModel.getEndDateTime().getTime() > System.currentTimeMillis()) {
                notifyFilterList.add(filterModel);
            }
        }
        testService.updateNotification(notifyFilterList);
    }
    
    void schedule() {
        if (tTask != null) tTask.cancel();
        
        tTask = new TimerTask() {
            public void run() {
                startAsyncTask();
            }
        };
        timer.schedule(tTask, 120000, interval);
    }
    
    private void startAsyncTask() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if (asyncTask != null) {
                    asyncTask.cancel(false);
                    asyncTask = null;
                }
                asyncTask = new TestAsyncTask();
                asyncTask.execute(url);
            }
        });
    }
}

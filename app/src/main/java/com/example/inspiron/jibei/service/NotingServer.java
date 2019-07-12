package com.example.inspiron.jibei.service;

import android.app.*;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.example.inspiron.jibei.R;
import com.example.inspiron.jibei.ui.AddBillActivity;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class NotingServer extends Service {
    private static final String TAG = "NotingServer";
    private  int hour;
    private int minute;
    private String content;

    private static Timer timer;

    public NotingServer() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if(null!=timer){
            timer.cancel();
            timer=null;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
       // startRemind();
        myTimer();
        return super.onStartCommand(intent, flags, startId);
    }

    public void myTimer() {
        new Thread(new Runnable() {
            @Override
            public void run() {


            }
        }).start();//开启线程
    }

    public void noting() {
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        timer.cancel();
        super.onDestroy();
    }

    public void startRemind(){

        SharedPreferences pref=getSharedPreferences("noteDate",MODE_PRIVATE);

        hour= Integer.parseInt(pref.getString("currentHour","21"));
        minute= Integer.parseInt(pref.getString("currentMinute","00"));
        content=pref.getString("currentNoteContent","记呗");
        if(content.equals("")){
            content="记呗";
        }

        long period = 24*60*60*1000; //24小时一个周期
        Calendar calendar= Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(Calendar.MINUTE,minute);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        Date delay=calendar.getTime();

        if (null == timer ) {
            timer = new Timer();
        }

        Log.d(TAG, "startRemind: content"+hour+minute+"  "+content);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //设置点击跳转
                Intent notificationIntent=new Intent(NotingServer.this, AddBillActivity.class);
                PendingIntent pendingIntent= PendingIntent.getActivity(NotingServer.this,0,notificationIntent,0);

                NotificationManager manager= (NotificationManager)NotingServer.this.getSystemService(NOTIFICATION_SERVICE);
                if(Build.VERSION.SDK_INT >= 26) {
                    //当sdk版本大于26
                    String id = "channel_1";
                    String description = "143";
                    int importance = NotificationManager.IMPORTANCE_LOW;
                    NotificationChannel channel = new NotificationChannel(id, description, importance);
                    manager.createNotificationChannel(channel);

                    Notification notification = new NotificationCompat.Builder(NotingServer.this,id)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setTicker("tick") //测试通知栏标题
                            .setContentText(content) //下拉通知啦内容
                            .setContentTitle("JiBei's Notification!!!")//下拉通知栏标题
                            .setAutoCancel(true)
                            .setDefaults(Notification.DEFAULT_ALL)
                            .setContentIntent(pendingIntent)
                            .build();

                    manager.notify((int) System.currentTimeMillis(), notification);
                }
                else {
                    Notification notification = new NotificationCompat.Builder(NotingServer.this)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setTicker("tick") //测试通知栏标题
                            .setContentText("JiBei's Notification!!!") //下拉通知啦内容
                            .setContentTitle(content)//下拉通知栏标题
                            .setAutoCancel(true)
                            .setDefaults(Notification.DEFAULT_ALL)
                            .setContentIntent(pendingIntent)
                            .build();

                    manager.notify((int) System.currentTimeMillis(), notification);
                }

            }
        },delay, period);
    }

}

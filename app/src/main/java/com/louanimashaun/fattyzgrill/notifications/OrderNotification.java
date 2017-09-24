package com.louanimashaun.fattyzgrill.notifications;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.louanimashaun.fattyzgrill.R;
import com.louanimashaun.fattyzgrill.util.Util;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by louanimashaun on 25/08/2017.
 */

public class OrderNotification {

    private static Context mConext;
    private static OrderNotification INSTANCE ;


    public static OrderNotification getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = new OrderNotification(context);
        }
        return  INSTANCE;
    }

    private OrderNotification(Context context){
        mConext = context;
    }



    public void createNotification(){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(Util.getApp())
                .setSmallIcon(R.drawable.logof)
                .setContentTitle("New Order")
                .setContentText("hello ");

        //TODO find the difference betwteen notificationManager and notificationManagerCompat
        NotificationManager notificationManager = (NotificationManager) mConext
                .getSystemService(NOTIFICATION_SERVICE);

        int notificationID = 0;
        notificationManager.notify(0, mBuilder.build());
    }
}
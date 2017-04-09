package com.group4.joseph.timetablenotifier;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import java.util.Calendar;

/*public class NotificationReceiver extends BroadcastReceiver {
    //int notify_id = 100;
    //MainActivity obj = new MainActivity();
    //int id = obj.notify_id;

    //int id = (int)System.currentTimeMillis();

    @Override
    public void onReceive(Context context, Intent intent) {     //onReceive will always get called when the class ges triggered
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE); // provides an builder interface to create an Notification object.
        Intent intent1 = new Intent(context, MainActivity.class);    //When the notification is pressed, it will open ReceivingNotification
        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);   //FLAG..TOP means that if MainActivity is already open when the notification is pressed, it will close it and freshly open it again
        //String text_notif = intent.getStringExtra(Monday.MONDAY_KEY);
        //if we want ring on notifcation then uncomment below line//
        //Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        //Pending intent specifies action performed when user clicks notifcation
    //(int) (Math.random()* 100

        String s = intent.getStringExtra("MODULE");
        String t = intent.getStringExtra("LOCATION");
        PendingIntent pendingIntent = PendingIntent.getActivity(context,100,intent1,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context).

                setContentIntent(pendingIntent).
                setSmallIcon(R.drawable.ic_launcher).
                setContentText("You have Module "+s+"in Theatre "+t).
                setContentTitle("My notificaton - id " + String.valueOf(100)).
                addAction(R.drawable.ic_launcher, "Next module", null).
                //setSound(alarmSound).
                        setAutoCancel(true);    //This makes the notification dismissable when the user hits it away


        notificationManager.notify(100,builder.build());

        //notify_id++;

    }
*/
public class NotificationReceiver extends BroadcastReceiver {
//int notify_id = 100;
//MainActivity obj = new MainActivity();
//int id = obj.notify_id;

//int id = (int)System.currentTimeMillis();

    @Override
    public void onReceive(Context context, Intent intent) {     //onReceive will always get called when the class ges triggered
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE); // provides an builder interface to create an Notification object.
        Intent intent1 = new Intent(context, MainActivity.class);    //When the notification is pressed, it will open ReceivingNotification
        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);   //FLAG..TOP means that if MainActivity is already open when the notification is pressed, it will close it and freshly open it again
        //String text_notif = intent.getStringExtra(Monday.MONDAY_KEY);
        //if we want ring on notifcation then uncomment below line//
        //Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        //Pending intent specifies action performed when user clicks notifcation

        int n = intent.getIntExtra("NOTIFY_ID",0);
        String s = intent.getStringExtra("MODULE");
        String t = intent.getStringExtra("LOCATION");
        PendingIntent pendingIntent = PendingIntent.getActivity(context, n, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context).

                setContentIntent(pendingIntent).
                setSmallIcon(R.drawable.ic_launcher).
                setContentText("You have Module " + s + " in Theatre " + t).
                setContentTitle("My notificaton - id " + String.valueOf(n)).
                addAction(R.drawable.ic_launcher, "Next module", null).
                //setSound(alarmSound).
                        setAutoCancel(true);    //This makes the notification dismissable when the user hits it away


        notificationManager.notify(100, builder.build());

    }

}
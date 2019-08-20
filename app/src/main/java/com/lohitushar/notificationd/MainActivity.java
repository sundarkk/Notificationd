package com.lohitushar.notificationd;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;

import java.net.URI;
import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button btn_login;
    private String NOTIFICATION_TITLE = "Notification Sample App";
    private String CONTENT_TEXT = "Expand me to see a detailed message!";

    EditText mEditText;
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // sendNotification();
                //RemoteViews expandedView = new RemoteViews(getPackageName(), R.layout.view_expanded_notification);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
                builder.setSmallIcon(android.R.drawable.ic_dialog_alert);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
                builder.setContentIntent(pendingIntent);
                builder.setContentTitle("Notification");
                builder.setContentText("Your notification");
                builder.setSubText("this is sub message");
               // builder.setCustomContentView(expandedView);

                Notification notification=builder.build();

                RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.notification);

                // Set text on a TextView in the RemoteViews programmatically.
                final String time = DateFormat.getTimeInstance().format(new Date()).toString();
                //final String text = getResources().getString(R.string.collapsed, time);
               // contentView.setTextViewText(R.id.textView, text);

                /* Workaround: Need to set the content view here directly on the notification.
                 * NotificationCompatBuilder contains a bug that prevents this from working on platform
                 * versions HoneyComb.
                 * See https://code.google.com/p/android/issues/detail?id=30495
                 */
                notification.contentView = contentView;

                // Add a big content view to the notification if supported.
                // Support for expanded notifications was added in API level 16.
                // (The normal contentView is shown when the notification is collapsed, when expanded the
                // big content view set here is displayed.)
                if (Build.VERSION.SDK_INT >= 16) {
                    // Inflate and set the layout for the expanded notification view
                    RemoteViews expandedView =
                            new RemoteViews(getPackageName(), R.layout.notification_expanded);
                    notification.bigContentView = expandedView;
                }
                // END_INCLUDE(customLayout)

                // START_INCLUDE(notify)
                // Use the NotificationManager to show the notification
                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                nm.notify(0, notification);
            }
        });
    }

   /* private void sendNotification() {
        RemoteViews expandedView = new RemoteViews(getPackageName(), R.layout.view_expanded_notification);
        //expandedView.setTextViewText(R.id.timestamp, DateUtils.formatDateTime(this, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME));
        //expandedView.setTextViewText(R.id.notification_message, mEditText.getText());
        // adding action to left button
        Intent leftIntent = new Intent(getApplicationContext(), MediaStore.ACTION_VIDEO_CAPTURE);

        // expandedView.setOnClickPendingIntent(R.id.left_button, PendingIntent.getService(this, 0, leftIntent, PendingIntent.FLAG_UPDATE_CURRENT));
        // adding action to right button
        *//*Intent rightIntent = new Intent(MainActivity.this, MediaStore.ACTION_VIDEO_CAPTURE);
        rightIntent.setAction("right");*//*
        // expandedView.setOnClickPendingIntent(R.id.right_button, PendingIntent.getService(this, 1, rightIntent, PendingIntent.FLAG_UPDATE_CURRENT));

        *//*RemoteViews collapsedView = new RemoteViews(getPackageName(), R.layout.view_collapsed_notification);
        collapsedView.setTextViewText(R.id.timestamp, DateUtils.formatDateTime(this, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME));
*//*
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                // these are the three things a NotificationCompat.Builder object requires at a minimum
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(NOTIFICATION_TITLE)
                .setContentText(CONTENT_TEXT)
                // notification will be dismissed when tapped
                .setAutoCancel(true)
                // tapping notification will open MainActivity
                .setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0))
                // setting the custom collapsed and expanded views
                .setCustomContentView(expandedView)
                .setCustomBigContentView(expandedView);

        // retrieves android.app.NotificationManager
        NotificationManager notificationManager = (android.app.NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }*/
}
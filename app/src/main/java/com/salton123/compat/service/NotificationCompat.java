package com.salton123.compat.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.JELLY_BEAN;
import static android.os.Build.VERSION_CODES.O;

/**
 * User: newSalton@outlook.com
 * Date: 2019/1/30 11:10
 * ModifyTime: 11:10
 * Description:
 */
public class NotificationCompat {
    private static final String NOTIFICATION_CHANNEL_ID = "compat_notification_channel_id";
    private static final String NOTIFICATION_CHANNEL_NAME = "compat_notification_channel_name";

    public static void showNotification(Context context, CharSequence contentTitle,
                                        CharSequence contentText, PendingIntent pendingIntent, int notificationId) {
        Notification.Builder builder = new Notification.Builder(context)
                .setContentText(contentText)
                .setContentTitle(contentTitle)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);
        Notification notification = buildNotification(context, builder);
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, notification);
    }

    public static Notification buildNotification(Context context,
                                                 Notification.Builder builder) {
        builder.setWhen(System.currentTimeMillis())
                .setOnlyAlertOnce(true);

        if (SDK_INT >= O) {
            NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel notificationChannel =
                    notificationManager.getNotificationChannel(NOTIFICATION_CHANNEL_ID);
            if (notificationChannel == null) {
                String channelName = NOTIFICATION_CHANNEL_NAME;
                notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName,
                        NotificationManager.IMPORTANCE_DEFAULT);
                notificationManager.createNotificationChannel(notificationChannel);
            }
            builder.setChannelId(NOTIFICATION_CHANNEL_ID);
        }

        if (SDK_INT < JELLY_BEAN) {
            return builder.getNotification();
        } else {
            return builder.build();
        }
    }
}

package com.salton123.compat.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.JELLY_BEAN;
import static android.os.Build.VERSION_CODES.O;

/**
 * User: newSalton@outlook.com
 * Date: 2019/4/17 13:43
 * ModifyTime: 13:43
 * Description:
 */
public class ForegroundDelegate {

    public static void startService(Context context, Intent intent) {
        ContextCompat.startForegroundService(context, intent);
    }

    private static final String NOTIFICATION_CHANNEL_ID = "compat_notification_channel_id";
    private static final String NOTIFICATION_CHANNEL_NAME = "compat_notification_channel_name";
    public Service mHost;
    public Notification mNotification;
    public int mNotificationId;

    public ForegroundDelegate(Builder builder) {
        mHost = builder.service;
        mNotificationId = builder.notificationId;
        mNotification = builder.build();
    }

    public static class Builder {
        private String contentTitle;
        private String contentText;
        private int smallIconRes;
        private int maxProgress;
        private int currentProgress;
        private boolean indeterminate;
        private int notificationId = ForegroundDelegate.class.hashCode();
        private Service service;

        public Builder(Service service) {
            this.service = service;
        }

        public Builder title(String contentTitle) {
            this.contentTitle = contentTitle;
            return this;
        }

        public Builder text(String contentText) {
            this.contentText = contentText;
            return this;
        }

        public Builder icon(int smallIconRes) {
            this.smallIconRes = smallIconRes;
            return this;
        }

        public Builder max(int maxProgress) {
            this.maxProgress = maxProgress;
            return this;
        }

        public Builder current(int currentProgress) {
            this.currentProgress = currentProgress;
            return this;
        }

        public Builder indeterminate(boolean indeterminate) {
            this.indeterminate = indeterminate;
            return this;
        }


        public Builder id(int notificationId) {
            this.notificationId = notificationId;
            return this;
        }

        public Notification build() {
            Notification.Builder builder = new Notification.Builder(service);
            builder.setContentTitle(contentTitle)
                    .setContentText(contentText)
                    .setSmallIcon(smallIconRes)
                    .setProgress(maxProgress, currentProgress, indeterminate);
            builder.setWhen(System.currentTimeMillis())
                    .setOnlyAlertOnce(true);
            if (SDK_INT >= O) {
                NotificationManager notificationManager =
                        (NotificationManager) service.getSystemService(Context.NOTIFICATION_SERVICE);
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

    public ForegroundDelegate onForeground() {
        mHost.startForeground(mNotificationId, mNotification);
        return this;
    }

    public ForegroundDelegate onBackground() {
        mHost.stopForeground(true);
        return this;
    }


}

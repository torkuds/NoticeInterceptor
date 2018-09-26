package com.torkuds.noticeinterceptor;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

@SuppressLint("NewApi")
public class NotificationInterceptorService extends NotificationListenerService {

    // 在收到消息时触发
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        // TODO Auto-generated method stub
        Bundle extras = sbn.getNotification().extras;
        long postTime = sbn.getPostTime();
        // 获取接收消息APP的包名
        String notificationPkg = sbn.getPackageName();
        // 获取接收消息的抬头
        String notificationTitle = extras.getString(Notification.EXTRA_TITLE);
        // 获取接收消息的内容
        String notificationText = extras.getString(Notification.EXTRA_TEXT);
        String conversitionTitle = extras.getString(Notification.EXTRA_CONVERSATION_TITLE);
        String infoText = extras.getString(Notification.EXTRA_INFO_TEXT);
        String messages = extras.getString(Notification.EXTRA_MESSAGES);
        String people = extras.getString(Notification.EXTRA_PEOPLE);
        int progress = extras.getInt(Notification.EXTRA_PROGRESS);
        String self_display_name = extras.getString(Notification.EXTRA_SELF_DISPLAY_NAME);
        boolean show_when = extras.getBoolean(Notification.EXTRA_SHOW_WHEN);
        String sub_text = extras.getString(Notification.EXTRA_SUB_TEXT);
        Log.i("通知监听", "时间：" + postTime + "，包名：" + notificationPkg + "，标题：" + notificationTitle + "，内容：" + notificationText);
        Log.i("通知监听", "1：" + conversitionTitle + "，2：" + infoText + "，3：" + messages + "，4：" + people);
        Log.i("通知监听", "5：" + progress + "，6：" + self_display_name + "，7：" + show_when + "，8：" + sub_text);
    }

    // 在删除消息时触发
    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        // TODO Auto-generated method stub
        Bundle extras = sbn.getNotification().extras;
        // 获取接收消息APP的包名
        String notificationPkg = sbn.getPackageName();
        // 获取接收消息的抬头
        String notificationTitle = extras.getString(Notification.EXTRA_TITLE);
        // 获取接收消息的内容
        String notificationText = extras.getString(Notification.EXTRA_TEXT);
        Log.i("通知监听", "Notification removed " + notificationTitle + " & " + notificationText);
    }

}

package com.torkuds.noticeinterceptor;

import android.content.ComponentName;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(MainActivity.this, NotificationInterceptorService.class);

        // 通知栏监控器开关
        Button notificationMonitorOnBtn = (Button)findViewById(R.id.notification_monitor_on_btn);
        notificationMonitorOnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                if (!isEnabled()) {
                    startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "监控器开关已打开", Toast.LENGTH_SHORT);
                    toast.show();
//                    startService(intent);
                }
            }
        });

        Button notificationMonitorOffBtn = (Button)findViewById(R.id.notification_monitor_off_btn);
        notificationMonitorOffBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                if (isEnabled()) {
                    startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "监控器开关已关闭", Toast.LENGTH_SHORT);
                    toast.show();
//                    stopService(intent);
                }
            }
        });
    }

    // 判断是否打开了通知监听权限
    private boolean isEnabled() {
        String pkgName = getPackageName();
        final String flat = Settings.Secure.getString(getContentResolver(), "enabled_notification_listeners");
        if (!TextUtils.isEmpty(flat)) {
            final String[] names = flat.split(":");
            for (int i = 0; i < names.length; i++) {
                final ComponentName cn = ComponentName.unflattenFromString(names[i]);
                if (cn != null) {
                    if (TextUtils.equals(pkgName, cn.getPackageName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

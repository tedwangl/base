package com.wl.mylibrary.util.network;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.wl.mylibrary.util.L;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 网络监听Service
 * Created by sks on 2016/4/9.
 */
public class NetWorkStateService extends Service {

    //private LocalBroadcastManager manager;

    private GetConnectState onGetConnectState;

    private boolean isContected = true;

    private Binder binder = new NetBinder();

    public class NetBinder extends Binder {

        public NetWorkStateService getService() {
            return NetWorkStateService.this;
        }

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化网络状态
        NetWorkUtils.initNetStatus(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        //manager = LocalBroadcastManager.getInstance(this);
        registerReceiver(mReceiver, intentFilter);
        L.e("-----网络状态监听开始------");
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(mReceiver);
        L.e("-----网络状态监听结束------");
        super.onDestroy();
    }

    BroadcastReceiver mReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            //判断wifi是否打开
            if(WifiManager.WIFI_STATE_CHANGED_ACTION.equals(action)){
                int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,0);
                switch (wifiState){
                    case WifiManager.WIFI_STATE_DISABLED:
                        break;
                    case WifiManager.WIFI_STATE_DISABLING:
                        break;
                }
            }
            //如果是在开启wifi连接和有网络状态下
            if(ConnectivityManager.CONNECTIVITY_ACTION.equals(action)){
                NetWorkUtils.initNetStatus(context);
                Timer timer = new Timer();
                timer.schedule(new NetStateTimerTask(getApplicationContext()),new Date());
            }
        }
    };

    public interface GetConnectState {
        // 网络状态改变之后，通过此接口的实例通知当前网络的状态，此接口在Activity中注入实例对象
        public void GetState(boolean isConnected);
    }

    public void setOnGetConnectState(GetConnectState onGetConnectState) {
        this.onGetConnectState = onGetConnectState;
    }

    class NetStateTimerTask extends TimerTask {

        private Context context;

        public NetStateTimerTask(Context context) {
            this.context = context;
        }

        @Override
        public void run() {
            if (NetWorkUtils.isNetworkConnected(context) || NetWorkUtils.isWifiConnected(context)) {
                isContected = true;
            } else {
                isContected = false;
            }
            if (onGetConnectState != null) {
                onGetConnectState.GetState(isContected); // 通知网络状态改变
                Log.i("mylog", "通知网络状态改变:" + isContected);
            }
        }

    }


}

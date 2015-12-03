package com.codekun.androidfirstcode.ch5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.codekun.androidfirstcode.R;
import com.codekun.androidfirstcode.core.TitleBarActivity;
import com.codekun.androidfirstcode.telephony.CKTelephonyManager;

/**
 * 1。实现网络类型变化监听
 * 2。实现移动网络类型判断，如2G,3G,4G
 * 3。实现网格信号强度获取
 */
public class Ch5NetworkChangeActivity extends TitleBarActivity {

    private NetworkChangeReceiver mNetworkChangeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");

        mNetworkChangeReceiver = new NetworkChangeReceiver();

        //====注意添加访问网络的权限
        //动态注册监听网络变化广播
        registerReceiver(mNetworkChangeReceiver, filter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mNetworkChangeReceiver);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_ch5_network_change;
    }

    /**
     * 定义监听网络变化
     */
    class NetworkChangeReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (info != null && info.isAvailable()){
                String type = "";
                switch (info.getType()){
                    case ConnectivityManager.TYPE_MOBILE:
                        type = "移动网络";
                        String netClass = "";
                        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
                        int networkType = telephonyManager.getNetworkType();
                        //监听信号强度
                        telephonyManager.listen(new PhoneStateListener(){
                            @Override
                            public void onSignalStrengthsChanged(SignalStrength signalStrength) {
                                super.onSignalStrengthsChanged(signalStrength);
                                Log.d("AndroidFirstCode", "网络信号:" +  signalStrength.getGsmSignalStrength());
                            }
                        }, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
                        //还可以获取各种手机信息
                        switch (CKTelephonyManager.getNetworkClass(networkType)){
                            case CKTelephonyManager.NETWORK_CLASS_2_G:
                                netClass = " 2G";
                                break;
                            case CKTelephonyManager.NETWORK_CLASS_3_G:
                                netClass = " 3G";
                                break;
                            case CKTelephonyManager.NETWORK_CLASS_4_G:
                                netClass = " 4G";
                                break;
                            case CKTelephonyManager.NETWORK_CLASS_UNKNOWN:
                                netClass = " 未知的网络类型";
                                break;
                        }
                        type += netClass;
                        break;
                    case ConnectivityManager.TYPE_WIFI:
                        type = "Wifi";
                        break;
                }
                Toast.makeText(context, "网络发现改变, 当前使用的网络：" + type, Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "网络已关闭", Toast.LENGTH_SHORT).show();
            }
            //Toast.makeText(context, "网络发现改变", Toast.LENGTH_SHORT).show();
        }
    }

}

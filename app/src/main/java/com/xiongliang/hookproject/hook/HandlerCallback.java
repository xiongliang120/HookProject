package com.xiongliang.hookproject.hook;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class HandlerCallback implements Handler.Callback {
    private Handler mHandler;

    public HandlerCallback(Handler handler){
        mHandler = handler;
    }

    @Override
    public boolean handleMessage(Message msg) {
        int id = msg.what;
        if(id == 100){
            Log.i("msg","启动Activity");
        }

        mHandler.handleMessage(msg);
        return true;
    }
}

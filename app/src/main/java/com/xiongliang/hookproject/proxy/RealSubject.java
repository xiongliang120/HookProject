package com.xiongliang.hookproject.proxy;

import android.util.Log;

public class RealSubject implements Subject {
    public void request(){
        Log.i("msg","真正执行类");
    }
}

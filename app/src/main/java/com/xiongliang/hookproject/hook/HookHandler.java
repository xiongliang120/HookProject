package com.xiongliang.hookproject.hook;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class HookHandler implements InvocationHandler {
    private Object mBase;

    public HookHandler(Object base){
        this.mBase = base;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.i("msg","method name="+method.getName());
        return method.invoke(mBase,args);
    }
}

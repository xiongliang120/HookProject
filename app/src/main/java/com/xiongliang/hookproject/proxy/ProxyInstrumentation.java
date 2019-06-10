package com.xiongliang.hookproject.proxy;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.xiongliang.hookproject.util.ReflectUtil;

public class ProxyInstrumentation  extends  Instrumentation{
    Instrumentation mBase;

    public ProxyInstrumentation(Instrumentation mInstrumentation){
        this.mBase = mInstrumentation;
    }


    public  Instrumentation.ActivityResult execStartActivity(Context context, IBinder contextThread, IBinder token, Activity target,
                                                             Intent intent, int requestCode, Bundle options){
        Log.i("msg","启动Activity");
        //由于execStartActivity是隐藏方法,因此需要通过反射调用
        Class[] paramType = {Context.class, IBinder.class,IBinder.class,Activity.class,Intent.class,int.class,Bundle.class};
        Object[] paramValue = {context,contextThread,token,target,intent,requestCode,options};
        return (ActivityResult) ReflectUtil.invokeInstanceMethod(mBase,"execStartActivity",paramType,paramValue);

    }
}

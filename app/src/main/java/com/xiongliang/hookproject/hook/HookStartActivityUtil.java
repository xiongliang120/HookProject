package com.xiongliang.hookproject.hook;

import android.app.Activity;
import android.app.Instrumentation;
import android.os.Handler;

import com.xiongliang.hookproject.proxy.ProxyInstrumentation;
import com.xiongliang.hookproject.util.ReflectUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

public class HookStartActivityUtil {
    /**
     *  Hook Activity中的mInstrumentation 变量
     */
    public static void hookInstrumentationFieldInActivity(Activity activity){
        try{
            Instrumentation instrumentation = (Instrumentation) ReflectUtil.getFieldObject("android.app.Activity",activity,"mInstrumentation");
            ProxyInstrumentation proxyInstrumentation = new ProxyInstrumentation(instrumentation);
            ReflectUtil.setFileObject("android.app.Activity",activity,"mInstrumentation",proxyInstrumentation);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     *  Hook ActivityManagerNative中的getDefault()hook
     */
    public static void hookGetDefaultMethodInActivityManagerNative(){
        try{
            Object gDefault = ReflectUtil.getStaticFieldObject("android.app.ActivityManagerNative","gDefault");
            Object  activityManager = ReflectUtil.getFieldObject("android.util.Singleton",gDefault,"mInstance");

            Class<?> activityManagerInterface = Class.forName("android.app.IActivityManager");
            Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                    new Class<?>[] {activityManagerInterface},
                    new HookHandler(activityManager));
            ReflectUtil.setFileObject("android.util.Singleton",gDefault,"mInstance",proxy);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * hook ActiivtyThread 中mH 变量的mCallback 变量
     */
    public static void hookActivityThreadmH(){
        try{
            Object currentActivityThread =ReflectUtil.getStaticFieldObject("android.app.ActivityThread","sCurrentActivityThread");

            Handler mHandler = (Handler) ReflectUtil.getFieldObject("android.app.ActivityThread",currentActivityThread,"mH");

            ReflectUtil.setFileObject(Handler.class,mHandler,"mCallback",new HandlerCallback(mHandler));

        }catch (Exception e){

        }
    }
}

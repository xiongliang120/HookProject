package com.xiongliang.hookproject.hook;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import com.xiongliang.hookproject.proxy.Subject;
import com.xiongliang.hookproject.util.ReflectUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *  ActivityManagerNative 在sdk 25及以下的模拟上才能hook 到
 */
public class HooUtil {
    public static void hookActivityManagerNative(){
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


    public static void hookPackageManager(Context context){
        try{
            Object currentActivityThread = ReflectUtil.getStaticFieldObject("android.app.ActivityThread","currentActivityThread");
            Object sPackageManager = ReflectUtil.getFieldObject("android.app.ActivityThread",currentActivityThread,"sPackageManager");

            Class<?> iPackageManagerInterface = Class.forName("android.content.pm.IPackageManager");
            Object proxy = Proxy.newProxyInstance(iPackageManagerInterface.getClassLoader(),new Class<?>[]{iPackageManagerInterface},
                    new HookHandler(sPackageManager));

            ReflectUtil.setFileObject("android.content.pm.IPackageManager",sPackageManager,"sPackageManager",proxy);
            PackageManager pm = context.getPackageManager();

            ReflectUtil.setFileObject("android.content.pm.IPackageManager",pm,"mPM",proxy);


        }catch (Exception e){
            e.printStackTrace();
        }
    }






}

package com.xiongliang.hookproject.proxy;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy {
       public void proxy(){
           Subject subject = new RealSubject();
           Subject subjectProxy = (Subject) Proxy.newProxyInstance(subject.getClass().getClassLoader(),
                   subject.getClass().getInterfaces(),
                   new InvocationHandlerImpl(subject));
           subjectProxy.request();
       }

       public class InvocationHandlerImpl implements InvocationHandler{
           private Subject target;
           public InvocationHandlerImpl(Subject subject){
               this.target = subject;
           }
           @Override
           public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
               Object obj = method.invoke(target,objects); //反射调用方法
               return obj;
           }
       }
}

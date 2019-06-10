package com.xiongliang.hookproject.proxy;

public class StaticProxy implements Subject {
    private RealSubject realSubject = new RealSubject();

    @Override
    public void request() {
        if(realSubject != null){
            realSubject.request();
        }
    }

}

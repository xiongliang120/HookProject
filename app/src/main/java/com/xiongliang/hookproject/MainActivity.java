package com.xiongliang.hookproject;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiongliang.hookproject.hook.HooUtil;
import com.xiongliang.hookproject.hook.HookStartActivityUtil;
import com.xiongliang.hookproject.proxy.DynamicProxy;
import com.xiongliang.hookproject.proxy.StaticProxy;

public class MainActivity extends AppCompatActivity {
    private Button staticProxy;
    private Button dynamicProxy;
    private Button hookActivityManagerNative;
    private Button hookPackageManager;
    private Button hoookmInstrumentation;
    private Button hookmHCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        staticProxy = (Button) findViewById(R.id.staticProxy);
        dynamicProxy = (Button) findViewById(R.id.dynamicProxy);
        hookActivityManagerNative = (Button) findViewById(R.id.hookActivityManagerNative);
        hookPackageManager = (Button) findViewById(R.id.hookPackageManager);
        hoookmInstrumentation = (Button) findViewById(R.id.mInstrumentationHook);
        hookmHCallback = (Button) findViewById(R.id.mHCallBackHook);

        staticProxy.setOnClickListener(clickListener);
        dynamicProxy.setOnClickListener(clickListener);
        hookActivityManagerNative.setOnClickListener(clickListener);
        hookPackageManager.setOnClickListener(clickListener);
        hoookmInstrumentation.setOnClickListener(clickListener);
        hookmHCallback.setOnClickListener(clickListener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id = view.getId();
            switch (id){
                case R.id.staticProxy:
                    testStaticProxy();
                    break;
                case R.id.dynamicProxy:
                    testDynamicProxy();
                    break;
                case R.id.hookActivityManagerNative:
                    testHookActivityManagerNativve();
                    break;
                case R.id.hookPackageManager:
                    testHookPackageManager();
                    break;
                case R.id.mInstrumentationHook:
                    testHookmInstrumentation();
                    break;
                case R.id.mHCallBackHook:
                    testHookmHCallback();
                    break;
                default:
                    break;
            }
        }
    };


    /***
     * 执行静态代理
     */
    public void testStaticProxy(){
        StaticProxy staticProxy = new StaticProxy();
        staticProxy.request();
    }


    /**
     * 执行动态代理
     */
    public void testDynamicProxy(){
        DynamicProxy dynamicProxy = new DynamicProxy();
        dynamicProxy.proxy();
    }


    /**
     *  hook ActivityManaggerNativve
     */
    public void testHookActivityManagerNativve(){
        HooUtil.hookActivityManagerNative();

        // 测试AMS HOOK (调用其相关方法)
        Uri uri = Uri.parse("http://wwww.baidu.com");
        Intent t = new Intent(Intent.ACTION_VIEW);
        t.setData(uri);
        startActivity(t);
    }

    /**
     * hook  PackageManager
     */
    public void testHookPackageManager(){
        HooUtil.hookPackageManager(this);
        getPackageManager().getInstalledApplications(0);
    }

    /**
     * 对Activity的mInstrumentation 变量进行hook
     */
    public void testHookmInstrumentation(){
        HookStartActivityUtil.hookInstrumentationFieldInActivity(this);
        Intent intent = new Intent(this,NextActivity.class);
        startActivity(intent);
    }

    /**
     * 对 Activity的mH hook 的mCallback 进行hook
     */
    public void testHookmHCallback(){
         HookStartActivityUtil.hookActivityThreadmH();
        Intent intent = new Intent(this,NextActivity.class);
        startActivity(intent);
    }
}

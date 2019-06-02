package com.xiongliang.hookproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button staticProxy;
    private Button dynamicProxy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        staticProxy = findViewById(R.id.staticProxy);
        dynamicProxy = findViewById(R.id.dynamicProxy);

        staticProxy.setOnClickListener(clickListener);
        dynamicProxy.setOnClickListener(clickListener);
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
}

package com.jiangkang.hack;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;


@Route(path = "/hack/HackActivity")
public class HackActivity extends AppCompatActivity {


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hack);
        ButterKnife.bind(this);


    }



    private void hookOnClickListener() {
        try {
            //getListenerInfo()
            Method getListenerInfoMethod = View.class.getDeclaredMethod("getListenerInfo");
            getListenerInfoMethod.setAccessible(true);

            //得到ListenerInfo
            Object listenerInfo = getListenerInfoMethod.invoke(View.class);

            //得到mOnClickListener对象
            Class<?> listenerInfoClz = Class.forName("android.view.View$ListenerInfo");
            Field mOnClickListener = listenerInfoClz.getDeclaredField("mOnClickListener");
            mOnClickListener.setAccessible(true);

            View.OnClickListener originOnClickListener = (View.OnClickListener) mOnClickListener.get(listenerInfo);

            View.OnClickListener hookedOnClickListener = new HookOnClickListener(originOnClickListener,this);

            mOnClickListener.set(listenerInfo,hookedOnClickListener);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}

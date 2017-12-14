package com.jiangkang.hack;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;


@Route(path = "/hack/HackActivity")
public class HackActivity extends AppCompatActivity {

//    @BindView(R2.id.btn_hook_OnClick)
//    Button mBtnHookOnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hack);
        ButterKnife.bind(this);
//        hookOnClickListener(mBtnHookOnClick);
    }

//    @OnClick(R.id.btn_hook_OnClick)
//    public void onBtnHookOnClicked(View view) {
//        KDialog.showMsgDialog(this,"点击了按钮");
//    }

    private void hookOnClickListener(View view) {
        try {
            //getListenerInfo()
            Method getListenerInfo = View.class.getDeclaredMethod("getListenerInfo");
            getListenerInfo.setAccessible(true);

            //得到ListenerInfo
            Object listenerInfo = getListenerInfo.invoke(view);

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

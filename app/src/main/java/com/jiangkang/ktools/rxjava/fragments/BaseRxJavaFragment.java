package com.jiangkang.ktools.rxjava.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;


import com.jiangkang.ktools.rxjava.LogAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseRxJavaFragment extends Fragment {

    public LogAdapter mAdapter;
    public List<String> mLogs;

    public BaseRxJavaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupLogs();
    }


    private void setupLogs() {
        mLogs = new ArrayList<>();
        mAdapter = new LogAdapter(this.getActivity(),mLogs);
    }


    private boolean isMainThread(){
        return Looper.getMainLooper() == Looper.myLooper();
    }

    public void log(String logMsg){
        if (isMainThread()){
            mLogs.add(logMsg + "(主线程)");
            mAdapter.notifyDataSetChanged();
        }else {
            mLogs.add(logMsg + "(非主线程)");
            //此处必须在UI线程更新（因为此时RecycelrView可能还在计算布局或者滚动）
            new Handler(Looper.getMainLooper())
                    .post(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.notifyDataSetChanged();
                        }
                    });
        }

    }


    public void doSomethingBlocked() {
        log("进行耗时操作");
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void clearLogs(){
        mLogs.clear();
        this.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAdapter.notifyDataSetChanged();
            }
        });

    }




}

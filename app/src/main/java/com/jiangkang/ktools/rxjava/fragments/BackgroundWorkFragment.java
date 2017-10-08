package com.jiangkang.ktools.rxjava.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;


import com.jiangkang.ktools.R;
import com.jiangkang.ktools.rxjava.LogAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * A simple {@link Fragment} subclass.
 */
public class BackgroundWorkFragment extends Fragment {


    private static final String TAG = "BackgroundWorkFragment";
    @BindView(R.id.btn_start_bg_work)
    Button mBtnStartBgWork;
    @BindView(R.id.btn_clear_log)
    Button mBtnClearLog;
    @BindView(R.id.progress_bg_work)
    ProgressBar mProgressBgWork;
    @BindView(R.id.rc_bg_work)
    RecyclerView mRcBgWork;
    Unbinder unbinder;


    private LogAdapter mAdapter;
    private List<String> mLogs;

    private Subscription subscription;

    private CompositeSubscription compositeSubscription = new CompositeSubscription();


    public BackgroundWorkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_background_work, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupLogs();
        this.getActivity().setTitle("后台耗时任务");
    }

    private void setupLogs() {
        mLogs = new ArrayList<>();
        mAdapter = new LogAdapter(this.getActivity(),mLogs);
        mRcBgWork.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mRcBgWork.setAdapter(mAdapter);
    }

    private void log(String logMsg){
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
        Log.d(TAG, "log: itemCount ->" + mAdapter.getItemCount());

    }

    private boolean isMainThread(){
        return Looper.getMainLooper() == Looper.myLooper();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();


        compositeSubscription.clear();

    }

    @OnClick(R.id.btn_start_bg_work)
    public void onBtnStartBgWorkClicked() {
        log("点击了开始按钮");
        mProgressBgWork.setVisibility(View.VISIBLE);
        startRxJavaStream();
    }

    private void startRxJavaStream() {
        subscription = Observable.just(true)
                .map(new Func1<Boolean, Boolean>() {
                    @Override
                    public Boolean call(Boolean aBoolean) {
                        log("Observable -> map -> call");
                        doSomethingBlocked();
                        return aBoolean;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {
                        mProgressBgWork.setVisibility(View.INVISIBLE);
                        log("subscriber -> onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        mProgressBgWork.setVisibility(View.INVISIBLE);
                        log("subscriber -> onError :" + e.getMessage());
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        log("subscriber -> onNext :" + aBoolean);
                    }
                });
        compositeSubscription.add(subscription);
    }

    private void doSomethingBlocked() {
        log("进行耗时操作");
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.btn_clear_log)
    public void onBtnClearLogClicked() {
        clearLogs();
        subscription.unsubscribe();
        compositeSubscription.clear();
    }

    private void clearLogs(){
        mLogs.clear();
        this.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAdapter.notifyDataSetChanged();
            }
        });

    }
}

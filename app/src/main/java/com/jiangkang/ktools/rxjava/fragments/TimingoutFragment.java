package com.jiangkang.ktools.rxjava.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.jiangkang.ktools.R;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimingoutFragment extends BaseRxJavaFragment {


    @BindView(R.id.btn_timing_normal)
    Button mBtnTimingNormal;
    @BindView(R.id.btn_timing_out)
    Button mBtnTimingOut;
    @BindView(R.id.rc_timing_out)
    RecyclerView mRcTimingOut;
    Unbinder unbinder;

    public TimingoutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timingout, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.getActivity().setTitle("超时任务");
        mRcTimingOut.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mRcTimingOut.setAdapter(mAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_timing_normal)
    public void onBtnTimingNormalClicked() {

        Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        log("开始一个耗时2s左右的任务，超时时间为3s");
                        subscriber.onNext("正常任务");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.computation())
                .timeout(3, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        log("任务完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                        log("任务出错 ->" + e.getMessage().toString());
                    }

                    @Override
                    public void onNext(String s) {
                        log(String.format("onNext() -> %s", s));
                    }
                })
        ;


    }

    @OnClick(R.id.btn_timing_out)
    public void onBtnTimingOutClicked() {

        Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        log("开启一个耗时4s左右，超时时间为3s的任务");
                        subscriber.onNext("超时任务");
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.computation())
                .timeout(3,TimeUnit.SECONDS,Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        log("任务超时");
                        subscriber.onCompleted();
                    }
                }))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        log("任务完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                        log("任务出错 ->" + e.getMessage().toString());
                    }

                    @Override
                    public void onNext(String s) {
                        log(String.format("onNext() -> %s",s));
                    }
                });

    }
}

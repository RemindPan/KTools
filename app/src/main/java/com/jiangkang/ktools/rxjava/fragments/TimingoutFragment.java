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

import org.reactivestreams.Subscriber;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                        log("开始一个耗时2s左右的任务，超时时间为3s");
                        observableEmitter.onNext("正常任务");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        observableEmitter.onComplete();
                    }

                })
                .subscribeOn(Schedulers.computation())
                .timeout(3, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        log(String.format("onNext() -> %s", s));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        log("任务出错 ->" + throwable.getMessage().toString());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        log("任务完成");
                    }
                });


    }

    @OnClick(R.id.btn_timing_out)
    public void onBtnTimingOutClicked() {

        Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                        log("开启一个耗时4s左右，超时时间为3s的任务");
                        observableEmitter.onNext("超时任务");
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        observableEmitter.onComplete();
                    }

                })
                .subscribeOn(Schedulers.computation())
                .timeout(3, TimeUnit.SECONDS, Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                        log("任务超时");
                        observableEmitter.onComplete();
                    }
                }))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        log(String.format("onNext() -> %s", s));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        log("任务出错 ->" + throwable.getMessage().toString());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        log("任务完成");
                    }
                });

    }
}

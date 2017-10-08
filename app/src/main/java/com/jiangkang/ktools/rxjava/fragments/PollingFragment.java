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
import rx.functions.Action0;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

/**
 * A simple {@link Fragment} subclass.
 */
public class PollingFragment extends BaseRxJavaFragment {


    private static final int INITIAL_DELAY = 0;
    private static final int POLLING_INTERVAL = 1000;
    private static final int POLL_COUNT = 8;


    private int mCounter = 0;

    private CompositeSubscription subscriptions = new CompositeSubscription();


    @BindView(R.id.btn_polling_simple)
    Button mBtnPollingSimple;
    @BindView(R.id.btn_polling_increase)
    Button mBtnPollingIncrease;
    @BindView(R.id.btn_polling_clear_logs)
    Button mBtnPollingClearLogs;
    @BindView(R.id.rc_polling)
    RecyclerView mRcPolling;
    Unbinder unbinder;

    public PollingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_polling, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mRcPolling.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mRcPolling.setAdapter(mAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_polling_simple)
    public void onBtnPollingSimpleClicked() {

        subscriptions.add(
                Observable
                        .interval(INITIAL_DELAY, POLLING_INTERVAL, TimeUnit.MILLISECONDS)
                        .map(new Func1<Long, String>() {
                            @Override
                            public String call(Long aLong) {
                                return simulateRequestAndResponse(aLong);
                            }
                        })
                        .take(POLL_COUNT)
                        .doOnSubscribe(new Action0() {
                            @Override
                            public void call() {
                                //subscribe前的初始化操作
                                log(String.format("开始简单的轮询: %s", mCounter));
                            }
                        })
                        .subscribe(new Subscriber<String>() {
                            @Override
                            public void onCompleted() {
                                mCounter = 0;
                                log("subscriber -> onCompleted");
                            }

                            @Override
                            public void onError(Throwable e) {
                                mCounter = 0;
                                log("subscriber -> onError ->" + e.getMessage().toString());
                            }

                            @Override
                            public void onNext(String s) {
                                log(String.format("执行任务：%s（%02d）", mCounter, getExeSecond()));
                            }
                        }));


    }


    //单位s
    private int getExeSecond() {
        long currentMillis = System.currentTimeMillis();
        return (int) (TimeUnit.MILLISECONDS.toSeconds(currentMillis) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(currentMillis)));
    }


    private String simulateRequestAndResponse(Long aLong) {

        try {
            if (aLong == 4) {
                Thread.sleep(6000);
            } else {
                Thread.sleep(3000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        mCounter++;
        return String.valueOf(mCounter);

    }

    @OnClick(R.id.btn_polling_increase)
    public void onBtnPollingIncreaseClicked() {

//        Observable
//                .just(1)
//                .repeatWhen(new Func1<Observable<? extends Void>, Observable<?>>() {
//                    @Override
//                    public Observable<?> call(Observable<? extends Void> observable) {
//                        return null;
//                    }
//                })


    }

    @OnClick(R.id.btn_polling_clear_logs)
    public void onBtnPollingClearLogsClicked() {
        clearLogs();
        subscriptions.clear();
        mCounter = 0;
    }
}

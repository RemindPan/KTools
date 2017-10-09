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
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;


/**
 * A simple {@link Fragment} subclass.
 */
public class PollingFragment extends BaseRxJavaFragment {


    private static final int INITIAL_DELAY = 0;
    private static final int POLLING_INTERVAL = 1000;
    private static final int POLL_COUNT = 8;


    private int mCounter = 0;

    private CompositeDisposable subscriptions = new CompositeDisposable();


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
                        .map(new Function<Long, Object>() {
                            @Override
                            public Object apply(Long aLong) throws Exception {
                                return simulateRequestAndResponse(aLong);
                            }
                        })
                        .take(POLL_COUNT)
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                log(String.format("开始简单的轮询: %s", mCounter));
                            }
                        })
                        .subscribe(new Consumer<Object>() {
                            @Override
                            public void accept(Object o) throws Exception {
                                log(String.format("执行任务：%s（%02d）", mCounter, getExeSecond()));
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                mCounter = 0;
                                log("subscriber -> onError ->" + throwable.getMessage().toString());
                            }
                        }, new Action() {
                            @Override
                            public void run() throws Exception {
                                mCounter = 0;
                                log("subscriber -> onCompleted");
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

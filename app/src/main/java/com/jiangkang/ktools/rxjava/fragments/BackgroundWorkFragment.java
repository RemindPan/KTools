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
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


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

    private Disposable subscription;

    private CompositeDisposable compositeSubscription = new CompositeDisposable();


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
                .map(new Function<Boolean, Boolean>() {
                    @Override
                    public Boolean apply(Boolean aBoolean) throws Exception {
                        log("Observable -> map -> call");
                        doSomethingBlocked();
                        return aBoolean;
                    }

                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        log("subscriber -> onNext :" + aBoolean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mProgressBgWork.setVisibility(View.INVISIBLE);
                        log("subscriber -> onError :" + throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        mProgressBgWork.setVisibility(View.INVISIBLE);
                        log("subscriber -> onCompleted");
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
        if (subscription != null){
            subscription.dispose();
        }

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

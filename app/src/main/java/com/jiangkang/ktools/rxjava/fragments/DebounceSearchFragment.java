package com.jiangkang.ktools.rxjava.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import com.jiangkang.ktools.R;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;


/**
 * A simple {@link Fragment} subclass.
 */
public class DebounceSearchFragment extends BaseRxJavaFragment {


    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.btn_search_clear_log)
    Button mBtnSearchClearLog;
    @BindView(R.id.rc_debounce_search)
    RecyclerView mRcDebounceSearch;
    Unbinder unbinder;

    private Disposable subscription;

    public DebounceSearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_debounce_search, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.getActivity().setTitle("即时搜索");
        mRcDebounceSearch.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mRcDebounceSearch.setAdapter(mAdapter);


        subscription = Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(final ObservableEmitter<String> observableEmitter) throws Exception {
                        mEtSearch.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                observableEmitter.onNext(s.toString());
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                    }

                })
                .debounce(500, TimeUnit.MILLISECONDS)
//                .filter(new Function<String, Boolean>() {
//                    @Override
//                    public Boolean apply(String s) throws Exception {
//                        if (!TextUtils.isEmpty(s)) return true;
//                        return false;
//                    }
//                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        if (!TextUtils.isEmpty(s)) {
                            log(s);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        log("subscriber -> onError ->" + throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        log("subscriber -> onCompleted");
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        subscription.dispose();
    }

    @OnClick(R.id.btn_search_clear_log)
    public void onViewClicked() {
        clearLogs();
    }
}

package com.jiangkang.ktools.rxjava.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jiangkang.ktools.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class RxJavaFragment extends Fragment {


    @BindView(R.id.btn_bg_work)
    Button mBtnBgWork;
    Unbinder unbinder;
    @BindView(R.id.btn_buffer)
    Button mBtnBuffer;
    @BindView(R.id.btn_debounce)
    Button mBtnDebounce;
    @BindView(R.id.btn_two_way_data_binding)
    Button mBtnTwoWayDataBinding;
    @BindView(R.id.btn_timing_out_task)
    Button mBtnTimingOutTask;
    @BindView(R.id.btn_polling)
    Button mBtnPolling;

    public RxJavaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rx_java, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.getActivity().setTitle("RxJava Demo");
    }

    @OnClick(R.id.btn_bg_work)
    public void onBtnBgWorkClicked() {
        handleClick(new BackgroundWorkFragment());
    }

    private void handleClick(@NonNull Fragment fragment) {
        String tag = fragment.getClass().toString();
        getFragmentManager()
                .beginTransaction()
                .addToBackStack(tag)
                .replace(android.R.id.content, fragment, tag)
                .commit();
    }

    @OnClick(R.id.btn_buffer)
    public void onBtnBufferClicked() {
        handleClick(new BufferTapsFragment());
    }

    @OnClick(R.id.btn_debounce)
    public void onBtnDebounceClicked() {
        handleClick(new DebounceSearchFragment());
    }

    @OnClick(R.id.btn_two_way_data_binding)
    public void onBtnTwoWayDataBindingClicked() {
        handleClick(new TwoWayDataBidingFragment());
    }

    @OnClick(R.id.btn_timing_out_task)
    public void onBtnTimingOutTaskClicked() {
        handleClick(new TimingoutFragment());
    }

    @OnClick(R.id.btn_polling)
    public void onBtnPollingClicked() {
        handleClick(new PollingFragment());
    }




}

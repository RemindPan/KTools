package com.jiangkang.ktools.rxjava.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


import com.jiangkang.ktools.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

/**
 * A simple {@link Fragment} subclass.
 */
public class TwoWayDataBidingFragment extends BaseRxJavaFragment {


    @BindView(R.id.et_add_left)
    EditText mEtAddLeft;
    @BindView(R.id.et_add_right)
    EditText mEtAddRight;
    @BindView(R.id.tv_add_result)
    TextView mTvAddResult;
    Unbinder unbinder;


    private PublishSubject<String> publishSubject;


    public TwoWayDataBidingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_two_way_data_biding, container, false);
        unbinder = ButterKnife.bind(this, view);

        publishSubject = PublishSubject.create();

        publishSubject.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                mTvAddResult.setText(s);
            }
        });

        onNumChanged();

        mEtAddLeft.requestFocus();

        return view;
    }



    @OnTextChanged({R.id.et_add_left,R.id.et_add_right})
    public void onNumChanged() {

        float numLeft = 0;
        float numRight = 0;

        if (!TextUtils.isEmpty(mEtAddLeft.getText().toString())){
            numLeft = Float.parseFloat(mEtAddLeft.getText().toString());
        }
        if (!TextUtils.isEmpty(mEtAddRight.getText().toString())){
            numRight = Float.parseFloat(mEtAddRight.getText().toString());
        }

        publishSubject.onNext(String.valueOf(numLeft + numRight));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.getActivity().setTitle("双向绑定");
    }


}

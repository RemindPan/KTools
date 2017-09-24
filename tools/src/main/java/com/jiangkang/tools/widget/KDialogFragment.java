package com.jiangkang.tools.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiangkang.tools.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class KDialogFragment extends DialogFragment {


  private Bundle receivedBundle;

  private int contentViewId;


  public KDialogFragment() {
    // Required empty public constructor
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    if (contentViewId != -1){
      return inflater.inflate(contentViewId,container,false);
    }else {
      return inflater.inflate(R.layout.fragment_kdialog, container, false);
    }

  }








}

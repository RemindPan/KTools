package com.jiangkang.ktools;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;

/**
 * Created by jiangkang on 2017/9/5.
 * description：
 */

public class FunctionAdapter extends RecyclerView.Adapter<FunctionAdapter.ViewHolder> {

  private Context mContext;

  private ArrayList<FunctionEntity> functionList = new ArrayList<>();

  public FunctionAdapter(Context mContext) {
    this.mContext = mContext;
    loadData();
  }

  private void loadData() {
    functionList.add(new FunctionEntity("System", SystemActivity.class, -1));
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new ViewHolder(
        LayoutInflater.from(mContext).inflate(R.layout.layout_item_function, parent, false));
  }

  @Override public void onBindViewHolder(ViewHolder holder, final int position) {
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        mContext.startActivity(new Intent(mContext,functionList.get(position).getActivity()));
      }
    });

    holder.mTvFunctionName.setText(functionList.get(position).getName());

  }

  @Override public int getItemCount() {
    return functionList.size();
  }


  static class ViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.iv_function_icon) ImageView mIvFunctionIcon;
    @BindView(R.id.tv_function_name) TextView mTvFunctionName;

    ViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}

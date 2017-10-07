package com.jiangkang.ktools.widget;

import android.content.Context;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiangkang.ktools.R;

import java.util.List;

/**
 * Created by jiangkang on 2017/10/5.
 * description：
 */

public class TextAdapter extends RecyclerView.Adapter<TextAdapter.ViewHolder> {


    private List<String> mDataList;

    private Context mContext;

    private onItemClickListener mListener;

    public TextAdapter(Context mContext, List<String> mDataList) {
        this.mDataList = mDataList;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_item_text, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (mDataList != null && mDataList.size() > 0) {
            String content = mDataList.get(position);

            holder.tvItem.setText(content);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null){
                        mListener.onClick(holder,position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mDataList != null && mDataList.size() > 0) {
            return mDataList.size();
        }
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvItem;

        public ViewHolder(View itemView) {
            super(itemView);
            tvItem = (TextView) itemView.findViewById(R.id.tv_item);
        }
    }


    public void setOnItemClickListener(onItemClickListener listener) {
        this.mListener = listener;
    }


    public interface onItemClickListener{
        void onClick(ViewHolder holder, int position);
    }

}

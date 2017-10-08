package com.jiangkang.ktools.rxjava;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiangkang.ktools.R;

import java.util.List;

/**
 * Created by jiangkang on 2017/5/12.
 * description：
 */

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.ViewHolder>{

    private Context mContext;

    private List<String> mLogs;

    public LogAdapter(Context context, @NonNull List<String> logs){
        this.mContext = context;
        this.mLogs = logs;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_log,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mLogs.size() > 0){
            holder.tvLog.setText(mLogs.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mLogs.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvLog;

        public ViewHolder(View itemView) {
            super(itemView);
            tvLog = (TextView) itemView.findViewById(R.id.tv_item_log);
        }

    }

}

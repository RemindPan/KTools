package com.jiangkang.ktools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiangkang.anko.AnkoMainActivity;
import com.jiangkang.jetpack.JetpackMainActivity;
import com.jiangkang.jni.JNIActivity;
import com.jiangkang.kdownloader.KDownloaderActivity;
import com.jiangkang.ktools.effect.EffectActivity;
import com.jiangkang.ktools.rxjava.RxJavaActivity;
import com.jiangkang.ktools.web.HybridActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jiangkang on 2017/9/5.
 * description：Function Module Adapter
 */

public class FunctionAdapter extends RecyclerView.Adapter<FunctionAdapter.ViewHolder> {

    private Context mContext;

    private ArrayList<FunctionEntity> functionList = new ArrayList<>();


    public FunctionAdapter(Context mContext) {
        this.mContext = mContext;
        loadData();
    }

    private void loadData() {
        functionList.add(new FunctionEntity("System", SystemActivity.class, R.drawable.ic_system));
        functionList.add(new FunctionEntity("UI", WidgetActivity.class, R.drawable.ic_widget));
        functionList.add(new FunctionEntity("Storage", StorageActivity.class, R.drawable.ic_storage));
        functionList.add(new FunctionEntity("Requests", RequestsActivity.class, R.drawable.ic_requests));
        functionList.add(new FunctionEntity("Device", DeviceActivity.class, R.drawable.ic_device));
//        functionList.add(new FunctionEntity("Security", SecurityActivity.class, R.drawable.ic_security));
        functionList.add(new FunctionEntity("Download", KDownloaderActivity.class, R.drawable.ic_download));
        functionList.add(new FunctionEntity("Image", ImageActivity.class, R.drawable.ic_image));
//        functionList.add(new FunctionEntity("File", FileSystemActivity.class, R.drawable.ic_file_system));
        functionList.add(new FunctionEntity("Scan", ScanActivity.class, R.drawable.ic_scan));
        functionList.add(new FunctionEntity("Audio", AudioActivity.class, R.drawable.ic_audio));
        functionList.add(new FunctionEntity("Effect", EffectActivity.class, R.drawable.ic_effect));
        functionList.add(new FunctionEntity("RxJava", RxJavaActivity.class, R.drawable.ic_rx_java));
        functionList.add(new FunctionEntity("Web", HybridActivity.class, R.drawable.ic_web));
        functionList.add(new FunctionEntity("JNI", JNIActivity.class, R.drawable.ic_jni));
        functionList.add(new FunctionEntity("Jetpack", JetpackMainActivity.class, R.drawable.ic_jetpack));
        functionList.add(new FunctionEntity("Anko", AnkoMainActivity.class, R.drawable.ic_anko));
        functionList.add(new FunctionEntity("Flutter", FlutterContainerActivity.class, R.drawable.ic_flutter));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.layout_item_function, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final FunctionEntity entity = functionList.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) mContext,
                        holder.mTvFunctionName, BaseActivity.Companion.getVIEW_NAME_HEADER_TITLE());
                ActivityCompat.startActivity(mContext,new Intent(mContext, entity.getActivity()),optionsCompat.toBundle());
            }
        });

        holder.mTvFunctionName.setText(entity.getName());
        if (entity.getResId() != -1) {
            holder.mIvFunctionIcon.setImageResource(entity.getResId());
        }

    }

    @Override
    public int getItemCount() {
        return functionList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_function_icon)
        ImageView mIvFunctionIcon;
        @BindView(R.id.tv_function_name)
        TextView mTvFunctionName;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

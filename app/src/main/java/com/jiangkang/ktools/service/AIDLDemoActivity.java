package com.jiangkang.ktools.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.jiangkang.ktools.IComputation;
import com.jiangkang.ktools.R;
import com.jiangkang.ktools.widget.KDialogActivity;
import com.jiangkang.tools.utils.ToastUtils;

import java.util.Random;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AIDLDemoActivity extends AppCompatActivity {

    @BindView(R.id.tv_add)
    TextView mTvAdd;
    @BindView(R.id.tv_sub)
    TextView mTvSub;
    @BindView(R.id.tv_mul)
    TextView mTvMul;
    @BindView(R.id.tv_del)
    TextView mTvDel;


    public static void launch(Context context, Bundle bundle) {
        Intent intent = new Intent(context, AIDLDemoActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }


    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            final IComputation computation = IComputation.Stub.asInterface(service);

            Runnable mAddTask = new Runnable() {
                @Override
                public void run() {
                    for (int i = 0 ; i < 120; i++){
                        float a = new Random().nextFloat() * 10;
                        float b = new Random().nextFloat() * 10;
                        try {
                            final float addResult = computation.add(a, b);
                            final float subResult = computation.sub(a, b);
                            final float mulResult = computation.mul(a, b);
                            final float delResult = computation.del(a, b);
                            Thread.sleep(500);
                            final int finalI = i;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mTvAdd.setText(String.valueOf(addResult));
                                    mTvSub.setText(String.valueOf(subResult));
                                    mTvMul.setText(String.valueOf(mulResult));
                                    mTvDel.setText(String.valueOf(delResult));
                                    ToastUtils.showShortToast("执行了" + (finalI + 1) + "次");
                                }
                            });

                        } catch (RemoteException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };

            Executors.newCachedThreadPool().submit(mAddTask);

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidldemo);
        ButterKnife.bind(this);

        Intent intent = new Intent(this, ComputationService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

    }


    @Override
    protected void onDestroy() {
        unbindService(mConnection);
        super.onDestroy();
    }
}

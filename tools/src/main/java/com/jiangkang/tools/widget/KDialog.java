package com.jiangkang.tools.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.widget.ImageView;

/**
 * Created by jiangkang on 2017/9/13.
 */

public class KDialog {

    public static void showImgInDialog(Context context, Bitmap bitmap) {
        ImageView imageView = new ImageView(context);
        imageView.setImageBitmap(bitmap);
        new AlertDialog.Builder(context)
                .setView(imageView)
                .setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    public static void showMsgDialog(final Context context, final String content) {
        new android.os.Handler(Looper.getMainLooper())
                .post(new Runnable() {
                    @Override
                    public void run() {
                        new AlertDialog.Builder(context)
                                .setMessage(content)
                                .setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .show();
                    }
                });

    }


}

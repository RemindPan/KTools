package com.jiangkang.tools.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

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


    public static void showCustomViewDialog(final Context context, final String title, final View view,
                                            final DialogInterface.OnClickListener positiveListener, final DialogInterface.OnClickListener negativeListener) {
        new Handler(Looper.getMainLooper())
                .post(new Runnable() {
                    @Override
                    public void run() {
                        new AlertDialog.Builder(context)
                                .setTitle(title)
                                .setView(view)
                                .setPositiveButton("确认", positiveListener)
                                .setNegativeButton("取消", negativeListener)
                                .show();
                    }
                });
    }

    public static void showSingleChoiceDialog(final Context context, final String title, CharSequence[] items, final SingleSelectedCallback callback) {
        final int[] selectedIndex = new int[1];
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedIndex[0] = which;
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (callback != null){
                            callback.singleSelected(selectedIndex[0]);
                        }
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .show();
    }



    public static void showMultiChocicesDialog(final Context context, final String title, CharSequence[] items, final MultiSelectedCallback callback){
        final int[] selectedItems;
        final boolean[] selected = new boolean[items.length];
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMultiChoiceItems(items, new boolean[items.length], new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        selected[which] = isChecked;
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int size = selected.length;
                        List<Integer> selectedList = new ArrayList<>();
                        if (callback != null){
                            for (int i = 0;i < size;i++){
                                if (selected[i]){
                                    selectedList.add(i);
                                }
                            }
                            if (selectedList != null && selectedList.size() > 0){
                                callback.multiSelected(selectedList);
                            }else {
                                callback.selectedNothing();
                            }
                        }
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .show();
    }


    public interface SingleSelectedCallback {
        void singleSelected(int index);
    }

    public interface MultiSelectedCallback{
        void multiSelected(List<Integer> list);
        void selectedNothing();
    }

}

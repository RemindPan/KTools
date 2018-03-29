package com.jiangkang.ktools.widget;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jiangkang.annotations.apt.Router;
import com.jiangkang.ktools.R;
import com.jiangkang.tools.utils.FileUtils;
import com.jiangkang.tools.utils.ToastUtils;
import com.jiangkang.tools.widget.KDialog;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class KDialogActivity extends AppCompatActivity {

    @BindView(R.id.btn_create_simple_text_dialog)
    Button btnCreateSimpleTextDialog;
    @BindView(R.id.btn_single_choice_dialog)
    Button btnSingleChoiceDialog;
    @BindView(R.id.btn_multi_choices_dialog)
    Button btnMultiChoicesDialog;
    @BindView(R.id.btn_progress_dialog_simple)
    Button btnProgressDialogSimple;
    @BindView(R.id.btn_progress_dialog_indeterminate)
    Button btnProgressDialogIndeterminate;
    @BindView(R.id.btn_input_dialog)
    Button btnInputDialog;
    @BindView(R.id.btn_choose_date_dialog)
    Button btnChooseDateDialog;
    @BindView(R.id.btn_choose_time_dialog)
    Button btnChooseTimeDialog;
    @BindView(R.id.btn_web_view_dialog)
    Button btnWebViewDialog;
    @BindView(R.id.btn_bottom_dialog)
    Button btnBottomDialog;


    public static void launch(Context context, Bundle bundle) {
        Intent intent = new Intent(context, KDialogActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //转场动画
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            getWindow().setEnterTransition(new Fade());
            getWindow().setExitTransition(new Fade());
        }
        setContentView(R.layout.activity_kdialog);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_create_simple_text_dialog)
    public void onBtnCreateSimpleTextDialogClicked() {
        new AlertDialog.Builder(this)
                .setTitle("AlertDialog")
                .setMessage("AlertDialog.Builder(context)\n     .setMessage('....')\n     .show()")
                .setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .show();
    }

    @OnClick(R.id.btn_single_choice_dialog)
    public void onBtnSingleChoiceDialogClicked() {
        final String[] singleChoiceItems = new String[]{
                "篮球",
                "足球",
                "乒乓球",
                "羽毛球"
        };
        KDialog.showSingleChoiceDialog(this,
                "你最喜欢哪种运动？",
                singleChoiceItems,
                new KDialog.SingleSelectedCallback() {
                    @Override
                    public void singleSelected(int index) {
                        ToastUtils.showShortToast(singleChoiceItems[index]);
                    }
                });
    }

    @OnClick(R.id.btn_multi_choices_dialog)
    public void onBtnMultiChoicesDialogClicked() {
        final String[] multiChoicesItems = new String[]{
                "篮球",
                "足球",
                "乒乓球",
                "羽毛球"
        };
        KDialog.showMultiChoicesDialog(
                this,
                "你擅长哪些运动？",
                multiChoicesItems,
                new KDialog.MultiSelectedCallback() {
                    @Override
                    public void multiSelected(List<Integer> list) {
                        StringBuilder builder = new StringBuilder();
                        for (int index : list) {
                            builder.append("\n" + multiChoicesItems[index]);
                        }
                        ToastUtils.showShortToast("你擅长:" + builder.toString());
                    }

                    @Override
                    public void selectedNothing() {
                        ToastUtils.showShortToast("你是一个不爱运动的人");
                    }
                }
        );
    }

    @OnClick(R.id.btn_progress_dialog_simple)
    public void onBtnProgressDialogSimpleClicked() {
        final int[] progress = {0};
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                progress[0] += 10;
                dialog.setProgress(progress[0]);
                if (progress[0] >= 100) {
                    ToastUtils.showShortToast("加载完成");
                    timer.cancel();
                    dialog.dismiss();
                }
            }
        }, 0, 500);
    }

    @OnClick(R.id.btn_progress_dialog_indeterminate)
    public void onBtnProgressDialogIndeterminateClicked() {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("正在加载中，请稍等....");
        dialog.show();
    }

    @OnClick(R.id.btn_input_dialog)
    public void onBtnInputDialogClicked() {
        /*
        * 封装之后，会变得非常有趣
        * */
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        View inputView1 = LayoutInflater.from(this).inflate(R.layout.layout_input_dialog, linearLayout, false);
        View inputView2 = LayoutInflater.from(this).inflate(R.layout.layout_input_dialog, linearLayout, false);
        ((TextView) inputView1.findViewById(R.id.tv_input)).setText("用户名：");
        ((EditText) inputView1.findViewById(R.id.et_input)).setHint("请输入用户名");
        ((TextView) inputView2.findViewById(R.id.tv_input)).setText("密    码：");
        ((EditText) inputView2.findViewById(R.id.et_input)).setHint("请输入密码");
        linearLayout.addView(inputView1, 0);
        linearLayout.addView(inputView2, 1);
        new AlertDialog.Builder(this)
                .setView(linearLayout)
                .setTitle("登录框")
                .setCancelable(false)
                .setPositiveButton("登录", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ToastUtils.showShortToast("这只是一个假登录");
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }


    @OnClick(R.id.btn_choose_date_dialog)
    public void onBtnChooseDateDialogClicked() {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        String date = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
                        ToastUtils.showShortToast(date);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    @OnClick(R.id.btn_choose_time_dialog)
    public void onBtnChooseTimeDialogClicked() {
        final Calendar calendar = Calendar.getInstance();
        TimePickerDialog dialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        String time = DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.getTime());
                        ToastUtils.showShortToast(time);
                    }
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                false
        );
        dialog.show();
    }

    @OnClick(R.id.btn_web_view_dialog)
    public void onBtnWebViewDialogClicked() {
        final WebView webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(FileUtils.getAssetsPath("web/gravity-points/index.html"));
        new AlertDialog.Builder(this)
                .setView(webView)
                .setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .show();
    }

    @OnClick(R.id.btn_bottom_dialog)
    public void onBtnBottomDialogClicked() {
        WebView webView = new WebView(this);
        webView.loadUrl("https://github.com/jiangkang/KTools");
        webView.getSettings().setJavaScriptEnabled(true);
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setCancelable(true);
        dialog.setContentView(webView);
        dialog.show();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        }
    }
}

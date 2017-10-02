package com.jiangkang.ktools.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.jiangkang.ktools.R;
import com.jiangkang.tools.utils.ToastUtils;
import com.jiangkang.tools.widget.KDialog;

import java.util.List;

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
        KDialog.showMultiChocicesDialog(
                this,
                "你擅长哪些运动？",
                multiChoicesItems,
                new KDialog.MultiSelectedCallback() {
                    @Override
                    public void multiSelected(List<Integer> list) {
                        StringBuilder builder = new StringBuilder();
                        for (int index : list){
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
    }

    @OnClick(R.id.btn_progress_dialog_indeterminate)
    public void onBtnProgressDialogIndeterminateClicked() {
    }

    @OnClick(R.id.btn_input_dialog)
    public void onBtnInputDialogClicked() {
    }

    @OnClick(R.id.btn_choose_date_dialog)
    public void onBtnChooseDateDialogClicked() {
    }

    @OnClick(R.id.btn_choose_time_dialog)
    public void onBtnChooseTimeDialogClicked() {
    }

    @OnClick(R.id.btn_web_view_dialog)
    public void onBtnWebViewDialogClicked() {
    }
}

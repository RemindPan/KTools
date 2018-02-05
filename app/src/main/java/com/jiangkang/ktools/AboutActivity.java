package com.jiangkang.ktools;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jiangkang.ktools.databinding.ActivityAboutBinding;
import com.jiangkang.ktools.mvvm.AboutPageViewModel;

public class AboutActivity extends AppCompatActivity {

    public static void launch(Context context, Bundle bundle) {
        Intent intent = new Intent(context, AboutActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAboutBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_about);
        AboutPageViewModel viewModel = new AboutPageViewModel(this);
        viewModel.setAuthor("姜康").setSourceUrl("https://github.com/jiangkang/KTools");
        binding.setVm(viewModel);
    }


}

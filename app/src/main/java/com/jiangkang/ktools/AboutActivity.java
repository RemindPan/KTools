package com.jiangkang.ktools;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jiangkang.annotations.apt.Router;
import com.jiangkang.ktools.databinding.ActivityAboutBinding;
import com.jiangkang.ktools.mvvm.AboutPageViewModel;

@Router
public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAboutBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_about);
        AboutPageViewModel viewModel = new AboutPageViewModel(this);
        viewModel.setAuthor("姜康").setSourceUrl("https://github.com/jiangkang/KTools");
        binding.setVm(viewModel);
    }
}

package com.jiangkang.ktools;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;

public class FileSystemActivity extends AppCompatActivity {

    private static final String TAG = FileSystemActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_system);

        printAllPath();

    }

    @TargetApi(Build.VERSION_CODES.N)
    private void printAllPath() {
        Log.d(TAG, "printAllPath: \n" +
            "\ngetPackageCodePath() " + getPackageCodePath() +
            "\ngetPackageResourcePath() " + getPackageResourcePath() +
            "\ngetFilesDir() " + getFilesDir() +
            "\ngetExternalCacheDir() " + getExternalCacheDir() +
            "\ngetCacheDir() " + getCacheDir() +
            "\ngetCodeCacheDir() " + getCodeCacheDir() +
            "\ngetDataDir() " + getDataDir() +
            "\ngetExternalCacheDirs() " + getExternalCacheDirs() +
            "\ngetExternalMediaDirs() " + getExternalMediaDirs());
    }








}

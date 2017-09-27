package com.jiangkang.tools.utils;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.renderscript.ScriptGroup;

import com.jiangkang.tools.King;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jiangkang on 2017/9/20.
 */

public class FileUtils {


    public static String getAssetsPath(String filename) {
        return "file:///android_asset/" + filename;
    }


    public static InputStream getInputStreamFromAssets(String filename) throws IOException {
        AssetManager manager = King.getApplicationContext().getAssets();
        return manager.open(filename);
    }

    public static String[] listFilesFromPath(String path) throws IOException {
        AssetManager manager = King.getApplicationContext().getAssets();
        return manager.list(path);
    }


    public static AssetFileDescriptor getAssetFileDescription(String filename) throws IOException {
        AssetManager manager = King.getApplicationContext().getAssets();
        return manager.openFd(filename);
    }


}

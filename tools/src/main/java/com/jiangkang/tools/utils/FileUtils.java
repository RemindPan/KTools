package com.jiangkang.tools.utils;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.renderscript.ScriptGroup;

import com.jiangkang.tools.King;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executors;

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


    public static void writeStringToFile(String string, File file, boolean isAppending) {
        FileWriter writer = null;
        BufferedWriter bufferedWriter = null;
        try {
            writer = new FileWriter(file);
            bufferedWriter = new BufferedWriter(writer);
            if (isAppending) {
                bufferedWriter.append(string);
            } else {
                bufferedWriter.write(string);
            }
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void writeStringToFile(String string, String filePath, boolean isAppending) {
        writeStringToFile(string, new File(filePath), isAppending);
    }


    public static Bitmap getBitmapFromAssets(String filename) throws IOException {
        AssetManager manager = King.getApplicationContext().getAssets();
        InputStream inputStream = manager.open(filename);
        return BitmapFactory.decodeStream(inputStream);
    }


    public static void copyAssetsToFile(final String assetFilename, final String dstName) {
        Executors.newCachedThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                FileOutputStream fos = null;
                try {
                    File dstFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "ktools", dstName);
                    fos = new FileOutputStream(dstFile);
                    InputStream fileInputStream = getInputStreamFromAssets(assetFilename);
                    byte[] buffer = new byte[1024 * 2];
                    int byteCount;
                    while((byteCount = fileInputStream.read(buffer)) != -1){
                        fos.write(buffer,0,byteCount);
                    }
                    fos.flush();
                } catch (IOException e) {
                }finally {
                    if (fos != null){
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

    }


}

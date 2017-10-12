package com.jiangkang.ktools;

import android.annotation.TargetApi;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonReader;
import android.util.Log;
import android.widget.Button;

import com.jiangkang.tools.utils.FileUtils;
import com.jiangkang.tools.utils.ToastUtils;
import com.jiangkang.tools.widget.KDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FileSystemActivity extends AppCompatActivity {

    private static final String TAG = FileSystemActivity.class.getSimpleName();
    @BindView(R.id.btn_get_general_file_path)
    Button btnGetGeneralFilePath;
    @BindView(R.id.btn_get_img_from_assets)
    Button btnGetImgFromAssets;
    @BindView(R.id.btn_get_audio_from_assets)
    Button btnGetAudioFromAssets;
    @BindView(R.id.btn_get_json_from_assets)
    Button btnGetJsonFromAssets;
    @BindView(R.id.btn_create_file)
    Button btnCreateFile;
    @BindView(R.id.btn_create_dir)
    Button btnCreateDir;
    @BindView(R.id.btn_traverse)
    Button btnTraverse;
    @BindView(R.id.btn_zip_files)
    Button btnZipFiles;
    @BindView(R.id.btn_unzip_files)
    Button btnUnzipFiles;
    @BindView(R.id.btn_read_json_from_file)
    Button btnReadJsonFromFile;
    @BindView(R.id.btn_store_json_to_file)
    Button btnStoreJsonToFile;
    @BindView(R.id.btn_parse_xml_file)
    Button btnParseXmlFile;
    @BindView(R.id.btn_get_file_size)
    Button btnGetFileSize;
    @BindView(R.id.btn_get_dir_size)
    Button btnGetDirSize;
    @BindView(R.id.btn_delete_file)
    Button btnDeleteFile;
    @BindView(R.id.btn_delete_dir)
    Button btnDeleteDir;
    @BindView(R.id.btn_encrypt_file)
    Button btnEncryptFile;
    @BindView(R.id.btn_decrypt_file)
    Button btnDecryptFile;


    private AssetManager assetManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_system);
        ButterKnife.bind(this);
        assetManager = getAssets();

    }


    @OnClick(R.id.btn_get_general_file_path)
    public void onBtnGetGeneralFilePathClicked() {
        StringBuilder builder = new StringBuilder();
        builder.append("context.getExternalCacheDir():\n  --" + getExternalCacheDir().getAbsolutePath() + "\n");
        builder.append("context.getCacheDir():\n  --" + getCacheDir().getAbsolutePath() + "\n");
        builder.append("context.getExternalFilesDir(Environment.DIRECTORY_PICTURES):\n  --" + getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath()+ "\n");
        builder.append("context.getFilesDir():\n  --" + getFilesDir().getAbsolutePath() + "\n");
        KDialog.showMsgDialog(this,builder.toString());
    }

    @OnClick(R.id.btn_get_img_from_assets)
    public void onBtnGetImgFromAssetsClicked() {
        try {
            InputStream inputStream = assetManager.open("img/dog.jpg");
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            KDialog.showImgInDialog(this,bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.btn_get_audio_from_assets)
    public void onBtnGetAudioFromAssetsClicked() {
        try {
            AssetFileDescriptor descriptor = FileUtils.getAssetFileDescription("music/baiyemeng.mp3");
            MediaPlayer player = new MediaPlayer();
            player.setDataSource(descriptor.getFileDescriptor(),descriptor.getStartOffset(),descriptor.getLength());
            player.prepare();
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        ToastUtils.showShortToast("暂时还没有实现");
    }

    @OnClick(R.id.btn_get_json_from_assets)
    public void onBtnGetJsonFromAssetsClicked() {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(assetManager.open("json/demo.json"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            StringBuilder builder = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null){
                builder.append(line);
            }
            String jsonStr = builder.toString();
            JSONObject json = new JSONObject(jsonStr);
            KDialog.showMsgDialog(this,json.toString(4));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.btn_create_file)
    public void onBtnCreateFileClicked() {
        ToastUtils.showShortToast("暂时还没有实现");
    }

    @OnClick(R.id.btn_create_dir)
    public void onBtnCreateDirClicked() {
        ToastUtils.showShortToast("暂时还没有实现");
    }

    @OnClick(R.id.btn_traverse)
    public void onBtnTraverseClicked() {
        ToastUtils.showShortToast("暂时还没有实现");
    }

    @OnClick(R.id.btn_zip_files)
    public void onBtnZipFilesClicked() {
        ToastUtils.showShortToast("暂时还没有实现");
    }

    @OnClick(R.id.btn_unzip_files)
    public void onBtnUnzipFilesClicked() {
        ToastUtils.showShortToast("暂时还没有实现");
    }

    @OnClick(R.id.btn_read_json_from_file)
    public void onBtnReadJsonFromFileClicked() {
        ToastUtils.showShortToast("暂时还没有实现");
    }

    @OnClick(R.id.btn_store_json_to_file)
    public void onBtnStoreJsonToFileClicked() {
        ToastUtils.showShortToast("暂时还没有实现");
    }

    @OnClick(R.id.btn_parse_xml_file)
    public void onBtnParseXmlFileClicked() {
        ToastUtils.showShortToast("暂时还没有实现");
    }

    @OnClick(R.id.btn_get_file_size)
    public void onBtnGetFileSizeClicked() {
        ToastUtils.showShortToast("暂时还没有实现");
    }

    @OnClick(R.id.btn_get_dir_size)
    public void onBtnGetDirSizeClicked() {
        ToastUtils.showShortToast("暂时还没有实现");
    }

    @OnClick(R.id.btn_delete_file)
    public void onBtnDeleteFileClicked() {
        ToastUtils.showShortToast("暂时还没有实现");
    }

    @OnClick(R.id.btn_delete_dir)
    public void onBtnDeleteDirClicked() {
        ToastUtils.showShortToast("暂时还没有实现");
    }

    @OnClick(R.id.btn_encrypt_file)
    public void onBtnEncryptFileClicked() {
        ToastUtils.showShortToast("暂时还没有实现");
    }

    @OnClick(R.id.btn_decrypt_file)
    public void onBtnDecryptFileClicked() {
        ToastUtils.showShortToast("暂时还没有实现");
    }

}

package com.jiangkang.ktools;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;

import com.jiangkang.tools.permission.RxPermissions;
import com.jiangkang.tools.utils.ToastUtils;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.functions.Action1;

import static android.os.Environment.DIRECTORY_PICTURES;

public class ImageActivity extends AppCompatActivity {

    private static final int REQUEST_OPEN_ALBUM = 1000;

    private static final int REQUEST_IMAGE_CAPTURE = 1001;

    private static final int REQUEST_CODE_CAPTURE_IMAGE_WITHOUT_COMPRESS = 1002;
    private static final String TAG = ImageActivity.class.getSimpleName();


    @BindView(R.id.btn_choose_picture_from_album)
    Button btnChoosePictureFromAlbum;

    Unbinder unbinder;
    @BindView(R.id.btn_take_picture)
    Button btnTakePicture;

    @BindView(R.id.btn_take_video)
    Button btnTakeVideo;

    @BindView(R.id.btn_screen_capture)
    Button btnScreenCapture;

    RxPermissions rxPermissions;

    File outputFile;

    @BindView(R.id.btn_take_picture_without_compress)
    Button btnTakePictureWithoutCompress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        setTitle("Image");

        initVar();

        unbinder = ButterKnife.bind(this);
    }

    private void initVar() {
        rxPermissions = new RxPermissions(this);
    }

    @OnClick(R.id.btn_choose_picture_from_album)
    public void onBtnChoosePictureFromAlbumClicked() {
        openAlbum();
    }

    private void openAlbum() {
        Intent albumIntent = new Intent(Intent.ACTION_PICK);
        albumIntent.setType("image/*");
        startActivityForResult(albumIntent, REQUEST_OPEN_ALBUM);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_OPEN_ALBUM:
                if (resultCode == RESULT_OK) {
                    handleAlbumData(data);
                }
                break;
            case REQUEST_IMAGE_CAPTURE:
                if (resultCode == RESULT_OK) {
                    handleImageCaptureData(data);
                }
                break;
            case REQUEST_CODE_CAPTURE_IMAGE_WITHOUT_COMPRESS:
                handleImageCaptureWithoutCompress(data);
                break;
            default:
                break;
        }
    }

    private void handleImageCaptureWithoutCompress(Intent data) {
        Bitmap bitmap = BitmapFactory.decodeFile(outputFile.getAbsolutePath());
        showImgInDialog(bitmap);
    }

    private void handleImageCaptureData(Intent data) {
        //这张图是经过压缩的，清晰度低
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        showImgInDialog(bitmap);
    }

    private void handleAlbumData(Intent data) {
        Uri uri = data.getData();
        String[] projection = new String[]{
                MediaStore.Images.Media.DATA
        };

        Cursor cursor = getContentResolver().query(
                uri,
                projection,
                null,
                null,
                null
        );
        if (cursor != null && cursor.moveToFirst()) {
            int dataIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
            String imagePath = cursor.getString(dataIndex);
            cursor.close();
            showBitmap(imagePath);
        }

    }

    private void showBitmap(String imagePath) {
        ToastUtils.showShortToast(imagePath);
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        showImgInDialog(bitmap);
    }

    private void showImgInDialog(Bitmap bitmap) {
        ImageView imageView = new ImageView(this);
        imageView.setImageBitmap(bitmap);
        new AlertDialog.Builder(this)
                .setView(imageView)
                .setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    @OnClick(R.id.btn_take_picture)
    public void onBtnTakePictureClicked() {
        rxPermissions.request(Manifest.permission.CAMERA)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {
                            openCamera();
                        } else {
                            ToastUtils.showShortToast("权限被拒绝");
                        }
                    }
                });

    }

    private void openCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
    }

    @OnClick(R.id.btn_take_video)
    public void onBtnTakeVideoClicked() {
    }

    @OnClick(R.id.btn_screen_capture)
    public void onBtnScreenCaptureClicked() {
    }

    @OnClick(R.id.btn_take_picture_without_compress)
    public void onBtnTakePictureWithoutCompressClicked() {
        rxPermissions.request(Manifest.permission.CAMERA)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean){
                           openCameraWithOutput();
                        }else {
                            ToastUtils.showShortToast("权限被拒绝");
                        }
                    }
                });
    }

    private void openCameraWithOutput() {
        String path = new File(Environment.getExternalStorageDirectory(),"ktools").getAbsolutePath();
        if (!new File(path).exists()){
            new File(path).mkdirs();
        }
        outputFile = new File(path ,System.currentTimeMillis() + ".png");
        if (!outputFile.exists()){
            try {
                outputFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Uri contentUri = FileProvider.getUriForFile(
                this,
                BuildConfig.APPLICATION_ID,
                outputFile
                );
        Log.d(TAG, "openCameraWithOutput: uri = " + contentUri.toString());
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(Intent.EXTRA_MIME_TYPES, MimeTypeMap.getSingleton().getMimeTypeFromExtension("png"));
        intent.putExtra(MediaStore.EXTRA_OUTPUT,contentUri);
        startActivityForResult(intent,REQUEST_CODE_CAPTURE_IMAGE_WITHOUT_COMPRESS);
    }
}

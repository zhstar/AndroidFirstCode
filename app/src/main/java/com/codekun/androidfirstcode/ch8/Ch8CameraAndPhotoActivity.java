package com.codekun.androidfirstcode.ch8;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.codekun.androidfirstcode.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Ch8CameraAndPhotoActivity extends AppCompatActivity {

    private static final int TAKE_PHOTO=  0;
    private static final int CROP_PHOTO = 1;

    private static final int GALLARY_PHOTO = 2;

    private Button cameraButton;
    protected Button photoButton;

    private ImageView previewImageView;

    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch8_camera_and_photo);

        cameraButton = (Button)findViewById(R.id.ch8_camera_button);
        photoButton = (Button)findViewById(R.id.ch8_photo_button);
        previewImageView = (ImageView) findViewById(R.id.ch8_preview_imageView);

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(Environment.getExternalStorageDirectory(), "androidfirstcode.png");
                if (file.exists()){
                    file.delete();
                }
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageUri = Uri.fromFile(file);
                Intent i = new Intent("android.media.action.IMAGE_CAPTURE");
                i.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(i, TAKE_PHOTO);
            }
        });

        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, GALLARY_PHOTO);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TAKE_PHOTO){
            if (resultCode == RESULT_OK){
                //裁剪
                Intent i = new Intent("com.android.camera.action.CROP");
                i.setDataAndType(imageUri, "image/*");
                i.putExtra("scale", true);
                i.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(i, CROP_PHOTO);
            }

        }else if (requestCode == CROP_PHOTO){
            if (resultCode == RESULT_OK){
                try {
                    Bitmap bmp = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                    previewImageView.setImageBitmap(bmp);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }else if (requestCode == GALLARY_PHOTO){
            if (resultCode == RESULT_OK){
                //自来:《Android多媒体开发高级编程》
                try {
                    Uri uri = data.getData();
                    int previewW = 200;
                    int previewH = 200;
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    //加载图像的尺寸
                    options.inJustDecodeBounds = true;
                    Bitmap bmp2 = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, options);
                    //计算比例
                    int widthRatio = (int)Math.ceil((float)options.outWidth / previewW);
                    int heightRatio = (int)Math.ceil((float)options.outHeight / previewH);
                    if (widthRatio > 1 && heightRatio > 1){
                        if (widthRatio > heightRatio){
                            options.inSampleSize = widthRatio;//大于1时为缩小
                        }else{
                            options.inSampleSize = heightRatio;
                        }
                    }
                    //加载图像本身
                    options.inJustDecodeBounds = false;
                    bmp2 = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, options);
                    previewImageView.setImageBitmap(bmp2);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }
}

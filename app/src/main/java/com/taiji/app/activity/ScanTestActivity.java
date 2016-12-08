package com.taiji.app.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.blankj.utilcode.utils.FileUtils;
import com.blankj.utilcode.utils.ImageUtils;
import com.blankj.utilcode.utils.SDCardUtils;
import com.taiji.app.R;
import com.taiji.library.data.constant.C;
import com.taiji.library.ui.activity.ScanActivity;
import com.taiji.library.util.L;
import com.taiji.library.util.T;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 描述：扫描二维码测试
 * 创建人： panho
 * 创建时间： 2016-11-28
 */

public class ScanTestActivity extends ScanActivity implements View.OnClickListener {

    @BindView(R.id.qrcode_iv)
    ImageView mQrcodeIv;
    @BindView(R.id.create_ic_qrcode_btn)
    Button mCreateIcQrcodeBtn;
    @BindView(R.id.create_qrcode_btn)
    Button mCreateQrcodeBtn;
    @BindView(R.id.parse_qrcode_btn)
    Button mParseQrcodeBtn;

    private static final int REQUEST_IMAGE = 2;

    private String textContent = "123456789";
    private Bitmap bitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_test_acy);
        ButterKnife.bind(this);
        setTitle("二维码扫描");

        mCreateQrcodeBtn.setOnClickListener(this);
        mCreateIcQrcodeBtn.setOnClickListener(this);
        mParseQrcodeBtn.setOnClickListener(this);
        mQrcodeIv.setOnClickListener(this);
    }

    @Override
    protected void onScanResult(String result) {
        L.d(result);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_qrcode_btn:
                bitmap = CodeUtils.createImage(textContent, 400, 400, null);
                mQrcodeIv.setImageBitmap(bitmap);
                ImageUtils.save(bitmap, C.BASE.SD_PATH+"Core"+File.separator+"二维码.png",Bitmap.CompressFormat.PNG);
                break;
            case R.id.create_ic_qrcode_btn:
                bitmap = CodeUtils.createImage(textContent, 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                mQrcodeIv.setImageBitmap(bitmap);
                ImageUtils.save(bitmap, SDCardUtils.getSDCardPath()+ File.separator+"Core"+File.separator+"二维码.png",Bitmap.CompressFormat.PNG);
                break;
            case R.id.parse_qrcode_btn:
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_IMAGE);
                break;
            case R.id.qrcode_iv:
                CodeUtils.analyzeBitmap(SDCardUtils.getSDCardPath() + File.separator + "Core" + File.separator + "二维码.png", new CodeUtils.AnalyzeCallback() {
                    @Override
                    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                        T.showLong(ScanTestActivity.this,"解析结果"+result);
                    }

                    @Override
                    public void onAnalyzeFailed() {
                        T.showShort(ScanTestActivity.this,"解析失败");
                    }
                });
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_IMAGE){
            if(data!=null){
                Uri uri = data.getData();
                String[] proj = { MediaStore.Images.Media.DATA };
                Cursor actualimagecursor = managedQuery(uri,proj,null,null,null);
                int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                actualimagecursor.moveToFirst();
                String img_path = actualimagecursor.getString(actual_image_column_index);
                CodeUtils.analyzeBitmap(img_path, new CodeUtils.AnalyzeCallback() {
                    @Override
                    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                        T.showLong(ScanTestActivity.this,"解析结果"+result);
                    }

                    @Override
                    public void onAnalyzeFailed() {
                        T.showShort(ScanTestActivity.this,"解析失败");
                    }
                });
            }
        }
    }
}

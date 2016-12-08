package com.taiji.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.taiji.app.R;
import com.taiji.library.ui.activity.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.iwf.photopicker.PhotoPicker;

/**
 * 描述：选择照片
 * 创建人： panho
 * 创建时间： 2016-12-2
 */

public class SelectPhotoActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.select_btn)
    Button mSelectBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_photo_layout);
        ButterKnife.bind(this);

        mSelectBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.select_btn:
                PhotoPicker.builder()
                        .setPhotoCount(9)
                        .setShowCamera(true)
                        .setPreviewEnabled(false)
                        .start(SelectPhotoActivity.this, PhotoPicker.REQUEST_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE){
            if(data!=null){
                ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            }
        }


    }
}

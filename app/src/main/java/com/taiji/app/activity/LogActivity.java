package com.taiji.app.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.taiji.app.R;
import com.taiji.library.andfix.AndFix;
import com.taiji.library.http.download.FileApi;
import com.taiji.library.http.download.FileCallback;
import com.taiji.library.statistics.TaiJiEventAgent;
import com.taiji.library.ui.activity.BaseActivity;
import com.taiji.library.util.PermissionUtil;
import com.taiji.library.util.T;

import java.io.File;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * 描述：日志框架测试
 * 创建人： panho
 * 创建时间： 2016-12-20
 */

public class LogActivity extends BaseActivity {

    @BindView(R.id.login_btn)
    Button mLoginBtn;
    @BindView(R.id.andfix_tv)
    TextView mAndfixTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        ButterKnife.bind(this);

        addPatch();

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaiJiEventAgent.getmInstance(LogActivity.this).onEvent("12", new HashMap<String, String>());
                T.showShort(LogActivity.this, "这是新版本");
            }
        });
        mAndfixTv.setText("看看是否更新成功");
    }

    public void addPatch() {
        String baseUrl = "http://192.168.1.6:8080/file/";
        String fileName = "AndFix.apatch";
        String fileStoreDir = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + File.separator + "CubeCare";
        String fileStoreName = fileName;
        PermissionUtil.checkWriteStoragePermission(this);
        FileApi.getInstance(baseUrl)
                .loadFileByName(fileName, new FileCallback(fileStoreDir, fileStoreName) {

                    @Override
                    public void onSuccess(File file) {
                        AndFix.addPatch(LogActivity.this, file.getPath());
                    }

                    @Override
                    public void progress(long progress, long total) {

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        call.cancel();
                    }
                });
    }

}

package com.taiji.app.activity;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.taiji.app.R;
import com.taiji.library.data.manager.DataManager;
import com.taiji.library.http.download.FileApi;
import com.taiji.library.http.download.FileCallback;
import com.taiji.library.http.download.HDialogBuilder;
import com.taiji.library.ui.activity.BaseActivity;
import com.taiji.library.ui.progressbar.NumberProgressBar;
import com.taiji.library.util.PermissionUtil;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 描述：
 * 创建人： panho
 * 创建时间： 2016-11-24
 */

public class DownloadActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.download_btn)
    Button mDownloadBtn;
    @BindView(R.id.load_progress_btn)
    Button mLoadProgressBtn;
    @BindView(R.id.numberbar)
    NumberProgressBar mNumberbar;

    TextView txtProgress;

    HDialogBuilder mHDialogBuilder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download_acy);
        ButterKnife.bind(this);

        mDownloadBtn.setOnClickListener(this);
        mLoadProgressBtn.setOnClickListener(this);


        txtProgress = (TextView) View.inflate(this, R.layout.layout_hd_dialog_custom_tv, null);


        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Observable.just(1)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<Integer>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(Integer integer) {
                                mNumberbar.incrementProgressBy(integer);
                            }
                        });
            }
        }, 1000, 100);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.download_btn:
                String baseUrl = "http://10.6.66.130:8085/CubeCare/apk/";
                String fileName = "CubeCare.apk";
                String fileStoreDir = Environment.getExternalStorageDirectory()
                        .getAbsolutePath() + File.separator + "CubeCare";
                String fileStoreName = fileName;
                showLoadingDialog();
                PermissionUtil.checkWriteStoragePermission(this);
                FileApi.getInstance(baseUrl)
                        .loadFileByName(fileName, new FileCallback(fileStoreDir, fileStoreName) {

                            @Override
                            public void onSuccess(File file) {
                                super.onSuccess(file);
                                mHDialogBuilder.dismiss();
                            }

                            @Override
                            public void progress(long progress, long total) {
                                updateProgress(progress, total);
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                mHDialogBuilder.dismiss();
                                call.cancel();
                            }
                        });

                break;
            case R.id.load_progress_btn:
                this.showProgress();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hideProgress();
                    }
                }, 4000);

                break;
        }
    }


    private void updateProgress(long progress, long total) {
        txtProgress.setText(String.format("正在下载:(%s/%s)",
                DataManager.getFormatSize(progress),
                DataManager.getFormatSize(total)));
    }

    private void showLoadingDialog() {
        txtProgress = (TextView) View.inflate(this, R.layout.layout_hd_dialog_custom_tv, null);
        mHDialogBuilder = new HDialogBuilder(this);
        mHDialogBuilder.setCustomView(txtProgress)
                .title("下载")
                .nBtnText("取消")
                .nBtnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mHDialogBuilder.dismiss();
                        FileApi.cancelLoading();
                    }
                })
                .show();
    }
}

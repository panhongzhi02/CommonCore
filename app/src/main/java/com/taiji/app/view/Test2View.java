package com.taiji.app.view;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.taiji.app.MainActivity;
import com.taiji.app.R;
import com.taiji.app.adapter.Test2Adapter;
import com.taiji.app.bean.Test2;
import com.taiji.library.data.manager.DataManager;
import com.taiji.library.http.download.FileApi;
import com.taiji.library.http.download.FileCallback;
import com.taiji.library.http.download.HDialogBuilder;
import com.taiji.library.ui.decoration.HorizontalDividerItemDecoration;
import com.taiji.library.ui.decoration.VerticalDividerItemDecoration;
import com.taiji.library.ui.progressbar.NumberProgressBar;
import com.taiji.library.ui.view.BasePageView;
import com.taiji.library.util.PermissionUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
 * 创建时间： 2016-11-18
 */

public class Test2View extends BasePageView implements View.OnClickListener {

    @BindView(R.id.test_rv)
    RecyclerView mTestRv;

    private MainActivity mActivity;

    private List<Test2> mTest2s = new ArrayList<>();

    public Test2View(Context context) {
        this(context, null);
    }

    public Test2View(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.test2_layout, this, true);
        ButterKnife.bind(this);

        mActivity = (MainActivity) context;


        mTest2s.add(new Test2("下载\n测试","com.taiji.app.activity.DownloadActivity"));
        mTest2s.add(new Test2("工具\n测试","com.taiji.app.activity.UtilActivity"));
        mTest2s.add(new Test2("扫描\n测试","com.taiji.app.activity.ScanTestActivity"));
        mTest2s.add(new Test2("网页\n测试","com.taiji.app.activity.WebViewActivity"));
        mTest2s.add(new Test2("通用\n登录","com.taiji.app.activity.WebViewActivity"));
        mTest2s.add(new Test2("MVP\n测试","com.taiji.app.activity.MVPTestActivity"));
        mTest2s.add(new Test2("选择\n照片","com.taiji.app.activity.SelectPhotoActivity"));
        mTest2s.add(new Test2("日志\n测试","com.taiji.app.activity.LogActivity"));

        mTestRv.setLayoutManager(new GridLayoutManager(mContext,4));
        Test2Adapter adapter = new Test2Adapter(mTest2s);
        mTestRv.setAdapter(adapter);
        mTestRv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Intent intent = new Intent();
                intent.setClassName(mContext,((Test2)baseQuickAdapter.getItem(i)).getActivityName());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public void onRefreshData(String... param) {

    }

    @Override
    public void onClick(View v) {

    }

}

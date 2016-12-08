package com.taiji.app.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.widget.TableLayout;

import com.taiji.app.R;
import com.taiji.library.ui.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 描述：
 * 创建人： panho
 * 创建时间： 2016-12-1
 */

public class MVPTestActivity extends BaseActivity {

    @BindView(R.id.main_vp)
    ViewPager mMainVp;
    @BindView(R.id.bottom_tab)
    TableLayout mBottomTab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mvp_test_layout);
        ButterKnife.bind(this);

        setTitle("MVP模式测试");

        

    }
}

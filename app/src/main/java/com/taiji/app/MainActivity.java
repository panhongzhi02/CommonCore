package com.taiji.app;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.taiji.app.view.Test1View;
import com.taiji.app.view.Test2View;
import com.taiji.library.ui.activity.BaseActivity;
import com.taiji.library.ui.adapter.SimplePageAdapter;
import com.taiji.library.ui.helper.TabPageHelper;
import com.taiji.library.util.T;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.test_tab)
    TabLayout mTestTab;
    @BindView(R.id.test_vp)
    ViewPager mTestVp;

    private TabPageHelper mPageHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setTitle("我的android框架");

        mPageHelper = new TabPageHelper();
        mPageHelper.add("测试页1",new Test1View(this));
        mPageHelper.add("测试页2",new Test2View(this));

        mTestTab.setTabMode(TabLayout.MODE_FIXED);
        mPageHelper.initTab(mTestTab);
        SimplePageAdapter adapter = new SimplePageAdapter(mPageHelper);
        mTestVp.setAdapter(adapter);
        mTestTab.setupWithViewPager(mTestVp);


    }

    @Override
    public void onTitleClick() {
        T.showShort(this, "点击了标题");
        super.onTitleClick();
    }
}

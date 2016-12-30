package com.taiji.cc.order.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taiji.cc.R;
import com.taiji.cc.exelist.view.ExelistView;
import com.taiji.library.ui.adapter.SimplePageAdapter;
import com.taiji.library.ui.fragment.BaseFragment;
import com.taiji.library.ui.helper.TabPageHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：panho on 2016-12-28 13:47
 * 邮箱: panhongzhi02@163.com
 * 功能描述：
 */

public class OrderFragment extends BaseFragment {

    @BindView(R.id.order_tab)
    TabLayout mOrderTab;
    @BindView(R.id.order_vp)
    ViewPager mOrderVp;

    private TabPageHelper mTabPageHelper;

    public OrderFragment() {
    }

    public static OrderFragment newInstance() {
        return new OrderFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.order_fragment, container, false);
        ButterKnife.bind(this, root);

        mTabPageHelper = new TabPageHelper();
        mTabPageHelper.add("医嘱列表",new OrderView(mActivity));
        mTabPageHelper.add("执行单",new ExelistView(mActivity));
        mOrderTab.setTabMode(TabLayout.MODE_FIXED);
        mTabPageHelper.initTab(mOrderTab);
        SimplePageAdapter adapter = new SimplePageAdapter(mTabPageHelper);
        mOrderVp.setAdapter(adapter);
        mOrderTab.setupWithViewPager(mOrderVp);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

}

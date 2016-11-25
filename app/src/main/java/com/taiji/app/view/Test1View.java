package com.taiji.app.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.taiji.app.R;
import com.taiji.app.adapter.Test1Adapter;
import com.taiji.app.greendao.bean.Test;
import com.taiji.app.greendao.manager.TestDBManager;
import com.taiji.library.ui.decoration.HorizontalDividerItemDecoration;
import com.taiji.library.ui.helper.PageHelper;
import com.taiji.library.ui.page.base.PageBean;
import com.taiji.library.ui.view.BasePageView;
import com.taiji.library.util.L;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 描述：
 * 创建人： panho
 * 创建时间： 2016-11-18
 */

public class Test1View extends BasePageView {
    @BindView(R.id.test_rv)
    RecyclerView mTestRv;
    @BindView(R.id.test_sr)
    SwipeRefreshLayout mTestSr;

    private PageHelper mPageHelper;

    List<Test> mlist = new ArrayList<>();
    private PageBean<Test> pb;

    private Test1Adapter mAdapter;

    public Test1View(Context context) {
        this(context, null);
    }

    public Test1View(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.test1_layout, this, true);
        ButterKnife.bind(this);

        mTestSr.setColorSchemeResources(R.color.colorPrimary);
        mTestSr.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPageHelper.init();
                loadData(mPageHelper.getStart());
            }
        });
        mTestRv.setLayoutManager(new LinearLayoutManager(mContext));
        List<Test> list = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            String s = i<10?"0"+i:i+"";
            list.add(new Test(s));
        }
        TestDBManager.getmInstance(mContext).insertList(list);


        mPageHelper = new PageHelper(mContext,20);
        mPageHelper.init();
        mPageHelper.setOnPageChangeListener(new PageHelper.OnPageChangeListener() {
            @Override
            public void onInit() {
                L.d("初始化");
                mAdapter.removeAllFooterView();
            }

            @Override
            public void onRefresh() {
                L.d("下拉刷新");
                mAdapter.setNewData(pb.getTList());
                mTestSr.setRefreshing(false);
                mPageHelper.setCurrentCounter(mAdapter.getData().size());
            }

            @Override
            public void loadMore() {
                L.d("加载更多");
                mAdapter.addData(pb.getTList());
                mTestSr.setRefreshing(false);
                mPageHelper.setCurrentCounter(mAdapter.getData().size());
            }

            @Override
            public void onAllComplete() {
                L.d("全部数据加载完成");
                mTestSr.setRefreshing(false);
                mAdapter.removeAllFooterView();
                mAdapter.loadComplete();
            }

        });

        mAdapter = new Test1Adapter(mlist);
        mAdapter.openLoadMore(mPageHelper.getPage_size());
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mTestRv.post(new Runnable() {
                    @Override
                    public void run() {
                        loadData(mPageHelper.getStart());
                        mPageHelper.pageLoad();
                    }
                });
            }
        });
        mTestRv.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(mContext)
                .colorResId(R.color.colorPrimary)
                        .showLastDivider()
                        .marginResId(R.dimen.listview_line_margin)
                        .sizeResId(R.dimen.listview_line)
                        .build()
        );
        mTestRv.setAdapter(mAdapter);
        loadData(mPageHelper.getStart());
    }

    public void loadData(int start){
        mTestSr.setRefreshing(true);
        pb = TestDBManager.getmInstance(mContext).queryPage(start,mPageHelper.getPage_size());
        mPageHelper.loadComplate(pb.getTotal());
    }

    @Override
    public void onRefreshData(String... param) {

    }
}

package com.taiji.app.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.taiji.app.R;
import com.taiji.app.greendao.bean.Test;

import java.util.List;

/**
 * 描述：
 * 创建人： panho
 * 创建时间： 2016-11-18
 */

public class Test1Adapter extends BaseQuickAdapter<Test>{

    public Test1Adapter(List<Test> data) {
        super(R.layout.test1_item_layout,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Test test) {
        baseViewHolder.setText(R.id.test_tv,test.getId());
    }

}

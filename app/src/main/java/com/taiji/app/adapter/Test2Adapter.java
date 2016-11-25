package com.taiji.app.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.taiji.app.R;
import com.taiji.app.bean.Test2;

import java.util.List;

/**
 * 描述：
 * 创建人： panho
 * 创建时间： 2016-11-24
 */

public class Test2Adapter extends BaseQuickAdapter<Test2>{

    public Test2Adapter(List<Test2> data) {
        super(R.layout.test2_item_layout,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Test2 test2) {
        baseViewHolder.setText(R.id.name_tv,test2.getName());
    }
}

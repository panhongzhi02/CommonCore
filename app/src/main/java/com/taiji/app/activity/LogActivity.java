package com.taiji.app.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.taiji.app.R;
import com.taiji.library.greendao.bean.EventLog;
import com.taiji.library.statistics.TaiJiEventAgent;
import com.taiji.library.ui.activity.BaseActivity;
import com.taiji.library.util.T;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 描述：日志框架测试
 * 创建人： panho
 * 创建时间： 2016-12-20
 */

public class LogActivity extends BaseActivity {

    @BindView(R.id.login_btn)
    Button mLoginBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        ButterKnife.bind(this);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaiJiEventAgent.getmInstance(LogActivity.this).onEvent("12",new HashMap<String, String>());
                T.showShort(LogActivity.this,"这是新版本");
            }
        });


    }
}

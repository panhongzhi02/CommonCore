package com.taiji.library.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.taiji.library.ui.activity.BaseActivity;

/**
 * 作者：panho on 2016-12-26 16:22
 * 邮箱: panhongzhi02@163.com
 * 功能描述：
 */

public class BaseFragment extends Fragment{

    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";

    protected BaseActivity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if(savedInstanceState!=null) {
            boolean isSupporHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            if (isSupporHidden) {
                fragmentTransaction.hide(this);
            } else {
                fragmentTransaction.show(this);
            }
            fragmentTransaction.commit();
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(STATE_SAVE_IS_HIDDEN,isHidden());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (BaseActivity) context;
    }

}

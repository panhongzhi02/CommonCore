package com.taiji.cc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.taiji.cc.order.view.OrderFragment;
import com.taiji.cc.patient.view.PatientFragment;
import com.taiji.library.data.DataPreferences;
import com.taiji.library.data.constant.C;
import com.taiji.library.ui.activity.BaseActivity;
import com.taiji.library.ui.fragment.BaseFragment;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener{

    @BindView(R.id.content_frame)
    FrameLayout mContentFrame;

    @BindView(R.id.dl_left)
    DrawerLayout mDlLeft;
    @BindView(R.id.nav_view)
    NavigationView mNavView;

    private FragmentManager mFragmentManager;
    private FragmentTransaction mTransaction;

    private BaseFragment mPatientFragment;
    private BaseFragment mOrderFragment;

    private Map<Integer,BaseFragment> mFragmentMap;

    private ActionBarDrawerToggle mActionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setTitle("主界面");

        DataPreferences.getInstance(this).updateDate(C.HTTP.BASE_URL,"http://192.168.1.13:8080/CubeCare/");

        mFragmentMap = new HashMap<>();
        mFragmentManager = getSupportFragmentManager();


        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDlLeft, mToolbar, R.string.open, R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mActionBarDrawerToggle.syncState();
        mDlLeft.addDrawerListener(mActionBarDrawerToggle);

        mNavView.setNavigationItemSelectedListener(this);
        View headerView = mNavView.getHeaderView(0);

        initView(R.id.nav_camera);
    }

    private void initView(int id){
        mTransaction = mFragmentManager.beginTransaction();

        for (Integer key:mFragmentMap.keySet()) {
            if(mFragmentMap.get(key)!=null){
                mTransaction.hide(mFragmentMap.get(key));
            }
        }

        switch (id){
            case R.id.nav_camera:
                if(mPatientFragment == null){
                    mPatientFragment = PatientFragment.newInstance();
                    mTransaction.add(R.id.content_frame,mPatientFragment);
                    mFragmentMap.put(id,mPatientFragment);
                }else {
                    mTransaction.show(mPatientFragment);
                }
                break;
            case R.id.nav_gallery:
                if(mOrderFragment == null){
                    mOrderFragment = OrderFragment.newInstance();
                    mTransaction.add(R.id.content_frame,mOrderFragment);
                    mFragmentMap.put(id,mOrderFragment);
                }else {
                    mTransaction.show(mOrderFragment);
                }
                break;
        }
        mTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        initView(id);
        mDlLeft.closeDrawer(GravityCompat.START);
        return true;
    }
}

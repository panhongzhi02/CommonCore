package com.taiji.cc;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.taiji.cc.patient.data.PatientRepository;
import com.taiji.cc.patient.data.local.PatientLocalDataSource;
import com.taiji.cc.patient.data.remote.PatientRemoteDataSource;
import com.taiji.cc.patient.presenter.PatientPresenter;
import com.taiji.cc.patient.view.PatientFragment;
import com.taiji.library.ui.activity.BaseActivity;

import static com.google.common.base.Preconditions.checkNotNull;

public class HomeActivity extends BaseActivity {

    @butterknife.BindView(R.id.content_frame)
    FrameLayout mContentFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        butterknife.ButterKnife.bind(this);
        setTitle("主界面");

        PatientFragment patientFragment = (PatientFragment) getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if(patientFragment==null){
            patientFragment = PatientFragment.newInstance();
            addFragment(getSupportFragmentManager(),patientFragment,R.id.content_frame);
        }

    }

    private void addFragment(@NonNull FragmentManager fragmentManager,
                             @NonNull Fragment fragment,int frameId){
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId,fragment);
        transaction.commit();
    }
}

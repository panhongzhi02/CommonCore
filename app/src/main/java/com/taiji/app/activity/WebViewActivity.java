package com.taiji.app.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.taiji.app.R;
import com.taiji.library.ui.activity.BaseActivity;
import com.taiji.library.util.T;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 描述：
 * 创建人： panho
 * 创建时间： 2016-11-29
 */

public class WebViewActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener{

    @BindView(R.id.webview)
    WebView mWebview;
    @BindView(R.id.webview_sr)
    SwipeRefreshLayout mWebviewSr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view_acy);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        mToolbar.setOnMenuItemClickListener(this);

        mWebviewSr.setColorSchemeResources(R.color.colorPrimary);
        mWebviewSr.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mWebview.reload();
            }
        });

        //WebView适配屏幕设置
        mWebview.getSettings().setUseWideViewPort(true);
        mWebview.getSettings().setLoadWithOverviewMode(true);
        //js功能支持
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebview.getSettings().setBuiltInZoomControls(true);
        //对网页源码解析功能
        mWebview.getSettings().setDomStorageEnabled(true);

        mWebview.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if(newProgress==100){
                    mWebviewSr.setRefreshing(false);
                }
            }

            @Override
            public void onCloseWindow(WebView window) {
                super.onCloseWindow(window);
            }
        });
        mWebview.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
                return true;
            }
        });
//        mWebview.loadUrl("http://192.168.1.14:8020/Test/index.html");
        JavaScriptInterface jsInterface = new JavaScriptInterface(this);
        mWebview.addJavascriptInterface(jsInterface,"Android");
//        mWebview.loadUrl("https://www.baidu.com/");
        mWebview.loadUrl("http://192.168.1.11:8080/react/");
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_test:
                mWebview.loadUrl("javascript:androidCallJS('潘','刘')");
                break;
        }
        return false;
    }


    final class JavaScriptInterface{

        private Context mContext;

        JavaScriptInterface(Context context){
            this.mContext = context;
        }

        @JavascriptInterface
        public String getUser(){

            return "张";
        }
        @JavascriptInterface
        public String getPatient(){
            return "王";
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add,menu);
        return true;
    }

    /**
     * 设置点击返回时优先调用网页返回
     */
    @Override
    public void onBackPressed() {
        if(mWebview.canGoBack()){
            mWebview.goBack();
        }else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebview.setVisibility(View.GONE);
    }
}

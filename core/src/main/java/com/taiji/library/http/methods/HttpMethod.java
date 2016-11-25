package com.taiji.library.http.methods;

import android.content.Context;

import com.taiji.library.data.DataPreferences;
import com.taiji.library.data.constant.C;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 描述：Retrofit网络请求实例封装
 * 创建人： panho
 * 创建时间： 2016-11-21
 */

public class HttpMethod {

    private static final int DEFAULT_TIMEOUT = 5;

    private HttpMethod(){

    }

    private static class SingleHolder{
        private static final HttpMethod INSTANCE = new HttpMethod();
    }

    public static HttpMethod getInstance(){
        return SingleHolder.INSTANCE;
    }

    public Retrofit getRetrofit(int timeout,String baseUrl){
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient().newBuilder();
        httpClientBuilder.connectTimeout(timeout, TimeUnit.SECONDS);
        Retrofit mRetrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
        return mRetrofit;
    }

    public Retrofit getRetrofit(String baseUrl){
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient().newBuilder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT,TimeUnit.SECONDS);
        Retrofit mRetrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
        return mRetrofit;
    }
    /**
     * 使用预设中的BaseUrl获取Retrofit
     * @param context 上下文环境
     * @return
     * @throws Exception
     */
    public Retrofit getRetrofit(Context context) throws Exception {
            OkHttpClient.Builder httpClientBuilder = new OkHttpClient().newBuilder();
            httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT,TimeUnit.SECONDS);
        String baseurl = DataPreferences.getInstance(context).getData(C.HTTP.BASE_URL);
        if(baseurl==null){
            throw new Exception("未初始化baseurl，请使用HttpMethod.setBaseUrl()进行初始化");
        }
        Retrofit mRetrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseurl)
                .build();
        return mRetrofit;
    }

    /**
     * 使用预设中的BaseUrl获取Retrofit，不使用json，普通字符串
     * @param context 上下文环境
     * @return
     * @throws Exception
     */
    public Retrofit getRetrofitString(Context context) throws Exception {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient().newBuilder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT,TimeUnit.SECONDS);
        String baseurl = DataPreferences.getInstance(context).getData(C.HTTP.BASE_URL);
        if(baseurl==null){
            throw new Exception("未初始化baseurl，请使用HttpMethod.setBaseUrl()进行初始化");
        }
        Retrofit mRetrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseurl)
                .build();
        return mRetrofit;
    }

    /**
     * 设置基础url
     * @param context
     * @param ip ip地址
     * @param port 端口号
     * @param serverName 应用服务名称
     */
    public static void setBaseUrl(Context context,String ip,String port,String serverName){
        DataPreferences.getInstance(context).updateDate(C.HTTP.IP,ip);
        DataPreferences.getInstance(context).updateDate(C.HTTP.PORT,port);
        DataPreferences.getInstance(context).updateDate(C.HTTP.SERVER_NAME,serverName);
        DataPreferences.getInstance(context).updateDate(C.HTTP.BASE_URL,"http://"+ip+":"+port+"/"+serverName+"/");
    }

}

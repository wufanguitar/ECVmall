package com.frank.vmall.net;

import android.content.Context;

import com.frank.vmall.net.callback.IError;
import com.frank.vmall.net.callback.IFailure;
import com.frank.vmall.net.callback.IRequest;
import com.frank.vmall.net.callback.ISuccess;
import com.frank.vmall.ui.loader.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/8/28 0028.
 */

public class RestfulClientBuilder {
    private String mUrl;
    // 每次构造RestfulClientBuilder对象时，其属性mParams也会随着一起构建
    // 因此，将其写成静态且单例
    private static final WeakHashMap<String, Object> PARAMS = RestfulCreator.getParams();
    private IRequest mIRequest;
    private ISuccess mISuccess;
    private IError mIError;
    private IFailure mIFailure;
    private RequestBody mRequestBody;
    private LoaderStyle mLoaderStyle;
    private File mFile;
    private String mDownloadDir;
    private String mExtension;
    private String mName;
    private Context mContext;

    /*
       只允许同包的RestfulClient能够new该对象
     */
    RestfulClientBuilder() {
    }

    public final RestfulClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestfulClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RestfulClientBuilder params(String key, Object object) {
        PARAMS.put(key, object);
        return this;
    }

    public final RestfulClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RestfulClientBuilder file(String fileAddress) {
        this.mFile = new File(fileAddress);
        return this;
    }

    /**
     * @param raw: 原始数据
     */
    public final RestfulClientBuilder raw(String raw) {
        this.mRequestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    /*
       下载文件存放的目录
     */
    public final RestfulClientBuilder downLoadDir(String downloadDir) {
        this.mDownloadDir = downloadDir;
        return this;
    }

    /*
       下载文件的后缀名
     */
    public final RestfulClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }

    /*
       下载的完整文件名
     */
    public final RestfulClientBuilder name(String name) {
        this.mName = name;
        return this;
    }

    public final RestfulClientBuilder request(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

    public final RestfulClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    public final RestfulClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }

    public final RestfulClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    public final RestfulClientBuilder loader(Context context, LoaderStyle loaderStyle) {
        this.mContext = context;
        this.mLoaderStyle = loaderStyle;
        return this;
    }

    /*
       默认Loader
     */
    public final RestfulClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RestfulClient build() {
        return new RestfulClient(mUrl, PARAMS, mIRequest, mISuccess, mIError, mIFailure, mRequestBody, mLoaderStyle, mFile, mDownloadDir, mExtension, mName, mContext);
    }
}

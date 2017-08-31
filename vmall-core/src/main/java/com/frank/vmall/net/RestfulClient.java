package com.frank.vmall.net;

import android.content.Context;

import com.frank.vmall.net.callback.IError;
import com.frank.vmall.net.callback.IFailure;
import com.frank.vmall.net.callback.IRequest;
import com.frank.vmall.net.callback.ISuccess;
import com.frank.vmall.net.callback.RequestCallbacks;
import com.frank.vmall.net.download.DownloadHandler;
import com.frank.vmall.ui.loader.LoaderStyle;
import com.frank.vmall.ui.loader.MallLoader;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Administrator on 2017/8/28 0028.
 */

public class RestfulClient {
    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestfulCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;
    private final RequestBody REQUEST_BODY;
    private final LoaderStyle LOADER_STYLE;
    private final File FILE;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final Context CONTEXT;

    RestfulClient(String url,
                  Map<String, Object> params,
                  IRequest request,
                  ISuccess success,
                  IError error,
                  IFailure failure,
                  RequestBody requestBody,
                  LoaderStyle loaderStyle,
                  File file,
                  String download_dir, String extension, String name, Context context) {
        this.URL = url;
        PARAMS.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.ERROR = error;
        this.FAILURE = failure;
        this.REQUEST_BODY = requestBody;
        this.LOADER_STYLE = loaderStyle;
        this.FILE = file;
        this.DOWNLOAD_DIR = download_dir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.CONTEXT = context;
    }

    public static RestfulClientBuilder builder() {
        return new RestfulClientBuilder();
    }

    private void request(HttpMethod method) {
        final RestfulService service = RestfulCreator.getRestfulService();
        Call<String> call = null;

        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        if (LOADER_STYLE != null) {
            MallLoader.showLoading(CONTEXT, LOADER_STYLE);
        }

        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                call = service.postRaw(URL, REQUEST_BODY);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call = service.putRaw(URL, REQUEST_BODY);
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = RestfulCreator.getRestfulService().upload(URL, body);
            default:
                break;
        }

        if (call != null) {
            call.enqueue(getRequestCallbacks());
        }
    }

    private Callback<String> getRequestCallbacks() {
        return new RequestCallbacks(REQUEST, SUCCESS, ERROR, FAILURE, LOADER_STYLE);
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        if (REQUEST_BODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                new RuntimeException("params must be null");
            }
            request(HttpMethod.POST_RAW);
        }
    }

    public final void put() {
        if (REQUEST_BODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                new RuntimeException("params must be null");
            }
            request(HttpMethod.PUT_RAW);
        }
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

    public final void download() {
        new DownloadHandler(URL, REQUEST, DOWNLOAD_DIR, EXTENSION, NAME, SUCCESS, ERROR, FAILURE)
                .handleDownload();
    }
}

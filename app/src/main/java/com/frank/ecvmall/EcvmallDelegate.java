package com.frank.ecvmall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.frank.vmall.delegates.VmallDelegate;
import com.frank.vmall.net.RestfulClient;
import com.frank.vmall.net.callback.IError;
import com.frank.vmall.net.callback.IFailure;
import com.frank.vmall.net.callback.ISuccess;

/**
 * Created by Administrator on 2017/8/28 0028.
 */

public class EcvmallDelegate extends VmallDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_ecvmall;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestfulClient();
    }

    private void testRestfulClient() {
        RestfulClient.builder()
                .url("http://127.0.0.1/index")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }
}

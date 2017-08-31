package com.frank.vmall.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.frank.vmall.app.Mall;
import com.frank.vmall.net.callback.IRequest;
import com.frank.vmall.net.callback.ISuccess;
import com.frank.vmall.utils.file.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/8/29 0029.
 */

public class SaveFileTask extends AsyncTask<Object, Void, File> {
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;

    public SaveFileTask(IRequest request, ISuccess success) {
        this.REQUEST = request;
        this.SUCCESS = success;
    }

    @Override
    protected File doInBackground(Object[] params) {
        String downloadDir = (String) params[0];
        String extension = (String) params[1];
        String name = (String) params[2];
        final ResponseBody requestBody = (ResponseBody) params[3];
        final InputStream is = requestBody.byteStream();
        if (downloadDir == null || "".equals(downloadDir)) {
            downloadDir = "down_loads";
        }
        if (extension == null || "".equals(extension)) {
            extension = "";
        }
        if (name == null || "".equals(name)) {
            return FileUtil.writeToDisk(is, downloadDir, extension.toUpperCase(), extension);
        } else {
            return FileUtil.writeToDisk(is, downloadDir, name);
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (SUCCESS != null) {
            SUCCESS.onSuccess(file.getPath());
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        autoInstallApk(file);
    }

    private void autoInstallApk(File file) {
        if ("apk".equals(FileUtil.getExtension(file.getPath()))) {
            final Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archiv");
            Mall.getApplicationContext().startActivity(install);
        }
    }
}

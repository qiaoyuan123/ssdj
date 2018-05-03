package com.example.qiaoy.qiao_core.net.callback;

import android.os.Handler;
import android.telecom.Call;

import com.example.qiaoy.qiao_core.app.Core;
import com.example.qiaoy.qiao_core.net.RestCreator;
import com.example.qiaoy.qiao_core.ui.CoreLoader;
import com.example.qiaoy.qiao_core.ui.LoaderStyle;


import retrofit2.Callback;
import retrofit2.Response;

public class RequestCallBacks implements Callback<String> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;
    private final LoaderStyle LOADER_STYLE;
    private static final Handler HANDLER = Core.getHandler();

    public RequestCallBacks(IRequest REQUEST, ISuccess SUCCESS, IError ERROR, IFailure FAILURE, LoaderStyle LOADER_STYLE) {
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
        this.ERROR = ERROR;
        this.FAILURE = FAILURE;
        this.LOADER_STYLE = LOADER_STYLE;
    }


    @Override
    public void onResponse(retrofit2.Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (SUCCESS != null) {
                SUCCESS.onSuccess(response.body());
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }

        onRequestFinish();
    }

    @Override
    public void onFailure(retrofit2.Call<String> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }

        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }

        onRequestFinish();
    }

    private void onRequestFinish() {
        if (LOADER_STYLE != null) {
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    RestCreator.getParams().clear();
                    CoreLoader.stopLoading();
                }
            }, 500);
        }
    }
}

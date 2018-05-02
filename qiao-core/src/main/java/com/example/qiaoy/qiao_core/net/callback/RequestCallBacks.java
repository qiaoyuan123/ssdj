package com.example.qiaoy.qiao_core.net.callback;

import android.telecom.Call;

import retrofit2.Callback;
import retrofit2.Response;

public class RequestCallBacks implements Callback<String> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;

    public RequestCallBacks(IRequest REQUEST, ISuccess SUCCESS, IError ERROR, IFailure FAILURE) {
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
        this.ERROR = ERROR;
        this.FAILURE = FAILURE;
    }


    @Override
    public void onResponse(retrofit2.Call<String> call, Response<String> response) {
        if(response.isSuccessful()){
            if(SUCCESS != null){
                SUCCESS.onSuccess(response.body());
            }
        }else {
            if (ERROR != null){
                ERROR.onError(response.code(),response.message());
            }
        }
    }

    @Override
    public void onFailure(retrofit2.Call<String> call, Throwable t) {
        if(FAILURE != null){
            FAILURE.onFailure();
        }

        if(REQUEST != null){
            REQUEST.onRequestEnd();
        }
    }
}

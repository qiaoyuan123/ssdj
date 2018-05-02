package com.example.qiaoy.qiao_core.net;

import com.example.qiaoy.qiao_core.net.callback.IError;
import com.example.qiaoy.qiao_core.net.callback.IFailure;
import com.example.qiaoy.qiao_core.net.callback.IRequest;
import com.example.qiaoy.qiao_core.net.callback.ISuccess;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public final class RestClientBuilder {

    private String mUrl;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private IRequest mIRequest;
    private ISuccess mISuccess;
    private IError mIError;
    private IFailure mIFailure;
    private RequestBody mBody;

    RestClientBuilder() {

    }


    public RestClientBuilder url(String mUrl) {
        this.mUrl = mUrl;
        return this;
    }

    public RestClientBuilder params(WeakHashMap<String, Object> mParams) {
        PARAMS.putAll(mParams);
        return this;
    }

    public RestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public RestClientBuilder failure(IFailure mIFailure) {
        this.mIFailure = mIFailure;
        return this;
    }

    public RestClientBuilder error(IError mIError) {
        this.mIError = mIError;
        return this;
    }

    public RestClientBuilder success(ISuccess mISuccess) {
        this.mISuccess = mISuccess;
        return this;
    }

    public RestClientBuilder onRequest(IRequest mIRequest) {
        this.mIRequest = mIRequest;
        return this;
    }

    public final RestClient build() {
        return new RestClient(mUrl,
                PARAMS,
                mIRequest,
                mISuccess,
                mIError,
                mIFailure,
                mBody);
    }

}

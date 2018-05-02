package com.example.qiaoy.qiao_core.net;

import com.example.qiaoy.qiao_core.net.callback.IError;
import com.example.qiaoy.qiao_core.net.callback.IFailure;
import com.example.qiaoy.qiao_core.net.callback.IRequest;
import com.example.qiaoy.qiao_core.net.callback.ISuccess;
import com.example.qiaoy.qiao_core.net.callback.RequestCallBacks;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;


public class RestClient {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;
    private final RequestBody BODY;


    RestClient(String URL,
               Map<String, Object> PARAMS,
               IRequest REQUEST,
               ISuccess SUCCESS,
               IError ERROR,
               IFailure FAILURE,
               RequestBody BODY) {
        this.URL = URL;
        PARAMS.putAll(PARAMS);
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
        this.ERROR = ERROR;
        this.FAILURE = FAILURE;
        this.BODY = BODY;
    }


    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;

        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            default:
                break;
        }

        if (call != null) {
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallBacks(
                REQUEST,
                SUCCESS,
                ERROR,
                FAILURE
        );
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        request(HttpMethod.POST);
    }

    public final void put() {
        request(HttpMethod.PUT);
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }
}

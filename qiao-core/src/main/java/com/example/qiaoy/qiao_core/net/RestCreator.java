package com.example.qiaoy.qiao_core.net;

import android.util.TimeUtils;

import com.example.qiaoy.qiao_core.app.ConfigType;
import com.example.qiaoy.qiao_core.app.Core;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public final class RestCreator {

    /**
     * 参数容器
     */
    private static final class ParamsHolder {
        private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }
    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }
    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }
    /**
     * 构建全局Retrofit客户端
     */
    private static final class RetrofitHolder{
        private static final String BASE_URL = (String) Core.getConfigs().get(ConfigType.API_HOST.name());
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }
    /**
     * 构建OkHttp
     */
    private static final class OkhttpHolder{
        private static final int TIME_OUT = 60;
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT , TimeUnit.SECONDS)
                .build();
    }

    /**
     * Service接口
     */
    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }

}

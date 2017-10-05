package com.example.lenovo.sitra;

import com.loopj.android.http.*;

public class RestClient {

    public static String BASE_URL = "http://ryrodontologia.com/sitracom/api/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler){
        client.get(BASE_URL + url, params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler){
        client.post(BASE_URL + url, params, responseHandler);
    }
}

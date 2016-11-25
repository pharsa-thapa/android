package com.suresh.androidapp.helpers;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.suresh.androidapp.constants.WebServiceConstants;

/**
 * Created by suresh on 11/14/16.
 */
public class HttpHelper {

    private static final String URL = WebServiceConstants.FLOWER_SERVICE_URL;

    private static AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        asyncHttpClient.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        asyncHttpClient.post(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void getByUrl(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        asyncHttpClient.get(url, params, responseHandler);
    }

    public static void postByUrl(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        asyncHttpClient.post(url, params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return URL + relativeUrl;
    }
}

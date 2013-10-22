/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.yellowme.conekta.rest;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.apache.http.entity.StringEntity;

/**
 *
 * @author javier
 */
public class ConektaRestClientAsync {

    private static final String BASE_URL = "https://api.conekta.io/";
    private static final AsyncHttpClient asyncclient;    

    static {
        asyncclient = new AsyncHttpClient();
        asyncclient.addHeader("Accept", "application/vnd.conekta-v0.2.0+json");
        asyncclient.addHeader("Content-type ", "application/json");        
    }                    

    public static void get(Context context, String url, RequestParams params, AsyncHttpResponseHandler responseHandler) throws PackageManager.NameNotFoundException {
        asyncclient.setBasicAuth(getConektakey(context), "");
        asyncclient.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(Context context, String url,StringEntity entity, AsyncHttpResponseHandler responseHandler) throws PackageManager.NameNotFoundException {
        asyncclient.setBasicAuth(getConektakey(context), "");
        asyncclient.post(context,getAbsoluteUrl( url), entity, "application/json",responseHandler);
    }

    private static String getConektakey(Context context) throws PackageManager.NameNotFoundException{
        ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(),PackageManager.GET_META_DATA);       
        return ai.metaData.getString("conektakey");
    }
    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}

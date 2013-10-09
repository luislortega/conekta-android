/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.yellowme.conekta.rest;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

/**
 *
 * @author javier
 */
public class ConektaRestClientSync {

    private static final String BASE_URL = "https://api.conekta.io/";    
    
    public static HttpResponseLite get(Context context, String url) throws Exception{
        HttpSyncConexion conexion = getHttpSyncConexion(context);
        conexion.executeGet(getAbsoluteUrl(url));        
        return  conexion.getHttpResponseLite();
    }
    
    public static HttpResponseLite post(Context context, String url, String json) throws Exception {
        HttpSyncConexion conexion = getHttpSyncConexion(context);
        conexion.executePostJson(getAbsoluteUrl(url), json);
        return conexion.getHttpResponseLite();
    }
    
    private static HttpSyncConexion getHttpSyncConexion(Context context) throws PackageManager.NameNotFoundException{
        HttpSyncConexion conexion = new HttpSyncConexion();                
        conexion.addHeader("Accept", "application/vnd.conekta-v0.2.0+json");
        conexion.addHeader("Content-type ", "application/json");        
        ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(),PackageManager.GET_META_DATA);       
        conexion.setBasicAuth(ai.metaData.getString("conektakey"),"");                        
        return conexion;
    }
    
    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}

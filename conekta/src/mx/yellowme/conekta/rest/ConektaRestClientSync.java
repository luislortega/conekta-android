/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.yellowme.conekta.rest;

/**
 *
 * @author javier
 */
public class ConektaRestClientSync {

    private static final String BASE_URL = "https://api.conekta.io/";    
    
    public static HttpResponseLite get(String url) throws Exception{
        HttpSyncConexion conexion = getHttpSyncConexion();
        conexion.executeGet(getAbsoluteUrl(url));        
        return  conexion.getHttpResponseLite();
    }
    
    public static HttpResponseLite post(String url,String json) throws Exception {
        HttpSyncConexion conexion = getHttpSyncConexion();
        conexion.executePostJson(getAbsoluteUrl(url), json);
        return conexion.getHttpResponseLite();
    }
    
    private static HttpSyncConexion getHttpSyncConexion(){
        HttpSyncConexion conexion = new HttpSyncConexion();                
        conexion.addHeader("Accept", "application/vnd.conekta-v0.2.0+json");
        conexion.addHeader("Content-type ", "application/json");
        conexion.setBasicAuth("key_EWkxLTVn4BWyRyoY","");
        return conexion;
    }
    
    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}

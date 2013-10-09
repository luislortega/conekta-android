/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.yellowme.conekta.rest;

import android.content.Context;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.yellowme.conekta.objects.Cargo;
import org.apache.http.entity.StringEntity;

/**
 *
 * @author javier
 */
public class Conekta {

    public static void getChargesAsync(JsonHttpResponseHandler handler){
        ConektaRestClientAsync.get("charges", null, handler);
    }
    
    public static void getEventsAsync(JsonHttpResponseHandler handler){
        ConektaRestClientAsync.get("events", null, handler);
    }
    
    public static void chargeAsync(Context context, Cargo cargo,JsonHttpResponseHandler handler) {
        try {
            Gson gson = new Gson();
            String sgson = gson.toJson(cargo);
            StringEntity entity = new StringEntity(sgson);
            ConektaRestClientAsync.post(context,"charges", entity, handler);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Conekta.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public static HttpResponseLite getChargesSync(){
        try {              
            return ConektaRestClientSync.get("charges");
        } catch (Exception ex) {
            Logger.getLogger(Conekta.class.getName()).log(Level.SEVERE, null, ex);
            return new HttpResponseLite(400, ex.getMessage(), null, null);
        }
    }
    
    public static HttpResponseLite getEventsSync(){
        try {              
            return ConektaRestClientSync.get("events");
        } catch (Exception ex) {
            Logger.getLogger(Conekta.class.getName()).log(Level.SEVERE, null, ex);
            return new HttpResponseLite(400, ex.getMessage(), null, null);
        }
    }
    
    public static HttpResponseLite chargeSync(Cargo cargo) {
        try {
            Gson gson = new Gson();                            
            String sgson = gson.toJson(cargo);            
            return ConektaRestClientSync.post("charges",sgson);            
        } catch (Exception ex) {
            Logger.getLogger(Conekta.class.getName()).log(Level.SEVERE, null, ex);
            return new HttpResponseLite(400, ex.getMessage(), null, null);
        }        
    }
    
    
//    new JsonHttpResponseHandler() {            
//            @Override
//            public void onSuccess(JSONArray jsona) {
//            }                                  
//            @Override
//            public void onFailure(Throwable thrwbl, JSONArray jsona) {                
//            }                
//        });
    //            ConektaRestClientAsync.post(context,"charges", entity, new JsonHttpResponseHandler() {                        
//                @Override
//                public void onSuccess(JSONObject jsono) {                
//                    jr.setjSONObject(jsono);
//                }                                              
//                @Override
//                public void onFailure(Throwable thrwbl, JSONObject jsono) { 
//                }
//            });
}


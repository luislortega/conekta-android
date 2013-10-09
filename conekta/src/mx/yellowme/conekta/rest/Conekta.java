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
    
    public static HttpResponseLite getChargesSync(Context context){
        try {              
            return ConektaRestClientSync.get(context, "charges");
        } catch (Exception ex) {
            Logger.getLogger(Conekta.class.getName()).log(Level.SEVERE, null, ex);
            return new HttpResponseLite(400, ex.getMessage(), null, null);
        }
    }
    
    public static HttpResponseLite getEventsSync(Context context){
        try {              
            return ConektaRestClientSync.get(context ,"events");
        } catch (Exception ex) {
            Logger.getLogger(Conekta.class.getName()).log(Level.SEVERE, null, ex);
            return new HttpResponseLite(400, ex.getMessage(), null, null);
        }
    }
    
    public static HttpResponseLite chargeSync(Context context, Cargo cargo) {
        try {
            Gson gson = new Gson();                            
            String sgson = gson.toJson(cargo);            
            return ConektaRestClientSync.post(context ,"charges",sgson);            
        } catch (Exception ex) {
            Logger.getLogger(Conekta.class.getName()).log(Level.SEVERE, null, ex);
            return new HttpResponseLite(400, ex.getMessage(), null, null);
        }        
    }        
    
}


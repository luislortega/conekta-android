/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.yellowme.conekta.rest;

import android.content.Context;
import android.content.pm.PackageManager;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.threatmetrix.TrustDefenderMobile.ProfileNotify;
import com.threatmetrix.TrustDefenderMobile.TrustDefenderMobile;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.yellowme.conekta.objects.Charge;
import org.apache.http.entity.StringEntity;

/**
 *
 * @author javier
 */
public class Conekta {

    public static final String ORG_ID = "k8vif92e";
    public static final String FTP_SERVER = "h.online-metrix.net";
    public static final String URL_SERVER = "http://threatmetrix.com";
    private static TrustDefenderMobile profile;

    static {
        profile = new TrustDefenderMobile();
    }

    public static void getChargesAsync(final Context context, JsonHttpResponseHandler handler) {
        try {
            ConektaRestClientAsync.get(context, "charges", null, handler);
        } catch (PackageManager.NameNotFoundException ex) {
            Logger.getLogger(Conekta.class.getName()).log(Level.SEVERE, "Dont have api key conekta", ex);
        }
    }

    public static void getEventsAsync(final Context context, JsonHttpResponseHandler handler) {
        try {
            ConektaRestClientAsync.get(context, "events", null, handler);
        } catch (PackageManager.NameNotFoundException ex) {
            Logger.getLogger(Conekta.class.getName()).log(Level.SEVERE, "Dont have api key conekta", ex);
        }
    }

    public static void chargeAsync(final Context context, final Charge charge, final JsonHttpResponseHandler handler) {
        try {
            doProfileAsync(context, new ProfileNotify() {
                @Override
                public void complete() {
                    try {
                        switch (profile.getStatus()) {
                            case THM_OK:
                                String sesString = profile.getSessionID();
                                sesString = sesString.substring(18);
                                charge.setSessionId(sesString);
                                Gson gson = new Gson();
                                String sgson = gson.toJson(charge);
                                StringEntity entity = new StringEntity(sgson);
                                ConektaRestClientAsync.post(context, "charges", entity, handler);
                                break;
                            default:
                                Logger.getLogger(Conekta.class.getName()).log(Level.SEVERE, "Time out exception in fingerprint");
                        }
                    } catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(Conekta.class.getName()).log(Level.SEVERE, "Bad format Json", ex);
                    } catch (PackageManager.NameNotFoundException ex) {
                        Logger.getLogger(Conekta.class.getName()).log(Level.SEVERE, "Dont have api key conekta", ex);
                    }
                }
            });
        } catch (InterruptedException ex) {
            Logger.getLogger(Conekta.class.getName()).log(Level.SEVERE, "Time out exception in fingerprint", ex);
        }
    }

    public static HttpResponseLite getChargesSync(Context context) {
        try {
            return ConektaRestClientSync.get(context, "charges");
        } catch (Exception ex) {
            Logger.getLogger(Conekta.class.getName()).log(Level.SEVERE, null, ex);
            return new HttpResponseLite(400, ex.getMessage(), null, null);
        }
    }

    public static HttpResponseLite getEventsSync(Context context) {
        try {
            return ConektaRestClientSync.get(context, "events");
        } catch (Exception ex) {
            Logger.getLogger(Conekta.class.getName()).log(Level.SEVERE, null, ex);
            return new HttpResponseLite(400, ex.getMessage(), null, null);
        }
    }

    public static HttpResponseLite chargeSync(Context context, Charge charge) {
        try {
            Gson gson = new Gson();
            String idSession = doProfile(context);
            String sesString = profile.getSessionID();
            sesString = sesString.substring(18);
            charge.setSessionId(sesString);
            String sgson = gson.toJson(charge);
            switch (profile.getStatus()) {
                case THM_OK:
                    return ConektaRestClientSync.post(context, "charges", sgson);
            }
            return new HttpResponseLite(400, profile.getStatus().toString(), null, null);
        } catch (Exception ex) {
            Logger.getLogger(Conekta.class.getName()).log(Level.SEVERE, null, ex);
            return new HttpResponseLite(400, ex.getMessage(), null, null);
        }
    }

    private static void doProfileAsync(Context context, ProfileNotify profileNotify) throws InterruptedException {
        profile.setTimeout(10);
        profile.setCompletionNotifier(profileNotify);
        int options = TrustDefenderMobile.THM_OPTION_ALL_ASYNC;
        profile.setSessionID(getSessionIdGeneric());
        profile.doProfileRequest(context, ORG_ID, FTP_SERVER, URL_SERVER, options);
    }

    private static String doProfile(Context context) throws InterruptedException {
        profile.setTimeout(10);
        int options = TrustDefenderMobile.THM_OPTION_ALL_SYNC;
        profile.setCompletionNotifier(null);
        profile.setSessionID(getSessionIdGeneric());
        profile.doProfileRequest(context, ORG_ID, FTP_SERVER, URL_SERVER, options);
        return profile.getSessionID();
    }

    private static String getSessionIdGeneric() {
        String sessionId = "banorteixe_conekta";
        String useable_characters = "abcdefghijklmnopqrstuvwxyz0123456789";
        for(int x=0; x<30; x++){
            sessionId +=useable_characters.charAt((int) Math.floor(Math.random() * 36 ));            
        }
        return  sessionId;
    }
}

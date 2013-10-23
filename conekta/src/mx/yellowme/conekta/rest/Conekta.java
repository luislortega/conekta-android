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

    public static void getCharges(final Context context, JsonHttpResponseHandler handler) {
        try {
            ConektaRestClientAsync.get(context, "charges", null, handler);
        } catch (PackageManager.NameNotFoundException ex) {
            Logger.getLogger(Conekta.class.getName()).log(Level.SEVERE, "Dont have api key conekta", ex);
        }
    }

    public static void getEvents(final Context context, JsonHttpResponseHandler handler) {
        try {
            ConektaRestClientAsync.get(context, "events", null, handler);
        } catch (PackageManager.NameNotFoundException ex) {
            Logger.getLogger(Conekta.class.getName()).log(Level.SEVERE, "Dont have api key conekta", ex);
        }
    }

    public static void charge(final Context context, final Charge charge, final JsonHttpResponseHandler handler) {
        try {
            doProfile(context, new ProfileNotify() {
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

    private static void doProfile(Context context, ProfileNotify profileNotify) throws InterruptedException {
        profile.setTimeout(10);
        profile.setCompletionNotifier(profileNotify);
        int options = TrustDefenderMobile.THM_OPTION_ALL_ASYNC;
        profile.setSessionID(getSessionIdGeneric());
        profile.doProfileRequest(context, ORG_ID, FTP_SERVER, URL_SERVER, options);
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

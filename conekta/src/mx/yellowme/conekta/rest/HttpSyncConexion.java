/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.yellowme.conekta.rest;

import android.util.Base64;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

public class HttpSyncConexion {

    private ArrayList<NameValuePair> params;
    private ArrayList<NameValuePair> headers;
    private int responseCode;
    private String message;
    private String response;
    private HttpResponse httpResponse;
    private HttpResponseLite httpResponseLite;

    public HttpResponseLite getHttpResponseLite() {
        return httpResponseLite;
    }

    public void setHttpResponseLite(HttpResponseLite httpResponseLite) {
        this.httpResponseLite = httpResponseLite;
    }

    public HttpSyncConexion() {
        params = new ArrayList<NameValuePair>();
        headers = new ArrayList<NameValuePair>();
    }

    public void addParam(String name, String value) {
        params.add(new BasicNameValuePair(name, value));
    }

    public void addHeader(String name, String value) {
        headers.add(new BasicNameValuePair(name, value));
    }

    public void setBasicAuth(String user, String pass) {
        String comb = user + ":" + pass;
        String auth = "Basic " + Base64.encodeToString(comb.getBytes(), Base64.NO_WRAP);
        headers.add(new BasicNameValuePair("Authorization", auth));
    }

    public void executeGet(String url) throws Exception {
        //agregar parametros
        String combinedParams = "";
        if (!params.isEmpty()) {
            combinedParams += "?";
            for (NameValuePair p : params) {
                String paramString = p.getName() + "=" + URLEncoder.encode(p.getValue(), "UTF-8");
                if (combinedParams.length() > 1) {
                    combinedParams += "&" + paramString;
                } else {
                    combinedParams += paramString;
                }
            }
        }
        HttpGet request = new HttpGet(url + combinedParams);
        //agregar headers
        for (NameValuePair h : headers) {
            request.addHeader(h.getName(), h.getValue());
        }
        executeRequest(request);
    }

    public void executePost(String url) throws Exception {
        HttpPost request = new HttpPost(url);
        //agregar headers
        for (NameValuePair h : headers) {
            request.addHeader(h.getName(), h.getValue());
        }
        if (!params.isEmpty()) {
            request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        }
        executeRequest(request);
    }

    public void executePostJson(String url, String json) throws Exception {
        HttpPost request = new HttpPost(url);
        for (NameValuePair h : headers) {
            request.addHeader(h.getName(), h.getValue());
        }
        StringEntity entity = new StringEntity(json);
        request.setEntity(entity);
        executeRequest(request);
    }

    private void executeRequest(HttpUriRequest request) {
        HttpClient client = new DefaultHttpClient();
        try {
            this.httpResponse = client.execute(request);
            this.responseCode = httpResponse.getStatusLine().getStatusCode();
            this.message = httpResponse.getStatusLine().getReasonPhrase();            
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                this.response = convertStreamToString(instream);
                instream.close();
            }
            this.httpResponseLite = new HttpResponseLite(responseCode, message, response, httpResponse);
        } catch (IOException ex) {
            client.getConnectionManager().shutdown();
            Logger.getLogger(HttpSyncConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException ex) {
            Logger.getLogger(HttpSyncConexion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(HttpSyncConexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return sb.toString();
    }
}

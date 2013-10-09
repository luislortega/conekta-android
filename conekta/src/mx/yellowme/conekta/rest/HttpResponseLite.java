/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.yellowme.conekta.rest;

import org.apache.http.HttpResponse;

/**
 *
 * @author javier
 */
public class HttpResponseLite {

    private int responseCode;
    private String message;
    private String response;
    private HttpResponse httpResponse;

    public HttpResponseLite(int responseCode, String message, String response, HttpResponse httpResponse) {
        this.responseCode = responseCode;
        this.message = message;
        this.response = response;
        this.httpResponse = httpResponse;
    }

    
    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public HttpResponse getHttpResponse() {
        return httpResponse;
    }

    public void setHttpResponse(HttpResponse httpResponse) {
        this.httpResponse = httpResponse;
    }
    
    
}

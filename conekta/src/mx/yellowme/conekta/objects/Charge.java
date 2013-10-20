/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.yellowme.conekta.objects;

import java.math.BigInteger;

/**
 *
 * @author javier
 */
public abstract class Charge {
    
    private String description;
    private BigInteger amount;
    private String currency;
    private String reference_id;
    private Details details;
    private String session_id;

    public Charge() {
    }
    
    public Charge(String description, BigInteger amount, String currency) {
        this.description = description;
        this.amount = amount;
        this.currency = currency;               
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public void setAmount(BigInteger amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getReferenceId() {
        return reference_id;
    }

    public void setReferenceId(String referenceId) {
        this.reference_id = referenceId;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }  

    public String getSessionId() {
        return session_id;
    }

    public void setSessionId(String session_id) {
        this.session_id = session_id;
    }

    @Override
    public String toString() {
        return "Charge{" + "description=" + description + ", amount=" + amount + ", currency=" + currency + ", reference_id=" + reference_id + ", details=" + details + ", session_id=" + session_id + '}';
    }
    
        
}

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
public class ChargeCash extends Cargo{
    
    private Cash cash;    

    public ChargeCash(Cash cash, String description, BigInteger amount, String currency) {
        super(description, amount, currency);
        this.cash = cash;
    }   

    public Cash getCash() {
        return cash;
    }

    public void setCash(Cash cash) {
        this.cash = cash;
    }
    
    @Override
    public String toString() {
        return "CargoCash{" + "cash=" + cash + '}';
    }    
    
        
}

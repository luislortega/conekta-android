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
public class ChargeCard extends Charge{
    
    private Card card;    

    public ChargeCard(Card card, String description, BigInteger amount, String currency) {
        super(description, amount, currency);
        this.card = card;
    }

    @Override
    public String toString() {
        return "CargoCard{" + "card=" + card + '}';
    }        
    
    
        
}

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
public class ChargeBank extends Charge{
    
    private Bank bank;

    public ChargeBank(Bank bank, String description, BigInteger amount, String currency) {
        super(description, amount, currency);
        this.bank = bank;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    @Override
    public String toString() {
        return "CargoBank{" + "bank=" + bank + '}';
    }
    
    
        
}

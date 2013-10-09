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
public class Card  {
         
    private BigInteger number;
    private Integer exp_month;
    private Integer exp_year;
    private String name;
    private Integer cvc;
    private Address address;

    public Card(BigInteger number, Integer expMonth, Integer expYear, String name, Integer cvc) {
        this.number = number;
        this.exp_month = expMonth;
        this.exp_year = expYear;
        this.name = name;
        this.cvc = cvc;     
    }       

    public BigInteger getNumber() {
        return number;
    }

    public void setNumber(BigInteger number) {
        this.number = number;
    }

    public Integer getExpMonth() {
        return exp_month;
    }

    public void setExpMonth(Integer expMonth) {
        this.exp_month = expMonth;
    }

    public Integer getExpYear() {
        return exp_year;
    }

    public void setExpYear(Integer expYear) {
        this.exp_year = expYear;
    }        

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCvc() {
        return cvc;
    }

    public void setCvc(Integer cvc) {
        this.cvc = cvc;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Card[" + "number=" + number + ", exp_month=" + exp_month + ", exp_year=" + exp_year + ", name=" + name + ", cvc=" + cvc + ", address=" + address + ']';
    }
        
    
    
}

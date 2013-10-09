/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.yellowme.conekta.objects;

/**
 *
 * @author javier
 */
public class Address {    
      
    String street1;
    String street2;
    String street3;
    String city;
    String state;
    String country;
    String zip;   

    public Address(String street1, String city, String state, String country, String zip) {
        this.street1 = street1;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zip = zip;
    }
    
    

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getStreet2() {
        return street2;
    }

    public String getStreet3() {
        return street3;
    } 

    @Override
    public String toString() {
        return "address{" + "street1=" + street1 + ", street2=" + street2 + ", street3=" + street3 + ", city=" + city + ", state=" + state + ", country=" + country + ", zip=" + zip + '}';
    }
    
    
    
    
    
}

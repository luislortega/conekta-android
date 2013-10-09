/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.yellowme.conekta.objects;

/**
 *
 * @author javier
 */
public class BillingAddress {

    private String taxId;
    private String company_name;
    private String street1;
    private String street2;
    private String street3;
    private String city;
    private String  state;
    private String zip;
    private String phone;
    private String email;

    public BillingAddress(String taxId, String companyName, String street1, String city, String state, String zip, String phone, String email) {
        this.taxId = taxId;
        this.company_name = companyName;
        this.street1 = street1;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
    }

    public BillingAddress(String taxId, String companyName, String street1, String street2, String street3, String city, String state, String zip, String phone, String email) {
        this.taxId = taxId;
        this.company_name = companyName;
        this.street1 = street1;
        this.street2 = street2;
        this.street3 = street3;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
    }

    @Override
    public String toString() {
        return "BillingAddress{" + "tax_id=" + taxId + ", company_name=" + company_name + ", street1=" + street1 + ", street2=" + street2 + ", street3=" + street3 + ", city=" + city + ", state=" + state + ", zip=" + zip + ", phone=" + phone + ", email=" + email + '}';
    }
    
    
}

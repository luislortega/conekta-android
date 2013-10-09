/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.yellowme.conekta.objects;

import java.util.List;

/**
 *
 * @author javier
 */
public class Details {
    
    private String name;
    private String phone;
    private String email;
    private String date_of_birth;
    private BillingAddress billing_address;
    private List<LineItems> line_items;
    private Shipment shipment;

    public Details(String name, String phone, String email, String dateOfBirth) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.date_of_birth = dateOfBirth;
    }

    public Details(String name, String phone, String email, String dateOfBirth, BillingAddress billingAddress, List<LineItems> lineItemses, Shipment shipment) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.date_of_birth = dateOfBirth;
        this.billing_address = billingAddress;
        this.line_items = lineItemses;
        this.shipment = shipment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return date_of_birth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.date_of_birth = dateOfBirth;
    }

    public BillingAddress getBillingAddress() {
        return billing_address;
    }

    public void setBillingAddress(BillingAddress billingAddress) {
        this.billing_address = billingAddress;
    }

    public List<LineItems> getLineItemses() {
        return line_items;
    }

    public void setLineItemses(List<LineItems> lineItemses) {
        this.line_items = lineItemses;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    @Override
    public String toString() {
        return "Details{" + "name=" + name + ", phone=" + phone + ", email=" + email + ", date_of_birth=" + date_of_birth + ", billingAddress=" + billing_address + ", lineItemses=" + line_items + ", shipment=" + shipment + '}';
    }
    
    
}

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
public class Shipment {
    
    private String carrier;
    private String service;
    private String tracking_id;
    private BigInteger price;
    private Address address;

    public Shipment(String carrier, String service, String trackingId, BigInteger price, Address address) {
        this.carrier = carrier;
        this.service = service;
        this.tracking_id = trackingId;
        this.price = price;
        this.address = address;
    }

    public Shipment(String carrier, String service, String trackingId, BigInteger price) {
        this.carrier = carrier;
        this.service = service;
        this.tracking_id = trackingId;
        this.price = price;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getTrackingId() {
        return tracking_id;
    }

    public void setTrackingId(String trackingId) {
        this.tracking_id = trackingId;
    }

    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Shipment{" + "carrier=" + carrier + ", service=" + service + ", tracking_id=" + tracking_id + ", price=" + price + ", address=" + address + '}';
    }        
    
}
